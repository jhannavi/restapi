package com.meena.server.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "phone_numbers")
public class PhoneNumber {
    private Long uniqueId;
    private String custId;
    private String phoneNumber;
    private String simStatus;
    private LocalDateTime simActDate;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSimStatus() {
        return simStatus;
    }

    public void setSimStatus(String simStatus) {
        this.simStatus = simStatus;
    }

    public LocalDateTime getSimActDate() {
        return simActDate;
    }

    public void setSimActDate(LocalDateTime simActDate) {
        this.simActDate = simActDate;
    }
}
