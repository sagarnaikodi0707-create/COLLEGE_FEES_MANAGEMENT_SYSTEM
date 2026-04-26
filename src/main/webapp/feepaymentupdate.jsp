<%@ page import="java.util.*,dao.FeePaymentDAO,model.FeePayment" %>

<%
String idParam = request.getParameter("id");

if(idParam == null){
    out.println("<h3 style='color:red'>Invalid Request</h3>");
    return;
}

int id = Integer.parseInt(idParam);

FeePaymentDAO dao = new FeePaymentDAO();
FeePayment f = null;

// Fetch the record
List<FeePayment> list = dao.getAllPayments();
for(FeePayment temp : list){
    if(temp.getPaymentId() == id){
        f = temp;
        break;
    }
}

if(f == null){
    out.println("<h3 style='color:red'>Record Not Found</h3>");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Update Fee Payment</title>

<style>
body {
    font-family: Arial;
    background: #f2f2f2;
}

.container {
    width: 400px;
    margin: auto;
    margin-top: 80px;
    background: white;
    padding: 20px;
    border-radius: 10px;
}

input, select {
    width: 100%;
    padding: 10px;
    margin: 10px 0;
}

button {
    width: 100%;
    padding: 10px;
    background: orange;
    color: white;
    border: none;
}
</style>
</head>

<body>

<div class="container">
<h2>Update Payment</h2>

<form action="update" method="post">

<input type="hidden" name="paymentId" value="<%=f.getPaymentId()%>">

Student ID:
<input type="number" name="studentId" value="<%=f.getStudentId()%>" required>

Name:
<input type="text" name="studentName" value="<%=f.getStudentName()%>" required>

Date:
<input type="date" name="paymentDate"
value="<%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(f.getPaymentDate())%>" required>

Amount:
<input type="number" step="0.01" name="amount" value="<%=f.getAmount()%>" required>

Status:
<select name="status">
    <option <%=f.getStatus().equals("Paid") ? "selected" : ""%>>Paid</option>
    <option <%=f.getStatus().equals("Overdue") ? "selected" : ""%>>Overdue</option>
</select>

<button type="submit">Update Payment</button>

</form>
</div>

</body>
</html>