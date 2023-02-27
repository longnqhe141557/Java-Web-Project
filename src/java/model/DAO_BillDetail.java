/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.BillDetail;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAO_BillDetail {

    Connection conn = null;
    DBConnect dbconn = new DBConnect();

    public DAO_BillDetail(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn = dbconn;
    }


    public int addBillDetail(BillDetail obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[BillDetail]\n"
                + "           ([pid]\n"
                + "           ,[oID]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[total])\n"
                + "     VALUES ('" + obj.getPid() + "','" + obj.getoID() + "','" + obj.getQuantity() + "','" + obj.getPrice()
                + "','" + obj.getTotal() + "')";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_BillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateBillDetail(BillDetail billDe) {
        int n = 0;
        String sql = "UPDATE [dbo].[BillDetail]\n"
                + "   SET [quantity] = " + billDe.getQuantity() + "\n"
                + "      ,[price] = " + billDe.getPrice() + "\n"
                + "      ,[total] = " + billDe.getTotal() + "\n"
                + " WHERE [pid] = " + billDe.getPid() + "  and [oID] = " + billDe.getoID() + "";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(DAO_BillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateBillDetailQuantity(String pid, String oID, int quantity) {
        int n = 0;
        String sql = "UPDATE [dbo].[BillDetail]\n"
                + "   SET [quantity] = " + quantity + "\n"
                + " WHERE [pid] = " + pid + "  and [oID] = " + oID + "";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(DAO_BillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateBillDetailPrice(String pid, String oID, int price) {
        int n = 0;
        String sql = "UPDATE [dbo].[BillDetail]\n"
                + "   SET [price] = " + price + "\n"
                + " WHERE [pid] = " + pid + "  and [oID] = " + oID + "";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(DAO_BillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void displayAllBillDetail() {
        String sql = "select * from BillDetail";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String productID = rs.getString(1);
                String orderID = rs.getString(2);
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                double total = rs.getDouble(5);
                BillDetail b = new BillDetail(productID, orderID, quantity, price, total);
                System.out.println(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAO_BillDetail dao = new DAO_BillDetail(dbconn);
        /*   int n = dao.addBillDetail(new BillDetail("DD1391-102", "C6T3", 1, 297, 297));
        if (n > 0) {
            System.out.println("Đã thêm 1 chi tiết hóa đơn thành công nhớ !!!");
        } */
        dao.displayAllBillDetail();
    }
}
