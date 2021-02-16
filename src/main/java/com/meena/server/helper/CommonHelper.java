package com.meena.server.helper;

public class CommonHelper {
    public static final int PHONENUMBER_LENGTH = 10;
    public static final String ALREADY_ACTIVE = "Phone Number is already in active state, so current request not processed";
    public static final String NUMBER_NA = "Phone Number is not available, so current request not processed";
    public static final String NUMBER_NOTISSUED = "Phone Number is not issued to the customer Id";
    public static final String CUSTOMER_PHONENUMBER_NA = "Phone Numbers are not available for the given Customer Id";
    public static final String CUSTOMER_PHONENUMBERS = "Phone Numbers for the given Customer Id";
    public static final String PHONENUMBER_NA = "Phone Numbers is not available for the given phone number";
    public static final String PHONENUMBER_AVAILABLE = "Phone Numbers details available for the given phone number";
    public static final String PHONENUMBER_ACTIVATED = "Given Phone Number is activated";
    public static final String APPLICATION_JSON = "application/json";
    public static final String PHONENUMBER_LENGTH_ISSUE = "Given phone number length should be " + PHONENUMBER_LENGTH;

    /**
     * Method to check the phone number length
     * @param phoneNumber
     * @return boolean
     */
    public boolean checkPhoneNumberLength(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.trim().length() == PHONENUMBER_LENGTH) {
            return true;
        } else {
            return false;
        }
    }
}
