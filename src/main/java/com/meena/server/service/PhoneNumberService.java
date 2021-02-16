package com.meena.server.service;

import com.meena.server.helper.CommonHelper;
import com.meena.server.model.*;
import com.meena.server.repository.PhoneNumberRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PhoneNumberService {
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    private CommonHelper commonHelper = new CommonHelper();

    Logger logger = LogManager.getLogger(PhoneNumberService.class);

    public PhoneNumberRetrieveResponse getAllPhoneNumbers() {
        List<PhoneNumber> phoneNumbersList = phoneNumberRepository.findAll();
        PhoneNumberRetrieveResponse phoneNumberRetrieveResponse = new PhoneNumberRetrieveResponse();
        phoneNumberRetrieveResponse.setRequestType("All Phone Numbers");
        if (phoneNumbersList.isEmpty()) {
            phoneNumberRetrieveResponse.setResponseStatusMessage("Phone number details are not available");
        } else {
            phoneNumberRetrieveResponse.setPhoneNumberList(phoneNumbersList);
            phoneNumberRetrieveResponse.setResponseStatusMessage("All phone number details");
        }
        return phoneNumberRetrieveResponse;
    }

    public PhoneNumberRetrieveResponse getPhoneNumberByCustomer(String custId) {
        List<PhoneNumber> phoneNumbersList = phoneNumberRepository.findByCustId(custId);
        PhoneNumberRetrieveResponse phoneNumberRetrieveResponse = new PhoneNumberRetrieveResponse();
        phoneNumberRetrieveResponse.setRequestType("Search by Customer Id");
        phoneNumberRetrieveResponse.setRequestCustomerId(custId);

        if (phoneNumbersList.isEmpty()) {
            phoneNumberRetrieveResponse.setResponseStatusMessage(CommonHelper.CUSTOMER_PHONENUMBER_NA);
        } else {
            phoneNumberRetrieveResponse.setPhoneNumberList(phoneNumbersList);
            phoneNumberRetrieveResponse.setResponseStatusMessage(CommonHelper.CUSTOMER_PHONENUMBERS);
        }
        return phoneNumberRetrieveResponse;
    }

    public PhoneNumberRetrieveResponse getPhoneNumberDetails(String phoneNumber) {
        List<PhoneNumber> phoneNumbersList = phoneNumberRepository.findByPhoneNumber(phoneNumber);
        PhoneNumberRetrieveResponse phoneNumberRetrieveResponse = new PhoneNumberRetrieveResponse();
        phoneNumberRetrieveResponse.setRequestType("Search by Phone Number");
        phoneNumberRetrieveResponse.setRequestPhoneNumber(phoneNumber);
        if (!commonHelper.checkPhoneNumberLength(phoneNumber)) {
            logger.debug("Phone number length is not " + CommonHelper.PHONENUMBER_LENGTH + " for " + phoneNumber);
            phoneNumberRetrieveResponse.setResponseStatusMessage(CommonHelper.PHONENUMBER_LENGTH_ISSUE);
            return phoneNumberRetrieveResponse;
        }

        if (phoneNumbersList.isEmpty()) {
            phoneNumberRetrieveResponse.setResponseStatusMessage(CommonHelper.PHONENUMBER_NA);
        } else {
            phoneNumberRetrieveResponse.setPhoneNumberList(phoneNumbersList);
            phoneNumberRetrieveResponse.setResponseStatusMessage(CommonHelper.PHONENUMBER_AVAILABLE);
        }
        return phoneNumberRetrieveResponse;
    }

    public PhoneNumberResponse updateStatus(PhoneNumberRequest phoneNumberRequest) {
        //Updating the Inactive Phone Number to Active
        logger.info("Phone number activation request");
        PhoneNumberResponse phoneNumberResponse = new PhoneNumberResponse();
        phoneNumberResponse.setPhoneNumberRequest(phoneNumberRequest);
        String phoneNumber = phoneNumberRequest.getPhoneNumber();
        if (!commonHelper.checkPhoneNumberLength(phoneNumber)) {
            logger.debug("Phone number length is not " + CommonHelper.PHONENUMBER_LENGTH + " for " + phoneNumber);
            phoneNumberResponse.setResponseStatusMessage(CommonHelper.PHONENUMBER_LENGTH_ISSUE + ", so current request not processed");
            return phoneNumberResponse;
        }

        List<PhoneNumber> requestedPhoneNumbers = phoneNumberRepository.findByPhoneNumber(phoneNumber);
        PhoneNumber phoneNumberToUpdate = new PhoneNumber();
//        phoneNumberResponse.setPhoneNumber(phoneNumberRequest.getPhoneNumber());
        if(!requestedPhoneNumbers.isEmpty()) {
            logger.info("Given phone number is available");
            System.out.println("---------------------------Inside data available :: " + requestedPhoneNumbers.size());
            String phoneSimStatus = requestedPhoneNumbers.get(0).getSimStatus();
            String phoneNumberRes = requestedPhoneNumbers.get(0).getPhoneNumber();
            String custIdRes = requestedPhoneNumbers.get(0).getCustId();
            Long uniqueIdRes = requestedPhoneNumbers.get(0).getUniqueId();
            System.out.println("---------------------------Phone Number :: " + phoneNumberRes);
            System.out.println("---------------------------Customer ID :: " + custIdRes);
            System.out.println("---------------------------SIM Status :: " + phoneSimStatus);
            System.out.println("---------------------------UNIQUE ID :: " + uniqueIdRes);

            if (phoneSimStatus.equalsIgnoreCase(SimStatusEnum.ACTIVATED.toString())) {
//                phoneNumberResponse.setSimStatus(phoneSimStatus);
                logger.debug("Given phone number is already in activated state, " + phoneSimStatus + " for " + phoneNumber);
                phoneNumberResponse.setResponseStatusMessage(CommonHelper.ALREADY_ACTIVE);
                return phoneNumberResponse;
            } else {
                logger.debug("Phone number activation started for " + phoneNumber);
                phoneNumberToUpdate.setSimStatus(SimStatusEnum.ACTIVATED.toString());
                phoneNumberToUpdate.setSimActDate(LocalDateTime.now());
                phoneNumberToUpdate.setPhoneNumber(phoneNumberRes);
                phoneNumberToUpdate.setUniqueId(uniqueIdRes);
                phoneNumberToUpdate.setCustId(custIdRes);
                System.out.println("-----------------------------------saving status to active");
                phoneNumberRepository.save(phoneNumberToUpdate);
                List<PhoneNumber> phoneNumberUpdated = phoneNumberRepository.findByPhoneNumber(phoneNumber);
                phoneNumberResponse.setPhoneNumberList(phoneNumberUpdated);
                phoneNumberResponse.setResponseStatusMessage(commonHelper.PHONENUMBER_ACTIVATED);
                logger.debug("Phone number activation completed for " + phoneNumber);
            }

        } else {
            logger.debug("Phone number not available for " + phoneNumber);
            phoneNumberResponse.setResponseStatusMessage(CommonHelper.NUMBER_NA);
        }
        return phoneNumberResponse;
    }
}
