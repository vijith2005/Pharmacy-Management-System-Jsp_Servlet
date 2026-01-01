<%@ page import="java.util.*,com.wipro.pharmacy.bean.PharmacyBean" %>

<%
  List<PharmacyBean> list =
      (List<PharmacyBean>) request.getAttribute("medicineList");
%>

<html>
<body>

<% if (list == null || list.isEmpty()) { %>
  <h3>No records available!</h3>
<% } else { %>

<table border="1">
<tr>
  <th>Record ID</th>
  <th>Medicine Name</th>
  <th>Supplier</th>
  <th>Date</th>
  <th>Quantity</th>
  <th>Price</th>
</tr>

<% for (PharmacyBean b : list) { %>
<tr>
  <td><%= b.getRecordId() %></td>
  <td><%= b.getMedicineName() %></td>
  <td><%= b.getSupplierName() %></td>
  <td><%= b.getPurchaseDate() %></td>
  <td><%= b.getQuantity() %></td>
  <td><%= b.getPrice() %></td>
</tr>
<% } %>

</table>

<% } %>

</body>
</html>
