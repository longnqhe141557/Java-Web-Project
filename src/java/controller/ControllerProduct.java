package controller;

import entity.Bill;
import entity.BillDetail;
import entity.Product;
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
import javax.servlet.http.HttpSession;
import model.DAO_Bill;
import model.DAO_BillDetail;
import model.DAO_Product;
import model.DBConnect;

/**
 *
 * @author User
 */
@WebServlet(name = "ControllerProduct", urlPatterns = {"/ControllerProduct"})
public class ControllerProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DBConnect dBConnect = new DBConnect();
        DAO_Product dao = new DAO_Product(dBConnect);
        DAO_Bill dao1 = new DAO_Bill(dBConnect);
        DAO_BillDetail dao2 = new DAO_BillDetail(dBConnect);
        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");
            if (service == null) {
                service = "displayAll";
            }
            if (service.equals("displayAll")) {
                String sql = "select * from Product order by cateID";
                ResultSet rs = dBConnect.getData(sql);
                String titleTable = "DANH SÁCH SẢN PHẨM NÈ MẤY MÁ ÔI!";
                request.setAttribute("ketQua", rs);
                request.setAttribute("tieuDe", titleTable);
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/ViewProduct.jsp");
                dis.forward(request, response);
            }
            if (service.equals("update")) {
                String ProductID = request.getParameter("pid");
                String sql = "select * from Product where pid = '" + ProductID + "'";
                ResultSet rs = dBConnect.getData(sql);
                String Sql1 = "select * from Category";
                ResultSet rs1 = dBConnect.getData(Sql1);
                if (rs.next()) {
                    Product product = new Product(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5),  rs.getInt(6), rs.getInt(7));
                    request.setAttribute("product", product);
                    request.setAttribute("ketQua1", rs1);
                    request.setAttribute("ketQua", rs);
                    RequestDispatcher dis = request.getRequestDispatcher("/jsp/updateProduct.jsp");
                    dis.forward(request, response);
                }
            }
            if (service.equals("updated")) {
                String proID = request.getParameter("productID");
                String proName = request.getParameter("productName");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                double price = Double.parseDouble(request.getParameter("price"));
                String image = request.getParameter("image");
           
                int status = Integer.parseInt(request.getParameter("status"));
                int cateID = Integer.parseInt(request.getParameter("cate"));
                Product pro = new Product(proID, proName, quantity, price, image, status, cateID);
                dao.updateProduct(pro);
                RequestDispatcher dis = request.getRequestDispatcher("/ControllerProduct?service=displayAll");
                dis.forward(request, response);
            }
            if (service.equals("delete")) {
                String productID = request.getParameter("pid");
                dao.deleteProduct(productID);
                RequestDispatcher dis = request.getRequestDispatcher("/ControllerProduct?service=displayAll");
                dis.forward(request, response);
            }
            if (service.equals("addProduct")) {
                String sql = "select * from Category";
                ResultSet rs = dBConnect.getData(sql);
                request.setAttribute("ketQua", rs);
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/addProduct.jsp");
                dis.forward(request, response);
            }
            if (service.equals("added")) {
                String pid = request.getParameter("ProductID");
                String name = request.getParameter("Name");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                double price = Double.parseDouble(request.getParameter("Price"));
                String image = request.getParameter("image");
                String description = request.getParameter("description");
                int status = Integer.parseInt(request.getParameter("st"));
                int cateID = Integer.parseInt(request.getParameter("Cate"));
                Product pro = new Product(pid, name, quantity, price, image,  status, cateID);
                dao.addProduct(pro);
                RequestDispatcher dis = request.getRequestDispatcher("/ControllerProduct?service=displayAll");
                dis.forward(request, response);
            }
            if (service.equals("add2Cart")) {
                String productID = request.getParameter("pid");
                String sql = "select * from Product where pid = '" + productID + "'";
                ResultSet rs = dBConnect.getData(sql);
                if (rs.next()) {
                    //Đây là cái sản phẩm của mình tìm thấy
                    Product product = new Product(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5),  rs.getInt(6), rs.getInt(7));
                    HttpSession sesssion = request.getSession();
                    //Đang lấy lại cái sản phẩm. qua session
                    Product p = (Product) sesssion.getAttribute(productID);
                    if (p == null) {
                        product.setQuantity(1);
                        //Đẩy cái product p lên trên session
                        sesssion.setAttribute(productID, product);
                    } else {
                        //Đang lấy lại cái sản phẩm. qua session
                        Product pro = (Product) sesssion.getAttribute(productID);
                        int count = pro.getQuantity();
                        product.setQuantity(count + 1);
                        sesssion.setAttribute(productID, product);
                    }
                    request.setAttribute("title", "Bạn đã thêm thành công 1 đôi " + product.getPname() + " với mã sản phẩm là:" + product.getPid() + " tới phần Giỏ hàng.");
                    request.getRequestDispatcher("/jsp/add2Cart.jsp").forward(request, response);
                }
            }
            if (service.equals("showCart")) {
                request.getRequestDispatcher("/jsp/showCart.jsp").forward(request, response);
            }
            if (service.equals("remove")) {
                String id = request.getParameter("id1");
                HttpSession sesssion = request.getSession();
                sesssion.removeAttribute(id);
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/showCart.jsp");
                dis.forward(request, response);
            }
            if (service.equals("checkout")) {
                // String orderID = dao.randomCapchar();
                // request.setAttribute("orderID", orderID);
                request.getRequestDispatcher("/jsp/checkout.jsp").forward(request, response);
            }
            if (service.equals("checkouted")) {
                //  String orderID = request.getParameter("oid");
                String orderID = dao.randomCapchar();
                String date = java.time.LocalDateTime.now().toString();
                String cname = request.getParameter("name");
                String cphone = request.getParameter("phone");
                String cAddress = request.getParameter("address");
                Double total = Double.parseDouble(request.getParameter("total"));
                Bill bill = new Bill(orderID, date, cname, cphone, cAddress, total);
                dao1.addBill(bill);
                HttpSession session = request.getSession();
                java.util.Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    String id = em.nextElement().toString();
                    Product pro = (Product) session.getAttribute(id);
                    int quantity = pro.getQuantity();
                    String pid = pro.getPid();
                    double price = (double) pro.getPrice();
                    double total1 = quantity * price;
                    BillDetail billDetail = new BillDetail(pid, orderID, quantity, price, total1);
                    dao2.addBillDetail(billDetail);
                }
                session.invalidate();
                response.sendRedirect("http://localhost:8080/OiDoiOi_Session_7_7_2021/bill");
            }
            if (service.equals("GetProductByCateID")) {
                String cateID = request.getParameter("cid");
                String sql = "select * from Category";
                String sql1 = "select * from Product where cateID = '" + cateID + "'";
                ResultSet rs = dBConnect.getData(sql);
                ResultSet rs1 = dBConnect.getData(sql1);
                request.setAttribute("category", rs);
                request.setAttribute("product", rs1);
                request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
            }
            if (service.equals("removeAll")) {
                HttpSession session = request.getSession();
                session.invalidate();
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/showCart.jsp");
                dis.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
