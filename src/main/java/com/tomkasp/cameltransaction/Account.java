package com.tomkasp.cameltransaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;

    public Account() {
    }

    public Account(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }

    public void updateNumber(String newNumber) {
        this.number = newNumber;
    }
}
