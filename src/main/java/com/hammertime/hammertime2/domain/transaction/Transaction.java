// Package
package com.hammertime.hammertime2.domain.transaction;

// java
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

// jakarta
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Transaction {
    private @Id @GeneratedValue Long id;

    Long stripeTransactionID;
    Long userID;
    Calendar tDateTime;

    Transaction () {}

    public Transaction(long stripeTransactionID, long userID){
        this.stripeTransactionID = stripeTransactionID;
        this.userID = userID;
        this.tDateTime = Calendar.getInstance();
    }

    // Accessors
    public Long getTransactionId(){ return id;}
    public Long getStripeTransactionID() { return stripeTransactionID;}
    public Calendar getCalendar() { return tDateTime;}

    // Check if transaction is the same as another transaction
    public boolean equals(Transaction o) {
        if (this == o)
            return true;
        if (!(o instanceof Transaction))
            return false;
            Transaction transaction = (Transaction) o;
        return Objects.equals(this.id, transaction.id) 
        && Objects.equals(this.stripeTransactionID, transaction.stripeTransactionID)
        && Objects.equals(this.tDateTime, transaction.tDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.stripeTransactionID, this.tDateTime);
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + this.id + "\'"
        + ", StripeID='" + this.stripeTransactionID + "\'"
        + ", Date Time='" + this.tDateTime + "\'"
        + "}";
    }
}
