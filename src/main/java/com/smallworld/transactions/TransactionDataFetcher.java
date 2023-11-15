package com.smallworld.transactions;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.smallworld.model.Transaction;

public class TransactionDataFetcher {

    private List<Transaction> transactions;

    // Constructor to initialize transactions from the JSON file
 
    public TransactionDataFetcher(InputStream inputStream) throws IOException {
        this.transactions = loadTransactionsFromStream(inputStream);
    }
    
    
    private List<Transaction> loadTransactionsFromStream(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(inputStream);

        List<Transaction> transactionList = new ArrayList<>();

        if (rootNode.isArray()) {
            Iterator<JsonNode> elements = rootNode.elements();

            while (elements.hasNext()) {
                JsonNode transactionNode = elements.next();
                Transaction transaction = objectMapper.treeToValue(transactionNode, Transaction.class);
                transactionList.add(transaction);
            }
        }

        return transactionList;
    }
    
    
    private void handleJsonParsingError(IOException e) {
        // Handle JSON parsing errors, e.g., log the error
        e.printStackTrace();
    }
    // Parse JSON content into a list of transactions
    private List<Transaction> parseJson(String jsonContent) {
        // Implement JSON parsing logic here
        // You can use a JSON library like Jackson or Gson for this purpose
        // Return a list of Transaction objects
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CollectionType transactionListType = objectMapper.getTypeFactory().constructCollectionType(List.class, Transaction.class);
            return objectMapper.readValue(jsonContent, transactionListType);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception according to your requirements
            return Collections.emptyList();
        }
    }

    // Method to get a list of unique transaction identifiers (mtn)
    public List<Long> getUniqueTransactionIds() {
        // Implement logic to extract unique transaction IDs
        // You can use Java streams to achieve this
        return transactions.stream()
                .map(Transaction::getMtn)
                .distinct()
                .collect(Collectors.toList());
    }

    // Method to get the total transaction amount for a given transaction ID
    public double getTotalAmountForTransaction(long transactionId) {
        // Implement logic to calculate total amount for a given transaction ID
        // You can use Java streams and filter by transaction ID
        return transactions.stream()
                .filter(transaction -> transaction.getMtn() == transactionId)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Method to get a map of transaction IDs to their total amounts
    public Map<Long, Double> getTotalAmountsForAllTransactions() {
        // Implement logic to calculate total amounts for all transactions
        // You can use Java streams to group by transaction ID and sum amounts
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getMtn,
                        Collectors.summingDouble(Transaction::getAmount)
                ));
    }

    // Method to get the count of transactions with unresolved issues
    public long getCountOfTransactionsWithUnresolvedIssues() {
        // Implement logic to count transactions with unresolved issues
        // You can use Java streams to filter by issueSolved
        return transactions.stream()
                .filter(transaction -> transaction.getIssueId() != null && !transaction.isIssueSolved())
                .count();
    }

    // Method to get the average age of senders with resolved issues
    public double getAverageAgeOfSendersWithResolvedIssues() {
        // Implement logic to calculate the average age of senders with resolved issues
        // You can use Java streams to filter by issueSolved and map to sender age
        return transactions.stream()
                .filter(transaction -> transaction.getIssueId() != null && transaction.isIssueSolved())
                .mapToDouble(Transaction::getSenderAge)
                .average()
                .orElse(0.0); // Return 0 if there are no transactions with resolved issues
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }    

}
