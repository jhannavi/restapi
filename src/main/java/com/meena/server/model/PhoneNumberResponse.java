package com.meena.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PhoneNumberResponse {
    private PhoneNumberRequest phoneNumberRequest;
    private List<PhoneNumber> phoneNumberList;
    private String responseStatusMessage;
}
