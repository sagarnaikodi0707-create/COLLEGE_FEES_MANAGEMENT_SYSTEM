package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.text.SimpleDateFormat;

import dao.FeePaymentDAO;
import model.FeePayment;

public class UpdateFeePaymentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            FeePayment f = new FeePayment();

            f.setPaymentId(Integer.parseInt(req.getParameter("paymentId")));
            f.setStudentId(Integer.parseInt(req.getParameter("studentId")));
            f.setStudentName(req.getParameter("studentName"));

            String dateStr = req.getParameter("paymentDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            f.setPaymentDate(sdf.parse(dateStr));

            f.setAmount(Double.parseDouble(req.getParameter("amount")));
            f.setStatus(req.getParameter("status"));

            FeePaymentDAO dao = new FeePaymentDAO();
            dao.updatePayment(f);

            res.sendRedirect("display");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}