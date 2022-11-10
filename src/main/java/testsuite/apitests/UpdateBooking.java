package testsuite.apitests;

import constants.RequestType;
import dataGenerators.TokenGenerator;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static io.restassured.path.json.JsonPath.from;

/**
 * @author vignan
 * @since 10/11/22
 * Class that has tests for validating different Update booking API's.
 */
public class UpdateBooking extends BaseTests {

    public static String token;
    public String additionalneeds = "Breakfast";
    private String firstName;
    private String lastName;

    /**
     * Test to verify the Update Bookings API
     */
    @Test
    public void updateBooking() {

        firstName = randomStrings.generateRandomWord(5);
        lastName = randomStrings.generateRandomWord(6);
        LocalDate checkInDate = LocalDate.now();
        JSONObject bookingInfo = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        bookingInfo.put("firstname", firstName);
        bookingInfo.put("lastname", lastName);
        bookingInfo.put("totalprice", 120);
        bookingInfo.put("depositpaid", true);
        bookingDates.put("checkin", checkInDate);
        bookingDates.put("checkout", checkInDate);
        bookingInfo.put("bookingdates", bookingDates);
        bookingInfo.put("additionalneeds", additionalneeds);

        token = TokenGenerator.getToken();

        Response response = restAssuredHelper.callEndPoint(RequestType.Put, "/3079", bookingInfo.toString(), token);

        String jsonString = response.asString();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(from(jsonString).getString("firstname"), firstName);
        Assert.assertEquals(from(jsonString).getString("lastname"), lastName);
        Assert.assertEquals(from(jsonString).getInt("totalprice"), 120);
        Assert.assertEquals(from(jsonString).getBoolean("depositpaid"), true);
        Assert.assertEquals(from(jsonString).getString("additionalneeds"), additionalneeds);
    }

    /**
     * Test to verify the Partial Update Booking by first name API
     */
    @Test
    public void partialUpdateBooking_firstName() {

        firstName = randomStrings.generateRandomWord(5);
        JSONObject bookingInfo = new JSONObject();
        bookingInfo.put("firstname", firstName);

        token = TokenGenerator.getToken();
        Response response = restAssuredHelper.callEndPoint(RequestType.Patch, "/3079", bookingInfo.toString(), token);

        String jsonString = response.asString();
        System.out.println(jsonString);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(from(jsonString).getString("firstname"), firstName);
    }

    /**
     * Test to verify the Partial Update Booking by last name API
     */
    @Test
    public void partialUpdateBooking_lastName() {

        lastName = randomStrings.generateRandomWord(5);
        JSONObject bookingInfo = new JSONObject();
        bookingInfo.put("lastname", lastName);

        token = TokenGenerator.getToken();
        Response response = restAssuredHelper.callEndPoint(RequestType.Patch, "/3079", bookingInfo.toString(), token);

        String jsonString = response.asString();
        System.out.println(jsonString);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(from(jsonString).getString("lastname"), lastName);

    }
}
