/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO_Customer;
import model.DBConnect;

/**
 *
 * @author User
 */
@WebServlet(name = "ControllerCustomer", urlPatterns = {"/ControllerCustomer"})
public class ControllerCustomer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DBConnect dBConnect = new DBConnect();
        DAO_Customer dao = new DAO_Customer(dBConnect);
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "displayAll";
            }
            if (service.equals("displayAll")) {
                String sql = "select * from Customer";
                ResultSet rs = dBConnect.getData(sql);
                String titleTable = "Danh sách khách hàng";
                request.setAttribute("ketQua", rs);
                request.setAttribute("tieuDe", titleTable);
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/ViewCustomer.jsp");
                dis.forward(request, response);
            }
            if (service.equals("addCustomer")) {
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/addCustomer.jsp");
                dis.forward(request, response);
            }
            if (service.equals("added")) {
                String cname = request.getParameter("cname");
                String cphone = request.getParameter("cphone");
                String cAddress = request.getParameter("cAddress");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                Customer A = new Customer(cname, cphone, cAddress, username, password);
                dao.addCustomer(A);
                request.getRequestDispatcher("/ControllerCustomer?service=displayAll").forward(request, response);
            }
            if (service.equals("update")) {
                int cid = Integer.parseInt(request.getParameter("cid"));
                String sql = "select * from Customer where cid = " + cid;
                ResultSet rs = dBConnect.getData(sql);
                if (rs.next()) {
                    Customer cus = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                    request.setAttribute("customer", cus);
                    RequestDispatcher dis = request.getRequestDispatcher("/jsp/updateCustomer.jsp");
                    dis.forward(request, response);
                }
            }
            if (service.equals("updated")) {
                int customerID = Integer.parseInt(request.getParameter("customerID"));
                String customerName = request.getParameter("customerName");
                String customerPhone = request.getParameter("customerPhone");
                String customerAddress = request.getParameter("customerAddress");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                int status = Integer.parseInt(request.getParameter("status"));
                Customer customer = new Customer(customerID, customerName, customerPhone, customerAddress, username, password, status);
                dao.updateCustomer(customer);
                request.getRequestDispatcher("/ControllerCustomer?service=displayAll").forward(request, response);
            }
            if (service.equals("delete")) {
                int customerID = Integer.parseInt(request.getParameter("cid"));
                dao.deleteCustomer(customerID);
                request.getRequestDispatcher("/ControllerCustomer?service=displayAll").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCustomer.class.getName()).log(Level.SEVERE, null, ex);
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
