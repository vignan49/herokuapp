package dataGenerators;

import constants.Header;
import io.restassured.response.Response;
import org.json.JSONObject;
import testsuite.apitests.BaseTests;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

/**
 * @author vignan
 * @since 10/11/22
 */
public class TokenGenerator extends BaseTests {

    public static String token;

    /**
     * Method to generate API token
     * return the token
     */
    public static String getToken() {
        JSONObject auth = new JSONObject();
        auth.put("username", "admin");
        auth.put("password", "password123");

        Response response = given()
                .header(Header.ContentType, Header.ApplicationJson)
                .header(Header.Accept, Header.ApplicationJson)
                .body(auth.toString())
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .extract().response();
        String jsonResponse = response.asString();
        return from(jsonResponse).getString("token");
    }
}