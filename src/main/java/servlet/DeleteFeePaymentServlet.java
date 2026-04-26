package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import dao.FeePaymentDAO;

public class DeleteFeePaymentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            new FeePaymentDAO().deletePayment(id);
            res.sendRedirect("display");

        } catch(Exception e) { e.printStackTrace(); }
    }
}