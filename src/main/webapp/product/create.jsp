<%--
  Created by IntelliJ IDEA.
  User: MSI GAMING
  Date: 7/12/2021
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/products" method="post">
    <input type="hidden" name="action" value="create">
    <a>productName</a><br>
    <input type="text" name="productName"><br>
    <a>productPrice</a><br>
    <input type="text" name="productPrice"><br>
    <a>productQuantity</a><br>
    <input type="text" name="productQuantity"><br>
    <a>productColor</a><br>
    <input type="text" name="productColor"><br>
    <a>productDescription</a><br>
    <input type="text" name="productDescription"><br>
    <a>productCategoryID</a><br>
    <input type="text" name="productCategoryID"><br>
    <button>Create</button>
</form>
</body>
</html>
