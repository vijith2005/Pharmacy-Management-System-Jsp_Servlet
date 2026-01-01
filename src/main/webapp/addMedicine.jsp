<%@ page language="java" %>
<html>
<head><title>Add Medicine</title></head>
<body>

<form action="MainServlet" method="post">
  <input type="hidden" name="operation" value="newRecord"/>

  Medicine Name: <input type="text" name="medicineName"/><br><br>
  Supplier Name: <input type="text" name="supplierName"/><br><br>
  Purchase Date: <input type="date" name="purchaseDate"/><br><br>
  Quantity: <input type="text" name="quantity"/><br><br>
  Price: <input type="text" name="price"/><br><br>
  Remarks: <input type="text" name="remarks"/><br><br>

  <input type="submit" value="Add Medicine"/>
</form>

</body>
</html>
