<%@ page import="java.util.*,model.FeePayment" %>

<h2>All Payments</h2>

<table border="1" cellpadding="10">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Amount</th>
    <th>Status</th>
    <th>Action</th>
</tr>

<%
List<FeePayment> list = (List<FeePayment>) request.getAttribute("list");

if(list != null && !list.isEmpty()){
    for(FeePayment f : list){
%>

<tr>
    <td><%=f.getPaymentId()%></td>
    <td><%=f.getStudentName()%></td>
    <td><%=f.getAmount()%></td>
    <td><%=f.getStatus()%></td>
    <td>
        <a href="feepaymentupdate.jsp?id=<%=f.getPaymentId()%>">Edit</a> |
        <a href="delete?id=<%=f.getPaymentId()%>">Delete</a>
    </td>
</tr>

<%
    }
} else {
%>

<tr>
    <td colspan="5">No Data Found</td>
</tr>

<%
}
%>

</table>