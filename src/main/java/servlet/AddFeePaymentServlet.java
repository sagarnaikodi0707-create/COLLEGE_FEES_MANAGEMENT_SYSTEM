package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.text.SimpleDateFormat;

import dao.FeePaymentDAO;
import model.FeePayment;

public class AddFeePaymentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");

        try {
            FeePayment f = new FeePayment();

            // DEBUG
            System.out.println("Add Servlet Called");

            // GET VALUES
            f.setStudentId(Integer.parseInt(req.getParameter("studentId")));
            f.setStudentName(req.getParameter("studentName"));

            String dateStr = req.getParameter("paymentDate");
            System.out.println("Date Received: " + dateStr);

            // FIX DATE FORMAT
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            f.setPaymentDate(sdf.parse(dateStr));

            f.setAmount(Double.parseDouble(req.getParameter("amount")));
            f.setStatus(req.getParameter("status"));

            // SAVE
            FeePaymentDAO dao = new FeePaymentDAO();
            dao.addPayment(f);

            System.out.println("Data Inserted Successfully");

            // REDIRECT
            res.sendRedirect("display");

        } catch (Exception e) {
            e.printStackTrace();

            // SHOW ERROR IN BROWSER
            PrintWriter out = res.getWriter();
            out.println("<h3 style='color:red'>ERROR OCCURRED:</h3>");
            out.println(e.getMessage());
        }
    }
}