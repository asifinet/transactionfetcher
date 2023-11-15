<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transactions</title>
</head>
<body>

<h2>Transactions</h2>

<table border="1">
    <thead>
    <tr>
        <th>MTN</th>
        <th>Amount</th>
        <th>Sender</th>
        <th>Beneficiary</th>
        <!-- Add other table headers based on your Transaction class -->
    </tr>
    </thead>
    <tbody>
    <c:forEach var="transaction" items="${transactions}">
        <tr>
            <td>${transaction.mtn}</td>
            <td>${transaction.amount}</td>
            <td>${transaction.senderFullName}</td>
            <td>${transaction.beneficiaryFullName}</td>
            <!-- Add other table cells based on your Transaction class -->
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
