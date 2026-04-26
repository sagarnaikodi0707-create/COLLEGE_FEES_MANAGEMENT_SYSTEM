<!DOCTYPE html>
<html>
<head>
<title>Reports</title>

<style>
body {
    font-family: Arial;
    background: #fff;
}

.container {
    width: 400px;
    margin: auto;
    margin-top: 80px;
}

select, input, button {
    width: 100%;
    padding: 10px;
    margin: 10px 0;
}

/* Buttons */
.generate {
    background: #007bff;
    color: white;
    border: none;
}

.total {
    background: green;
    color: white;
    border: none;
}
</style>

</head>
<body>

<div class="container">

<h2>Reports</h2>

<form action="report" method="post">

<select name="type">
    <option value="overdue">Overdue Students</option>
    <option value="notpaid">Not Paid in Period</option>
</select>

From:
<input type="date" name="fromDate">

To:
<input type="date" name="toDate">

<button class="generate">Generate Report</button>

</form>

<!-- ✅ SEPARATE TOTAL COLLECTION BUTTON -->
<form action="report" method="get">
    <button class="total">Total Collection</button>
</form>

</div>

</body>
</html>