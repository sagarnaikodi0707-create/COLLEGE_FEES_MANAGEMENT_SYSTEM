<%@ page import="java.util.*,model.FeePayment" %>

<h2>Report Result</h2>

<%
String type = (String) request.getAttribute("type");

if ("total".equals(type)) {

    double total = 0;
    if(request.getAttribute("total") != null){
        total = (double) request.getAttribute("total");
    }
%>

<h1 style="color:green;">Total Collection:  <%= total %></h1>

<%

} else {

List<FeePayment> list = (List<FeePayment>) request.getAttribute("list");
%>

<table border="1" cellpadding="10">
<tr>
    <th>Name</th>
    <th>Amount</th>
    <th>Status</th>
</tr>

<%
if(list != null && !list.isEmpty()){
    for(FeePayment f : list){
%>

<tr>
    <td><%=f.getStudentName()%></td>
    <td><%=f.getAmount()%></td>
    <td><%=f.getStatus()%></td>
</tr>

<%
    }
} else {
%>

<tr>
    <td colspan="3">No Data Found</td>
</tr>

<%
}
%>

</table>

<% } %>