<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Medicines</title>
    <style>
        table {
            border-collapse: collapse;
            width: 70%;
        }
        th, td {
            border: 1px solid black;
            padding: 8px 12px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        a {
            text-decoration: none;
            color: blue;
        }
    </style>
</head>
<body>
    <h2>All Medicine Records</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>

        <%
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                // Load Oracle JDBC Driver
                Class.forName("oracle.jdbc.driver.OracleDriver");

                // Connect to Oracle DB
                con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "admin"
                );

                // Execute query
                st = con.createStatement();
                rs = st.executeQuery("SELECT * FROM medicine");

                while(rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("name") %></td>
            <td><%= rs.getDouble("price") %></td>
            <td><%= rs.getInt("quantity") %></td>
        </tr>
        <%
                }
            } catch(Exception e) {
                out.println("<tr><td colspan='4'>Error: " + e.getMessage() + "</td></tr>");
            } finally {
                // Close resources
                try { if(rs != null) rs.close(); } catch(Exception e) {}
                try { if(st != null) st.close(); } catch(Exception e) {}
                try { if(con != null) con.close(); } catch(Exception e) {}
            }
        %>
    </table>

    <br>
    <a href="menu.html">Back to Menu</a>
</body>
</html>
