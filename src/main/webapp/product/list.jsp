<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Danh Sách</h1>
<button type="submit">
    <a href="/products?action=create">
        Add New Product
    </a>
</button>
<form>
    <input type="hidden" name="action" value="search">
    <input type="text" name="key">
    <button>Search</button>
</form>
<table border="1px">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Price</td>
        <td>Quantity</td>
        <td>Color</td>
        <td>Description</td>
        <td>Category</td>
        <td>Action</td>
    </tr>
    <c:forEach items="${products}" var="product">

        <tr>
            <input type="hidden" name="action">
            <td>${product.productId}</td>
            <td>${product.productName}</td>
            <td>${product.productPrice}</td>
            <td>${product.productQuantity}</td>
            <td>${product.productColor}</td>
            <td>${product.productDescription}</td>
            <td>${product.productCategoryId}</td>

            <td>
                <a href="/products?action=update&id=${product.productId}" >Update</a>
                <a href="/products?action=delete&id=${product.productId}" id="delete">Delete</a></td>

        </tr>
    </c:forEach>

</table>


</body>
</html>