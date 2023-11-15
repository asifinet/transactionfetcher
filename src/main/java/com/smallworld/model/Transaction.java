package com.smallworld.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @JsonProperty("mtn")
    private long mtn;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("senderFullName")
    private String senderFullName;

    @JsonProperty("senderAge")
    private int senderAge;

    @JsonProperty("beneficiaryFullName")
    private String beneficiaryFullName;

    @JsonProperty("beneficiaryAge")
    private int beneficiaryAge;

    @JsonProperty("issueId")
    private Integer issueId; // Use Integer for nullable values

    @JsonProperty("issueSolved")
    private boolean issueSolved;

    @JsonProperty("issueMessage")
    private String issueMessage;

    // Constructors, getters, and setters...

    // You may need to generate these using your IDE or manually.
}
