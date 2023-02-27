package controller;

import entity.Bill;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO_Bill;
import model.DAO_Product;
import model.DBConnect;

/**
 *
 * @author User
 */
@WebServlet(name = "ControllerBill", urlPatterns = {"/ControllerBill"})
public class ControllerBill extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DBConnect dBConnect = new DBConnect();
        DAO_Bill dao = new DAO_Bill(dBConnect);
        DAO_Product dao1 = new DAO_Product(dBConnect);
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "displayAll";
            }
            if (service.equals("displayAll")) {
                String sql = "select * from Bill";
                ResultSet rs = dBConnect.getData(sql);
                String titleTable = "Danh sách hóa đơn";
                request.setAttribute("ketQua", rs);
                request.setAttribute("tieuDe", titleTable);
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/viewBill.jsp");
                dis.forward(request, response);
            }
            if (service.equals("viewdetail")) {
                String orderID = request.getParameter("bid");
                String sql = "select * from   (select BillDetail.oID, BillDetail.pid, BillDetail.quantity, BillDetail.price, BillDetail.total, Bill.dateCreate, Bill.cname, Bill.cphone, Bill.cAddress, Product.pname, Product.image, Product.description, Category.cateName\n"
                        + "		  from BillDetail\n"
                        + "		  inner join Bill on BillDetail.oID = Bill.oID\n"
                        + "		  inner join Product on Product.pid = BillDetail.pid\n"
                        + "		  inner join Category on Category.cateID = Product.cateID) as T\n"
                        + "		  where T.oID = '" + orderID + "'";
                String sql1 = "select * from Bill where oID = '"+orderID+"'";
                ResultSet rs = dBConnect.getData(sql);
                ResultSet rs1 = dBConnect.getData(sql1);
                String titleTable = "Chi tiết hóa đơn";
                request.setAttribute("ketQua", rs);
                request.setAttribute("ketQua1", rs1);
                request.setAttribute("tieuDe", titleTable);
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/viewBillDetail.jsp");
                dis.forward(request, response);
            }
            if (service.equals("addBill")) {
                request.getRequestDispatcher("/jsp/addBill.jsp").forward(request, response);
            }
            if (service.equals("added")) {
                String orderID = request.getParameter("oid");
                String date = java.time.LocalDateTime.now().toString();
                String cname = request.getParameter("name");
                String cphone = request.getParameter("phone");
                String cAddress = request.getParameter("address");
                Double total = Double.parseDouble(request.getParameter("total"));
                Bill bill = new Bill(orderID, date, cname, cphone, cAddress, total);
                dao.addBill(bill);
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
