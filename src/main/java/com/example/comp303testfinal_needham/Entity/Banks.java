package com.example.comp303testfinal_needham.Entity;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Banks")
public class Banks {

    @Id
    private String bankID;
    @NotNull
    private String bankName;
    @NotNull
    private int bankYear;
    @NotNull
    private int bankEmp;
    @NotNull
    private String bankAddress;
    @NotNull
    private int BankBranches;
    @NotNull
    private int BankATMs;

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    public @NotNull String getBankName() {
        return bankName;
    }

    public void setBankName(@NotNull String bankName) {
        this.bankName = bankName;
    }

    @NotNull
    public int getBankYear() {
        return bankYear;
    }

    public void setBankYear(@NotNull int bankYear) {
        this.bankYear = bankYear;
    }

    @NotNull
    public int getBankEmp() {
        return bankEmp;
    }

    public void setBankEmp(@NotNull int bankEmp) {
        this.bankEmp = bankEmp;
    }

    public @NotNull String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(@NotNull String bankAddress) {
        this.bankAddress = bankAddress;
    }

    @NotNull
    public int getBankBranches() {
        return BankBranches;
    }

    public void setBankBranches(@NotNull int bankBranches) {
        BankBranches = bankBranches;
    }

    @NotNull
    public int getBankATMs() {
        return BankATMs;
    }

    public void setBankATMs(@NotNull int bankATMs) {
        BankATMs = bankATMs;
    }



}
