
package model;

import entity.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO_Admin {
    Connection conn = null;
    DBConnect dbConn;
    public DAO_Admin(DBConnect dbconn){
        conn = dbconn.con;
        this.dbConn = dbconn;
    }
    public int addAdmin(Admin obj){
        int n=0;
        String preSql="insert into admin(username, password) "
        + " values (?,?)";
        try {
            PreparedStatement pre=conn.prepareStatement(preSql);
            pre.setString(1,obj.getUsername());
            pre.setString(2,obj.getPassword());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    public Admin checkLogin(String username, String password){
        try {
            String sql = "select * from Administrator where username = ? and pass = ? ";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {                
                Admin admin = new Admin(rs.getString(2), rs.getString(3));
                return admin;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAO_Admin dao = new DAO_Admin(dbconn);
        int n1 = dao.addAdmin(new Admin("longdeptrai1234", "140320000"));
        if(n1 > 0){
            System.out.println("ĐÃ THÊM THÀNH CÔNG ADMIN MỚI NHỚ!!");
        }
    }
    
}
