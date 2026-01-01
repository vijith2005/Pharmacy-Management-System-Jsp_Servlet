<%@ page import="com.wipro.pharmacy.bean.PharmacyBean" %>
<%
  PharmacyBean bean = (PharmacyBean) request.getAttribute("pharmacyBean");
  String msg = request.getParameter("msg");
%>

<html>
<body>

<% if (bean != null) { %>
  <h3>Medicine Details</h3>
  Record ID: <%= bean.getRecordId() %><br>
  Medicine Name: <%= bean.getMedicineName() %><br>
  Supplier Name: <%= bean.getSupplierName() %><br>
  Purchase Date: <%= bean.getPurchaseDate() %><br>
  Quantity: <%= bean.getQuantity() %><br>
  Price: <%= bean.getPrice() %><br>
  Remarks: <%= bean.getRemarks() %><br>

<% } else { %>
  <h3>No matching records exists! Please try again!</h3>
<% } %>

</body>
</html>
