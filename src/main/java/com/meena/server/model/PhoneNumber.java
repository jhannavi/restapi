package com.meena.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
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
}
