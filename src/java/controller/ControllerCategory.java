/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO_Category;
import model.DBConnect;

/**
 *
 * @author User
 */
@WebServlet(name = "ControllerCategory", urlPatterns = {"/ControllerCategory"})
public class ControllerCategory extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DBConnect dBConnect = new DBConnect();
        DAO_Category dao = new DAO_Category(dBConnect);
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "displayAll";
            }
            if (service.equals("displayAll")) {
                String sql = "select * from Category";
                ResultSet rs = dBConnect.getData(sql);
                ArrayList<Category> arr = dao.getAllCategory();
                String titleTable = "List of Category";
                request.setAttribute("ketQua", rs);
                request.setAttribute("danhSach", arr);
                request.setAttribute("tieuDe", titleTable);
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/viewCategory.jsp");
                dis.forward(request, response);
            }
            if (service.equals("update")) {
                int cateID = Integer.parseInt(request.getParameter("id"));
                String sql = "select * from Category where cateid=" + cateID;
                ResultSet rs = dBConnect.getData(sql);
                if (rs.next()) {
                    Category cate = new Category(rs.getInt(1), rs.getString(2), rs.getInt(3));
                    request.setAttribute("cate", cate);
                    RequestDispatcher dis = request.getRequestDispatcher("/jsp/updateCategory.jsp");
                    dis.forward(request, response);
                }

            }
            if (service.equals("updated")) {
                int cateID = Integer.parseInt(request.getParameter("cateID"));
                String cateName = request.getParameter("cateName");
                int status = Integer.parseInt(request.getParameter("status"));
                Category catetoUpdate = new Category(cateID, cateName, status);
                dao.updateCategory(catetoUpdate);
            }
            if (service.equals("delete")) {
                String cateID = request.getParameter("id");
                dao.deleteCategory(cateID);
            }
            if (service.equals("addCategory")) {
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/addCategory.jsp");
                dis.forward(request, response);
            }
            if (service.equals("added")) {
                String cateName = request.getParameter("cateName");
                int status =  Integer.parseInt(request.getParameter("status"));
                Category cate = new Category(cateName, status);
                dao.addCategory(cate);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControllerCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
