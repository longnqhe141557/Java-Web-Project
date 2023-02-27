/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO_BillDetail;
import model.DBConnect;

/**
 *
 * @author User
 */
@WebServlet(name = "ControllerBillDetail", urlPatterns = {"/ControllerBillDetail"})
public class ControllerBillDetail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DBConnect dBConnect = new DBConnect();
        DAO_BillDetail dao = new DAO_BillDetail(dBConnect);
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "displayAll";
            }   
            if (service.equals("displayAll")) {
                String billID = request.getParameter("bid");
                /*     String sql = "select * from (select BillDetail.oID,BillDetail.pid, BillDetail.quantity, BillDetail.price, BillDetail.total, Bill.dateCreate, Bill.cname, Bill.cphone, Bill.cAddress, Bill.cid, Customer.username, Customer.password, Product.pname, Product.image, Product.description, Category.cateName  from BillDetail\n"
                        + "  inner join Bill on BillDetail.oID = Bill.oID\n"
                        + "  inner join Customer on Customer.cid = Bill.cid\n"
                        + "  inner join Product on Product.pid = BillDetail.pid\n"
                        + "  inner join Category on Category.cateID = Product.cateID) as T\n"
                        + "  where T.oID  = '"+billID+"'"; */
                String sql = "select BillDetail.oID,BillDetail.pid, BillDetail.quantity, BillDetail.price, BillDetail.total, Bill.dateCreate, Bill.cname, Bill.cphone, Bill.cAddress, Bill.cid, Customer.username, Customer.password, Product.pname, Product.image, Product.description, Category.cateName  from BillDetail\n"
                        + "  inner join Bill on BillDetail.oID = Bill.oID\n"
                        + "  inner join Customer on Customer.cid = Bill.cid\n"
                        + "  inner join Product on Product.pid = BillDetail.pid\n"
                        + "  inner join Category on Category.cateID = Product.cateID";
                ResultSet rs = dBConnect.getData(sql);
                String titleTable = "Chi tiết hóa đơn";
                request.setAttribute("ketQua", rs);
                request.setAttribute("tieuDe", titleTable);
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/viewBillDetail.jsp");
                dis.forward(request, response);
            }
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
