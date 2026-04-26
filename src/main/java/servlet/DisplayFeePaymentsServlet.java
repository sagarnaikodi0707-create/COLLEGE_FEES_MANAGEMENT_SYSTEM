package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import dao.FeePaymentDAO;
import model.FeePayment;

public class DisplayFeePaymentsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            FeePaymentDAO dao = new FeePaymentDAO();
            List<FeePayment> list = dao.getAllPayments();

            req.setAttribute("list", list);

            RequestDispatcher rd = req.getRequestDispatcher("feepaymentdisplay.jsp");
            rd.forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}