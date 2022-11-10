package testsuite.apitests;

import dataGenerators.RandomStrings;
import helpers.RestAssuredHelper;
import io.restassured.RestAssured;

/**
 * @author vignan,
 * @since 10/11/22
 */
public class BaseTests {

    RestAssuredHelper restAssuredHelper;
    RandomStrings randomStrings;

    public BaseTests() {
        SetBaseUri();
        restAssuredHelper = new RestAssuredHelper();
        randomStrings = new RandomStrings();
    }

    public void SetBaseUri() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking";
    }

}
