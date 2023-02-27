package model;

import entity.Customer;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO_Product {

    Connection conn = null;
    DBConnect dbConn;

    public DAO_Product(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbConn = dbconn;
    }

    public int addProduct(Product pro) {
        int n = 0;
        String preSql = "insert into product(pid, pname, quantity, price, image, status, cateID) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setString(2, pro.getPname());
            pre.setInt(3, pro.getQuantity());
            pre.setDouble(4, pro.getPrice());
            pre.setString(5, pro.getImage());
            pre.setInt(6, pro.getStatus());
            pre.setInt(7, pro.getCateID());
            pre.setString(1, pro.getPid());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateProduct(Product pro) {
        int n = 0;
        String preSql = "update product set pname=?, quantity=?, price=?, image=?, status=?, cateID=? where pid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setString(1, pro.getPname());
            pre.setInt(2, pro.getQuantity());
            pre.setDouble(3, pro.getPrice());
            pre.setString(4, pro.getImage());
            pre.setInt(5, pro.getStatus());
            pre.setInt(6, pro.getCateID());
            pre.setString(7, pro.getPid());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Product.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public String randomCapchar() {
        String root = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        String capcha = "";
        int length = root.length();// = 62
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            int index = r.nextInt(length);//tra ve index bat ki nam trong khoang 0 - 61
            capcha = capcha + root.charAt(index);
        }
        return capcha;
    }

    public int updateQuantity(String pid, int quantity) {
        int n = 0;
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET \n"
                + "   [quantity] = " + quantity + "\n"
                + " WHERE [pid] = " + pid + "";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(DAO_Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updatePrice(String pid, double price) {
        int n = 0;
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [price] = " + price + "\n"
                + " WHERE [pid] = " + pid + "";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(DAO_Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int changeStatus(String pid, int status) {
        int n = 0;
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [status] = " + status + "\n"
                + " WHERE [pid] = " + pid + "";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(DAO_Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void displayAllProduct() {
        String sql = "select * from Product";
        try {
            //Statement state=conn.createStatement(); default
            //TYPE_FORWARD_ONLY: default --> pointer top --> down only
            //TYPE_SCROLL_SENSITIVE: scroll; thread safe
            //CONCUR_READ_ONLY: default, can't change ResultSet
            //CONCUR_UPDATABLE: can change resultset --> database
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            //ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
//                rs.getDataType(index, fieldName);
//                dataType is dataType of filedName
                String pid = rs.getString("pid");//rs.getString(1)
                String pname = rs.getString(2);//rs.getString("pname")
                int quantity = rs.getInt("quantity");// rs.getInt(3)
                double price = rs.getDouble(4);
                String image = rs.getString(5);
                int status = rs.getInt(6), cateID = rs.getInt(7);
                Product pro = new Product(pid, pname, quantity, price, image, status, cateID);
                System.out.println(pro);

            }
            //PreparedStatement pre=conn.prepareStatement(sql, 0, 0);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void allRs() {
        String sql = "select cateID from Category where cateName = 'C01'";
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int cateID = rs.getInt(1);
                System.out.println(cateID);
                // TRA VE 1
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getCateID(String cateName) {
        String sql = "select cateID from Category where cateName = '" + cateName + "'";
        int cateID = 0;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                cateID = rs.getInt(1);
                //System.out.println(cateID);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cateID;

    }

    public ResultSet searchByName(String name) {
        String sql = "select * from Product where pname like '" + name + "%'";
        return dbConn.getData(sql);
    }

    public ResultSet searchByPrice(double from, double to) {
        String sql = "select * from Product where price between " + from + " and " + to + " ";
        return dbConn.getData(sql);
    }

    public int deleteProduct(String pid) {
        int n = 0;
        String sql = "delete from product where pid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return n;
    }

    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAO_Product dao = new DAO_Product(dbconn);
        dao.displayAllProduct();
    }

}
