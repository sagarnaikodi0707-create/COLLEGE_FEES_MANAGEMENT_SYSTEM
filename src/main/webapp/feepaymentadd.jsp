<!DOCTYPE html>
<html>
<head>
<title>Add Fee Payment</title>

<style>
body { font-family: Arial; background: #f2f2f2; }
.container {
    width: 400px; margin: auto; margin-top: 80px;
    background: white; padding: 20px; border-radius: 10px;
}
input, select {
    width: 100%; padding: 10px; margin: 10px 0;
}
button {
    width: 100%; padding: 10px;
    background: green; color: white; border: none;
}
</style>

</head>
<body>

<div class="container">
<h2>Add Fee Payment</h2>

<form action="add" method="post">

Student ID:
<input type="number" name="studentId" required>

Name:
<input type="text" name="studentName" required>

Date:
<input type="date" name="paymentDate" required>

Amount:
<input type="number" step="0.01" name="amount" required>

Status:
<select name="status">
    <option>Paid</option>
    <option>Overdue</option>
</select>

<button type="submit">Add Payment</button>

</form>
</div>

</body>
</html>