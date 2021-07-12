package dao;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    SQLConnection sqlConnection = new SQLConnection();
    public static final String GET_PRODUCT_BY_NAME = "select * from products where productName like ?";
    public static final String GET_PRODUCT_BY_ID = "select * from products where productId = ?";
    public final String GET_ALL_PRODUCT = "select productId, productName, productPrice, productQuantity,productColor,productDescription,category.categoryName, category.categoryId from products join category on products.productCategoryId = category.categoryId;";
    public final String DEL_PRODUCT_BY_ID = "delete from products where productId = ?;";
    public final String UPDATE_PRODUCT_BY_ID = "update products set productName = ?,productPrice = ?,productQuantity = ?,productColor=?,productDescription=? ,productCategoryId = ? where productId = ?;";
    public final String ADD_PRODUCT = "insert into products (productName,productPrice,productQuantity,productColor,productDescription,productCategoryId) values (?,?,?,?,?,?)";



    public List<Product> findAll() throws SQLException, ClassNotFoundException {
        List<Product> results = new ArrayList<>();
        Connection conn = sqlConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(GET_ALL_PRODUCT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("productId");
                String productName = rs.getString("productName");
                float productPrice = rs.getFloat("productPrice");
                int productQuantity = rs.getInt("productQuantity");
                String productColor = rs.getString("productColor");
                String productDescription = rs.getString("productDescription");
                int productCategoryId = Integer.parseInt(rs.getString("categoryId"));
                results.add(new Product(productId, productName, productPrice, productQuantity, productColor, productDescription, productCategoryId));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return results;
    }


    public void add(Product product) throws SQLException, ClassNotFoundException {
        Connection connection = sqlConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(ADD_PRODUCT);
            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getProductPrice());
            ps.setInt(3, product.getProductQuantity());
            ps.setString(4, product.getProductColor());
            ps.setString(5, product.getProductDescription());
            ps.setInt(6, product.getProductCategoryId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void updateById(int id, Product product) throws SQLException, ClassNotFoundException {
        Connection connection = sqlConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT_BY_ID);
            ps.setInt(7, product.getProductId());
            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getProductPrice());
            ps.setInt(3, product.getProductQuantity());
            ps.setString(4, product.getProductColor());
            ps.setString(5, product.getProductDescription());
            ps.setInt(6, product.getProductCategoryId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void deleteById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = sqlConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(DEL_PRODUCT_BY_ID);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public Product findById(int id) throws SQLException, ClassNotFoundException {
        Product product = null;
        Connection connection = sqlConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("productId");
                String productName = rs.getString("productName");
                double productPrice = rs.getDouble("productPrice");
                int productQuantity = rs.getInt("productQuantity");
                String productColor = rs.getString("productColor");
                String productDescription = rs.getString("productDescription");
                int productCategoryId = Integer.parseInt(rs.getString("productCategoryId"));
                product = new Product(productId, productName, productPrice, productQuantity, productColor, productDescription, productCategoryId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }


    public List<Product> findByName(String productName) throws SQLException, ClassNotFoundException {
        List<Product> list = new ArrayList<>();
        Connection connection = sqlConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_NAME);
            statement.setString(1, "%" + productName + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("productId");
                String productName1 = rs.getString("productName");
                double productPrice = rs.getDouble("productPrice");
                int productQuantity = rs.getInt("productQuantity");
                String productColor = rs.getString("productColor");
                String productDescription = rs.getString("productDescription");
                int productCategoryId = Integer.parseInt(rs.getString("productCategoryId"));
                Product   product = new Product(productId, productName1, productPrice, productQuantity, productColor, productDescription, productCategoryId);
                list.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
