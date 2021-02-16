package com.meena.server.controller;

import com.meena.server.model.PhoneNumberRequest;
import com.meena.server.model.PhoneNumberResponse;
import com.meena.server.model.PhoneNumberRetrieveResponse;
import com.meena.server.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/phonenumber")
public class PhoneNumberController {
    @Autowired
    private PhoneNumberService phoneNumberService;

    @GetMapping(value = "/allPhoneNumbers")
    public PhoneNumberRetrieveResponse getAllPhoneNumbers() {
        return phoneNumberService.getAllPhoneNumbers();
    }

    @GetMapping(value = "/customerPhoneNumbers/{custId}")
    public PhoneNumberRetrieveResponse getPhoneNumberByCustomer(@PathVariable final String custId){
        return phoneNumberService.getPhoneNumberByCustomer(custId);
    }

    @GetMapping(value = "/numberDetails/{phoneNumber}")
    public PhoneNumberRetrieveResponse getPhoneNumberDetails(@PathVariable final String phoneNumber){
        return phoneNumberService.getPhoneNumberDetails(phoneNumber);
    }

    @PostMapping(value = "/activate")
    public PhoneNumberResponse updateStatus(@RequestBody @NotNull @Valid final PhoneNumberRequest phoneNumberRequest) {
        return phoneNumberService.updateStatus(phoneNumberRequest);
    }
}
