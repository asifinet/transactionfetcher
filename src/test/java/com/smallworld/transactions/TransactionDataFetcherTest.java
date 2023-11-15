package com.smallworld.transactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TransactionDataFetcherTest {

    private TransactionDataFetcher dataFetcher;

    @Before
    public void setUp() {
        // Create an example JSON content for testing
        String jsonContent = "[{\"mtn\": 1, \"amount\": 100.0, \"issueId\": null, \"issueSolved\": true, \"senderAge\": 25},"
                + "{\"mtn\": 2, \"amount\": 150.0, \"issueId\": 101, \"issueSolved\": false, \"senderAge\": 30},"
                + "{\"mtn\": 1, \"amount\": 75.0, \"issueId\": 102, \"issueSolved\": true, \"senderAge\": 22}]";
        InputStream inputStream = new ByteArrayInputStream(jsonContent.getBytes());

        try {
            dataFetcher = new TransactionDataFetcher(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUniqueTransactionIds() {
        List<Long> uniqueIds = dataFetcher.getUniqueTransactionIds();
        assertEquals(2, uniqueIds.size());
        assertTrue(uniqueIds.contains(1L));
        assertTrue(uniqueIds.contains(2L));
    }

    @Test
    public void testGetTotalAmountForTransaction() {
        double totalAmount = dataFetcher.getTotalAmountForTransaction(1L);
        assertEquals(175.0, totalAmount, 0.001);
    }

    @Test
    public void testGetTotalAmountsForAllTransactions() {
        Map<Long, Double> totalAmounts = dataFetcher.getTotalAmountsForAllTransactions();
        assertEquals(2, totalAmounts.size());
        assertEquals(175.0, totalAmounts.get(1L), 0.001);
        assertEquals(150.0, totalAmounts.get(2L), 0.001);
    }

    @Test
    public void testGetCountOfTransactionsWithUnresolvedIssues() {
        long count = dataFetcher.getCountOfTransactionsWithUnresolvedIssues();
        assertEquals(1, count);
    }

    @Test
    public void testGetAverageAgeOfSendersWithResolvedIssues() {
        double averageAge = dataFetcher.getAverageAgeOfSendersWithResolvedIssues();
        assertEquals(22.0, averageAge, 0.001);
    }

    // Add more test cases as needed

}
	