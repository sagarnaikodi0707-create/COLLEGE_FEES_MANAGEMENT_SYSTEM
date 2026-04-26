package dao;

import java.sql.*;
import java.util.*;
import model.FeePayment;

public class FeePaymentDAO {

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/college_fee_db",
            "root",
            "sagar@6858"
        );
    }

    // ADD
    public void addPayment(FeePayment f) throws Exception {
        Connection con = getConnection();

        String sql = "INSERT INTO FeePayments(StudentID, StudentName, PaymentDate, Amount, Status) VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, f.getStudentId());
        ps.setString(2, f.getStudentName());
        ps.setDate(3, new java.sql.Date(f.getPaymentDate().getTime()));
        ps.setDouble(4, f.getAmount());
        ps.setString(5, f.getStatus());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    // DISPLAY
    public List<FeePayment> getAllPayments() throws Exception {
        List<FeePayment> list = new ArrayList<>();

        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM FeePayments");

        while (rs.next()) {
            FeePayment f = new FeePayment();
            f.setPaymentId(rs.getInt("PaymentID"));
            f.setStudentId(rs.getInt("StudentID"));
            f.setStudentName(rs.getString("StudentName"));
            f.setPaymentDate(rs.getDate("PaymentDate"));
            f.setAmount(rs.getDouble("Amount"));
            f.setStatus(rs.getString("Status"));
            list.add(f);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // DELETE
    public void deletePayment(int id) throws Exception {
        Connection con = getConnection();

        PreparedStatement ps = con.prepareStatement(
            "DELETE FROM FeePayments WHERE PaymentID=?"
        );

        ps.setInt(1, id);
        ps.executeUpdate();

        ps.close();
        con.close();
    }

    // UPDATE
    public void updatePayment(FeePayment f) throws Exception {
        Connection con = getConnection();

        String sql = "UPDATE FeePayments SET StudentID=?, StudentName=?, PaymentDate=?, Amount=?, Status=? WHERE PaymentID=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, f.getStudentId());
        ps.setString(2, f.getStudentName());
        ps.setDate(3, new java.sql.Date(f.getPaymentDate().getTime()));
        ps.setDouble(4, f.getAmount());
        ps.setString(5, f.getStatus());
        ps.setInt(6, f.getPaymentId());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    // GET BY ID
    public FeePayment getPaymentById(int id) throws Exception {
        FeePayment f = null;

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM FeePayments WHERE PaymentID=?"
        );

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            f = new FeePayment();
            f.setPaymentId(rs.getInt("PaymentID"));
            f.setStudentId(rs.getInt("StudentID"));
            f.setStudentName(rs.getString("StudentName"));
            f.setPaymentDate(rs.getDate("PaymentDate"));
            f.setAmount(rs.getDouble("Amount"));
            f.setStatus(rs.getString("Status"));
        }

        rs.close();
        ps.close();
        con.close();

        return f;
    }

    // OVERDUE STUDENTS
    public List<FeePayment> getOverduePayments() throws Exception {
        List<FeePayment> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM FeePayments WHERE Status='Overdue'"
        );

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            FeePayment f = new FeePayment();
            f.setPaymentId(rs.getInt("PaymentID"));
            f.setStudentName(rs.getString("StudentName"));
            f.setAmount(rs.getDouble("Amount"));
            f.setStatus(rs.getString("Status"));
            list.add(f);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

    // NOT PAID IN DATE RANGE
    public List<FeePayment> getNotPaid(String from, String to) throws Exception {
        List<FeePayment> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM FeePayments WHERE PaymentDate NOT BETWEEN ? AND ?"
        );

        ps.setString(1, from);
        ps.setString(2, to);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            FeePayment f = new FeePayment();
            f.setPaymentId(rs.getInt("PaymentID"));
            f.setStudentName(rs.getString("StudentName"));
            f.setAmount(rs.getDouble("Amount"));
            f.setStatus(rs.getString("Status"));
            list.add(f);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

    // TOTAL COLLECTION (ALL TIME) ✅ FIXED
    public double getTotalCollectionAll() throws Exception {
        double total = 0;

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT SUM(Amount) FROM FeePayments"
        );

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            total = rs.getDouble(1);
        }

        rs.close();
        ps.close();
        con.close();

        return total;
    }
}