package controller;

import dao.ProductDAO;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "productsServlet", value = "/products")
public class productsServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                showFormCreate(request, response);
                break;
            case "update":
                showFormUpdate(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "search" :
                searchByName(request,response);
                break;
            default:
                showListProduct(request, response);
        }
    }

    private void showListProduct(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = null;
        try {
            products = productDAO.findAll();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormUpdate(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("productId"));
        Product handler = null;
        try {
            handler = productDAO.findById(id);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("product", handler);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/view.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        try {
            productDAO.deleteById(productId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<Product> list = null;
        try {
            list = productDAO.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("products", list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                creatProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;

            default:
                showListProduct(request, response);
        }
    }
    private void updateProduct(HttpServletRequest request,HttpServletResponse response){
        String productName = request.getParameter("productName");
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));
        int productQuantity =Integer.parseInt(request.getParameter("productQuantity"));
        String productColor = request.getParameter("productColor");
        String productDescription = request.getParameter("productDescription");
        int productCategoryId = Integer.parseInt(request.getParameter("productCategoryId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product handler = new Product(productId,productName,productPrice,productQuantity,productColor,productDescription,productCategoryId);
        try {
            productDAO.updateById(productId,handler);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        showListProduct(request,response);
    }
    private void creatProduct(HttpServletRequest request,HttpServletResponse response){
        String productName = request.getParameter("productName");
        float productPrice = Float.parseFloat(request.getParameter("productPrice"));
        int productQuantity =Integer.parseInt(request.getParameter("productQuantity"));
        String productColor = request.getParameter("productColor");
        String productDescription = request.getParameter("productDescription");
        int productCategoryId = Integer.parseInt(request.getParameter("productCategoryId"));
        Product handler = new Product(productName,productPrice,productQuantity,productColor,productDescription,productCategoryId);
        try {
            productDAO.add(handler);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        showListProduct(request,response);
    }
    private void searchByName(HttpServletRequest request,HttpServletResponse response){
        String name = request.getParameter("productName");
        List <Product> list = null;
        try {
            list = productDAO.findByName(name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("products",list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
