package mybank;

import java.time.LocalDate;
import java.util.Date;

public class BankTransaction {
    private LocalDate date;
    private double amount;
    private String description;

    public BankTransaction(LocalDate date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public String toString() {
        return this.date + "," + this.amount + "," + description;
    }
}