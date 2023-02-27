package model;

import entity.Customer;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO_Customer {

    Connection conn = null;

    public DAO_Customer(DBConnect dbconn) {
        conn = dbconn.con;
    }

    public int addCustomer(Customer obj) {
        int n = 0;
        String sql = " INSERT INTO [dbo].[Customer]\n"
                + "           ([cname]\n"
                + "           ,[cphone]\n"
                + "           ,[cAddress]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[status])\n"
                + "     VALUES ('" + obj.getCname() + "','" + obj.getCphone() + "','" + obj.getcAddress() + "','" + obj.getUsername()
                + "','" + obj.getPassword() + "','" + obj.getStatus() + "')";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void displayAllCustomer() {
        String sql = "select * from Customer";
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String cname = rs.getString(2);
                String cphone = rs.getString(3);
                String cAddress = rs.getString(4);
                String username = rs.getString(5);
                String password = rs.getString(6);
                Customer cus = new Customer(cname, cphone, cAddress, username, password);
                System.out.println(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int updateCustomer(Customer cus) {
        int n = 0;
        String sql = "update customer set cname=?,cAddress=?,cphone=?,username=?,password=?,status=? where cid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCname());
            pre.setString(2, cus.getcAddress());
            pre.setString(3, cus.getCphone());
            pre.setString(4, cus.getUsername());
            pre.setString(5, cus.getPassword());
            pre.setInt(6, cus.getStatus());
            pre.setInt(7, cus.getCid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return n;
    }

    public int deleteCustomer(int cid) {
        int n = 0;
        String sql = "delete from customer where cid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return n;
    }

    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAO_Customer dao = new DAO_Customer(dbconn);
        /* int n = dao.addCustomer(new Customer("Lưu Công Thành", "0989917186", "Sóc Sơn- Hà Nội", "ThanhLC", "suckmydickbae2308", 0));
        if (n > 0) {
            System.out.println("Đã thêm thành công 1 khách hàng mới nhớ !!!!");
        } */
        //dao.displayAllCustomer();
    }
}
