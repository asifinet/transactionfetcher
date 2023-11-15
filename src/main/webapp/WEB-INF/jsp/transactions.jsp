<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.smallworld.model.Transaction" %>
<%@ page import="com.smallworld.transactions.TransactionDataFetcher" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.List" %>
<%@ page import="com.smallworld.model.Transaction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transactions</title>
</head>
<body>

<%
    try {
        // Load transactions from the "transactions.json" file
        TransactionDataFetcher dataFetcher = new TransactionDataFetcher(
                application.getResourceAsStream("/WEB-INF/classes/transactions.json")
        );

        // Example 1: Get Unique Transaction IDs
        List<Long> uniqueTransactionIds = dataFetcher.getUniqueTransactionIds();
        out.println("Unique Transaction IDs: " + uniqueTransactionIds + "<br>");

        // Example 2: Get Total Transaction Amount for a specific ID
        long transactionId = 322; // replace with the actual transaction ID
        double totalAmountForTransaction = dataFetcher.getTotalAmountForTransaction(transactionId);
        out.println("Total Amount for Transaction ID " + transactionId + ": " + totalAmountForTransaction + "<br>");

        // Example 3: Get Total Amounts for All Transactions
        Map<Long, Double> totalAmountsForAllTransactions = dataFetcher.getTotalAmountsForAllTransactions();
        out.println("Total Amounts for All Transactions: " + totalAmountsForAllTransactions + "<br>");

        // Example 4: Get Count of Transactions with Unresolved Issues
        long countOfTransactionsWithUnresolvedIssues = dataFetcher.getCountOfTransactionsWithUnresolvedIssues();
        out.println("Count of Transactions with Unresolved Issues: " + countOfTransactionsWithUnresolvedIssues + "<br>");

        // Example 5: Get Average Age of Senders with Resolved Issues
        double averageAgeOfSendersWithResolvedIssues = dataFetcher.getAverageAgeOfSendersWithResolvedIssues();
        out.println("Average Age of Senders with Resolved Issues: " + averageAgeOfSendersWithResolvedIssues + "<br>");

        
    } catch (IOException e) {
        e.printStackTrace();
        // Handle the exception according to your requirements
    }
%>

</body>
</html>
