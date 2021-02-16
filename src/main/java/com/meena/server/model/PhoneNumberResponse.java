package com.meena.server.model;

import java.util.List;

public class PhoneNumberResponse {
    private PhoneNumberRequest phoneNumberRequest;
    private List<PhoneNumber> phoneNumberList;
//    private String phoneNumber;
//    private String custId;
//    private LocalDateTime simActDate;
//    private String simStatus;
    private String responseStatusMessage;

    public PhoneNumberResponse() {
    }

//    public PhoneNumberResponse(PhoneNumberRequest phoneNumberRequest, String simStatus, String responseStatusMessage) {
//        this.phoneNumberRequest = phoneNumberRequest;
////        this.simStatus = simStatus;
//        this.responseStatusMessage = responseStatusMessage;
//    }

    public PhoneNumberRequest getPhoneNumberRequest() {
        return phoneNumberRequest;
    }

    public void setPhoneNumberRequest(PhoneNumberRequest phoneNumberRequest) {
        this.phoneNumberRequest = phoneNumberRequest;
    }

//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getCustId() {
//        return custId;
//    }
//
//    public void setCustId(String custId) {
//        this.custId = custId;
//    }
//
//    public LocalDateTime getSimActDate() {
//        return simActDate;
//    }
//
//    public void setSimActDate(LocalDateTime simActDate) {
//        this.simActDate = simActDate;
//    }
//
//    public String getSimStatus() {
//        return simStatus;
//    }
//
//    public void setSimStatus(String simStatus) {
//        this.simStatus = simStatus;
//    }

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
}
