package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import dao.FeePaymentDAO;
import model.FeePayment;

public class ReportServlet extends HttpServlet {

    // POST → Overdue + Not Paid
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            String type = req.getParameter("type");
            FeePaymentDAO dao = new FeePaymentDAO();

            if ("overdue".equals(type)) {

                List<FeePayment> list = dao.getOverduePayments();
                req.setAttribute("list", list);

            } else if ("notpaid".equals(type)) {

                String from = req.getParameter("fromDate");
                String to = req.getParameter("toDate");

                List<FeePayment> list = dao.getNotPaid(from, to);
                req.setAttribute("list", list);
            }

            req.setAttribute("type", type);
            req.getRequestDispatcher("report_result.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET → TOTAL COLLECTION (NO DATE)
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            FeePaymentDAO dao = new FeePaymentDAO();

            double total = dao.getTotalCollectionAll();

            req.setAttribute("type", "total");
            req.setAttribute("total", total);

            req.getRequestDispatcher("report_result.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}