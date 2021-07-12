
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/products" method="post">
    <input type="hidden" name="action" value="edit">
    <a>productId</a>
    <input type="text" name="productId" value="${product.productId}"><br>
    <a>productName</a>
    <input type="text" name="productName" value="${product.productName}"><br>
    <a>productPrice</a>
    <input type="text" name="productPrice" value="${product.productPrice}"><br>
    <a>productQuantity</a>
    <input type="text" name="productQuantity" value="${product.productQuantity}"><br>
    <a>productColor</a>
    <input type="text" name="productColor" value="${product.productColor}"><br>
    <a>productDescription</a>
    <input type="text" name="productDescription" value="${product.productDescription}"><br>
    <a>productCategoryId</a>
    <input type="text" name="productCategoryId" value="${product.productCategoryId}"><br>
    <button>Edit</button>
</form>
<form action="/products" method="post">
    <input type="hidden" name="action" value="delete">
    <input type="hidden" name="productId" value="${product.productId}">
    <button>Delete</button>
</form>
</body>
</html>
