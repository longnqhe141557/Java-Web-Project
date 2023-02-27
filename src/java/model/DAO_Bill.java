package model;

import entity.Bill;
import entity.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO_Bill {

    Connection conn = null;
    DBConnect dbConn;

    public DAO_Bill(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbConn = dbconn;
    }

    public int addBill(Bill bill) {
        int n = 0;
        String sql = "insert into  Bill(oID, dateCreate, cname, cphone, cAddress, total) values('" + bill.getoID() + "', '" + bill.getDateCreate() + "', '" + bill.getCname() + "', '" + bill.getCphone() + "', '" + bill.getcAddress() + "', '" + bill.getTotal() + "')";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void displayAllBill() {
        String sql = "select * from Bill";
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            //ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
                String oID = rs.getString(1);
                String dateCreate = rs.getString(2);
                String cname = rs.getString(3);
                String cphone = rs.getString(4);
                String cAddress = rs.getString(5);
                Double total = rs.getDouble(6);
                int status = rs.getInt(7);
                int cid = rs.getInt(8);
                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
                System.out.println(bill);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAO_Bill dao = new DAO_Bill(dbconn);
        //int n = dao.addBill(new Bill("C6T3", "10/05/2021", "Lưu Công Thành", "0989917186", "Sóc Sơn- Hà Nội", 500, 1, 2));
        //if (n > 0) {
        // System.out.println("Đã thêm 1 hóa đơn thành công nhé!!!");
        // }
        dao.displayAllBill();
    }
}
