/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO_Category {

    Connection conn = null;
    DBConnect dbconn = new DBConnect();

    public DAO_Category(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn = dbconn;
    }

    public ArrayList<Category> getAllCategory() {
        ArrayList<Category> arr = new ArrayList<Category>();
        String sql = "select * from Category";
        ResultSet rs = dbconn.getData(sql);
        try {
            while (rs.next()) {
                Category cate = new Category(rs.getInt(1), rs.getString(2), rs.getInt(3));
                arr.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public int addCategory(Category obj) {
        int n = 0;
        String sql = "insert into Category([cateName], [status]) "
                + " values ('" + obj.getCateName() + "','" + obj.getStatus() + "')";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateCategory(Category cate) {
        int n = 0;
        String sql = "UPDATE [dbo].[Category]\n"
                + "   SET [cateName] = '" + cate.getCateName() + "'\n"
                + "      ,[status] = " + cate.getStatus() + "\n"
                + " WHERE cateID = " + cate.getCateID() + "";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(DAO_Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteCategory(String cateId) {
        int n = 0;
        String sql = "delete from Category where cateId=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cateId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return n;
    }

    public int updateCateName(int cateID, String cateName) {
        int n = 0;
        String sql = "UPDATE [dbo].[Category]\n"
                + "   SET [cateName] = '" + cateName + "'\n"
                + " WHERE cateID = " + cateID + "";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(DAO_Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateStatus(int cateID, int status) {
        int n = 0;
        String sql = "UPDATE [dbo].[Category]\n"
                + "   SET[status] = " + status + "\n"
                + " WHERE cateID = " + cateID + "";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(DAO_Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void displayAllCategory() {
        String sql = "select * from Category";
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String cateName = rs.getString(2);
                Category cate = new Category(cateName);
                System.out.println(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAO_Category dao = new DAO_Category(dbconn);
        /* int n = dao.addCategory(new Category("Nike Dunk Low UNC", 0));
       /* if (n > 0) {
            System.out.println("Đã thêm thành công 1 Category mới nhớ !!");
        } */
        dao.displayAllCategory();

    }

}
