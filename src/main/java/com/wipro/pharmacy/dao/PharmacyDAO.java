package com.wipro.pharmacy.dao;

import com.wipro.pharmacy.bean.PharmacyBean;
import com.wipro.pharmacy.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PharmacyDAO {

    // ================= ADD RECORD =================
    public String createRecord(PharmacyBean bean) {

        String result = "FAIL";

        try (Connection con = DBUtil.getDBConnection()) {

            String sql =
                "INSERT INTO PHARMACY_TB " +
                "(RECORDID, MEDICINENAME, SUPPLIERNAME, PURCHASE_DATE, QUANTITY, PRICE, REMARKS) " +
                "VALUES (?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bean.getRecordId());
            ps.setString(2, bean.getMedicineName());
            ps.setString(3, bean.getSupplierName());
            ps.setDate(4, new java.sql.Date(bean.getPurchaseDate().getTime()));
            ps.setInt(5, bean.getQuantity());
            ps.setInt(6, bean.getPrice());
            ps.setString(7, bean.getRemarks());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                result = bean.getRecordId();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // ================= FETCH SINGLE RECORD =================
    public PharmacyBean fetchRecord(String medicineName, java.util.Date purchaseDate) {

        PharmacyBean bean = null;

        try (Connection con = DBUtil.getDBConnection()) {

            String sql =
                "SELECT RECORDID, MEDICINENAME, SUPPLIERNAME, PURCHASE_DATE, QUANTITY, PRICE, REMARKS " +
                "FROM PHARMACY_TB WHERE MEDICINENAME=? AND PURCHASE_DATE=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, medicineName);
            ps.setDate(2, new java.sql.Date(purchaseDate.getTime()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bean = new PharmacyBean();
                bean.setRecordId(rs.getString("RECORDID"));
                bean.setMedicineName(rs.getString("MEDICINENAME"));
                bean.setSupplierName(rs.getString("SUPPLIERNAME"));
                bean.setPurchaseDate(rs.getDate("PURCHASE_DATE"));
                bean.setQuantity(rs.getInt("QUANTITY"));
                bean.setPrice(rs.getInt("PRICE"));
                bean.setRemarks(rs.getString("REMARKS"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }

    // ================= CHECK RECORD EXISTS =================
    public boolean recordExists(String medicineName, java.util.Date purchaseDate) {
        return fetchRecord(medicineName, purchaseDate) != null;
    }

    // ================= GENERATE RECORD ID =================
    public String generateRecordID(String medicineName, java.util.Date purchaseDate)
            throws Exception {

        String recordId = "";

        try (Connection con = DBUtil.getDBConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT PHARMACY_SEQ.NEXTVAL FROM DUAL")) {

            if (rs.next()) {
                int seq = rs.getInt(1);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String datePart = sdf.format(purchaseDate);
                String medPart = medicineName.substring(0, 2).toUpperCase();

                recordId = datePart + medPart + seq;
            }
        }

        return recordId;
    }

    // ================= FETCH ALL RECORDS =================
    public List<PharmacyBean> fetchAllRecords() {

        List<PharmacyBean> list = new ArrayList<>();

        try (Connection con = DBUtil.getDBConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(
                 "SELECT RECORDID, MEDICINENAME, SUPPLIERNAME, PURCHASE_DATE, QUANTITY, PRICE, REMARKS " +
                 "FROM PHARMACY_TB")) {

            while (rs.next()) {
                PharmacyBean bean = new PharmacyBean();
                bean.setRecordId(rs.getString("RECORDID"));
                bean.setMedicineName(rs.getString("MEDICINENAME"));
                bean.setSupplierName(rs.getString("SUPPLIERNAME"));
                bean.setPurchaseDate(rs.getDate("PURCHASE_DATE"));
                bean.setQuantity(rs.getInt("QUANTITY"));
                bean.setPrice(rs.getInt("PRICE"));
                bean.setRemarks(rs.getString("REMARKS"));

                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
