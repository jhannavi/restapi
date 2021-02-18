package blackbox.com.meena;

import com.meena.server.PhoneNumberApplication;
import com.meena.server.helper.CommonHelper;
import com.meena.server.model.PhoneNumberRequest;
import com.meena.server.repository.PhoneNumberRepository;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.meena.server.helper.CommonHelper.APPLICATION_JSON;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@SpringBootTest(classes = PhoneNumberApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PhoneNumberControllerTest {
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    private CommonHelper commonHelper = new CommonHelper();

    private static final String BASE_URI = "http://localhost";
    private static final Integer PORT = 8083;
    private static final String BASE_PATH = "/phonenumber";

    @BeforeEach
    void setup() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = PORT;
        RestAssured.basePath = BASE_PATH;
    }

    @Test
    void givenValidGetAllPhoneNumbers_whenNullRequest_thenSuccess() {
        //1. TC success when no data returns []
        given()
            .log().all()
            .accept(APPLICATION_JSON)
        .when()
            .get("/allPhoneNumbers")
        .then()
            .log().all()
            .body(not(Matchers.empty()))
            .body("responseStatusMessage", equalTo("All phone number details"));
    }

    @Test
    void givenValidRequest_whenInvalidPhoneNumber_thenErrorMessage() {
        //2. Pass Invalid Phone number the error message is displayed
        given()
            .log().all()
            .accept(APPLICATION_JSON)
        .when()
            .get("/numberDetails/0223456789")
        .then()
            .log().all()
            .body(not(Matchers.empty()))
            .body("responseStatusMessage", equalTo("Phone Numbers is not available for the given phone number"));
    }

    @Test
    void givenValidRequest_whenValidPhoneNumber_thenSuccess() {
        //3. Pass valid Phone number then success message
        given()
            .log().all()
            .accept(APPLICATION_JSON)
        .when()
            .get("/numberDetails/0123456781")
        .then()
            .log().all()
            .body(not(Matchers.empty()))
            .body("responseStatusMessage", equalTo("Phone Numbers details available for the given phone number"));
    }

    @Test
    void givenValidRequest_whenNumberLengthLess_thenError() {
        //4. Pass short phone number then throw error
        given()
            .log().all()
            .accept(APPLICATION_JSON)
        .when()
            .get("/numberDetails/012345678")
        .then()
            .log().all()
            .body(not(Matchers.empty()))
            .body("responseStatusMessage", equalTo("Given phone number length should be 10"));
    }

    @Test
    void givenValidRequest_whenInvalidCustomerId_thenErrorMessage() {
        //5. Pass Invalid customer number the error message is displayed
        given()
            .log().all()
            .accept(APPLICATION_JSON)
        .when()
            .get("/customerPhoneNumbers/100")
        .then()
            .log().all()
            .body(not(Matchers.empty()))
            .body("responseStatusMessage", equalTo("Phone Numbers are not available for the given Customer Id"));
    }

    @Test
    void givenValidRequest_whenValidCustomerId_thenSuccess() {
        //6. Pass valid Customer Id then success message
        given()
            .log().all()
            .accept(APPLICATION_JSON)
        .when()
            .get("/customerPhoneNumbers/1001")
            .then()
        .log().all()
            .body(not(Matchers.empty()))
            .body("responseStatusMessage", equalTo("Phone Numbers for the given Customer Id"));
    }

    @Test
    void givenValidSimActivation_whenActive_thenError() {
        //7. TC to test the activation (if already activated throw error message)
        PhoneNumberRequest phoneNumberRequest = new PhoneNumberRequest();
        phoneNumberRequest.setPhoneNumber("0123456781");
        given()
            .log().all()
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .body(phoneNumberRequest)
        .when()
            .post("/activate")
        .then()
            .contentType(APPLICATION_JSON)
            .log().all()
            .body(not(Matchers.empty()))
            .body("responseStatusMessage"
                    , equalTo("Phone Number is already in active state, so current request not processed"));
    }

    @Test
    void givenValidSimActivation_whenLengthLess_thenError() {
        //8. TC to test the activation (if phone number length is less then error)
        PhoneNumberRequest phoneNumberRequest = new PhoneNumberRequest();
        phoneNumberRequest.setPhoneNumber("012345678");
        given()
            .log().all()
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .body(phoneNumberRequest)
        .when()
            .post("/activate")
        .then()
            .contentType(APPLICATION_JSON)
            .log().all()
            .body(not(Matchers.empty()))
            .body("responseStatusMessage"
                    , equalTo("Given phone number length should be 10, so current request not processed"));
    }

    @Test
    void givenValidSimActivation_whenLengthMore_thenError() {
        //9. TC to test the activation (if phone number length is more then error)
        PhoneNumberRequest phoneNumberRequest = new PhoneNumberRequest();
        phoneNumberRequest.setPhoneNumber("01234567891");
        given()
            .log().all()
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .body(phoneNumberRequest)
        .when()
            .post("/activate")
        .then()
            .contentType(APPLICATION_JSON)
            .log().all()
            .body(not(Matchers.empty()))
            .body("responseStatusMessage", equalTo("Given phone number length should be 10, so current request not processed"));
    }

    @Test
    void givenValidSimActivation_whenValid_thenSuccess() {
        //10. TC to test the activation
        PhoneNumberRequest phoneNumberRequest = new PhoneNumberRequest();
        phoneNumberRequest.setPhoneNumber("0123456782");
        given()
            .log().all()
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .body(phoneNumberRequest)
        .when()
            .post("/activate")
        .then()
            .contentType(APPLICATION_JSON)
            .log().all()
            .body(not(Matchers.empty()))
            .body("responseStatusMessage", equalTo("Given Phone Number is activated"));
    }

    @Test
    void givenValidSimActivation_whenEmptyRequest_thenError() {
        //11. TC to test the activation (fail for the empty request)
        PhoneNumberRequest phoneNumberRequest = new PhoneNumberRequest();
        given()
            .log().all()
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .body(phoneNumberRequest)
        .when()
            .post("/activate")
        .then()
            .contentType(APPLICATION_JSON)
            .log().all()
            .body(not(Matchers.empty()))
            .body("responseStatusMessage"
                    , equalTo("Given phone number length should be 10, so current request not processed"));
    }
}
