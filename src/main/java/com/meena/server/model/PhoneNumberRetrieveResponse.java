package com.meena.server.model;

import java.util.List;

public class PhoneNumberRetrieveResponse {
    private List<PhoneNumber> phoneNumberList;
    private String requestPhoneNumber;
    private String requestCustomerId;
    private String responseStatusMessage;
    private String requestType;

    public PhoneNumberRetrieveResponse() {
    }

    public String getRequestPhoneNumber() {
        return requestPhoneNumber;
    }

    public void setRequestPhoneNumber(String requestPhoneNumber) {
        this.requestPhoneNumber = requestPhoneNumber;
    }

    public String getRequestCustomerId() {
        return requestCustomerId;
    }

    public void setRequestCustomerId(String requestCustomerId) {
        this.requestCustomerId = requestCustomerId;
    }

    public String getResponseStatusMessage() {
        return responseStatusMessage;
    }

    public void setResponseStatusMessage(String responseStatusMessage) {
        this.responseStatusMessage = responseStatusMessage;
    }

    public List<PhoneNumber> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(List<PhoneNumber> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
