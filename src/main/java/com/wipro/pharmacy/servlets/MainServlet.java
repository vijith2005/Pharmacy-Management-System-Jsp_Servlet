package com.wipro.pharmacy.servlets;

import com.wipro.pharmacy.bean.PharmacyBean;
import com.wipro.pharmacy.service.Administrator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    Administrator admin = new Administrator();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        try {
            if ("newRecord".equals(operation)) {

                PharmacyBean bean = new PharmacyBean();
                bean.setMedicineName(request.getParameter("medicineName"));
                bean.setSupplierName(request.getParameter("supplierName"));
                bean.setPurchaseDate(
                    new SimpleDateFormat("yyyy-MM-dd")
                        .parse(request.getParameter("purchaseDate"))
                );
                bean.setQuantity(Integer.parseInt(request.getParameter("quantity")));
                bean.setPrice(Integer.parseInt(request.getParameter("price")));
                bean.setRemarks(request.getParameter("remarks"));

                String result = admin.addRecord(bean);

                if ("FAIL".equals(result) || result.contains("INVALID"))
                    response.sendRedirect("error.html");
                else
                    response.sendRedirect("success.html");

            } else if ("viewRecord".equals(operation)) {

                PharmacyBean bean = admin.viewRecord(
                    request.getParameter("medicineName"),
                    new SimpleDateFormat("yyyy-MM-dd")
                        .parse(request.getParameter("purchaseDate"))
                );

                if (bean == null)
                    request.setAttribute("msg",
                        "No matching records exists! Please try again!");
                else
                    request.setAttribute("pharmacyBean", bean);

                request.getRequestDispatcher("displayMedicine.jsp")
                       .forward(request, response);

            } else if ("viewAllRecords".equals(operation)) {

                List<PharmacyBean> list = admin.viewAllRecords();
                request.setAttribute("medicineList", list);
                request.getRequestDispatcher("displayAllMedicines.jsp")
                       .forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();   // 👈 MUST ADD
            response.sendRedirect("error.html");
        }

    }
}
