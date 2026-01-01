<%@ page language="java" %>
<html>
<head><title>View Medicine</title></head>
<body>

<form action="MainServlet" method="post">
  <input type="hidden" name="operation" value="viewRecord"/>

  Medicine Name: <input type="text" name="medicineName"/><br><br>
  Purchase Date: <input type="date" name="purchaseDate"/><br><br>

  <input type="submit" value="View"/>
</form>

</body>
</html>
