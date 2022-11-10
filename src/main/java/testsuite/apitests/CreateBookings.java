package testsuite.apitests;

import constants.RequestType;
import dataGenerators.TokenGenerator;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static io.restassured.path.json.JsonPath.from;

/**
 * @author vignan
 * @since 10/11/22
 * Class that has tests for validating different booking API's.
 */

public class CreateBookings extends BaseTests {

    public static String firstName;
    public static String lastName;
    public static String bookingID;
    public String token;
    public String additionalneeds = "Breakfast";

    /**
     * Test to verify the create Booking API
     */
    @Test
    public void createBooking() {

        String bookingInfo = BookingInfo();
        Response response = restAssuredHelper.callEndPoint(RequestType.Post, "/", bookingInfo, null);

        String jsonString = response.asString();
        System.out.println(jsonString);
        Assert.assertEquals(response.getStatusCode(), 200);
        bookingID = from(jsonString).getString("bookingid");
        HashMap<String, String> validateResponse = response.path("booking");
        Assert.assertEquals(validateResponse.get("firstname"), firstName);
        Assert.assertEquals(validateResponse.get("lastname"), lastName);
        Assert.assertEquals(validateResponse.get("totalprice"), 120);
        Assert.assertEquals(validateResponse.get("depositpaid"), true);
        Assert.assertEquals(validateResponse.get("additionalneeds"), additionalneeds);
    }

    /**
     * Test to verify the get Booking By ID API
     */
    @Test
    public void getBookingByID() {

        Response response = restAssuredHelper.callEndPoint(RequestType.Get, "/" + bookingID, null, null);

        Assert.assertEquals(response.getStatusCode(), 200);
        String jsonString = response.asString();
        System.out.println(jsonString);
        Assert.assertEquals(from(jsonString).getString("firstname"), firstName);
    }

    /**
     * Test to verify the update Booking API
     */
    @Test
    public void updateBooking() {


        String bookingInfo = BookingInfo();
        token = TokenGenerator.getToken();

        Response response = restAssuredHelper.callEndPoint(RequestType.Put, "/" + bookingID, bookingInfo, token);

        String jsonString = response.asString();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(from(jsonString).getString("firstname"), firstName);
        Assert.assertEquals(from(jsonString).getString("lastname"), lastName);
        Assert.assertEquals(from(jsonString).getInt("totalprice"), 120);
        Assert.assertEquals(from(jsonString).getBoolean("depositpaid"), true);
        Assert.assertEquals(from(jsonString).getString("additionalneeds"), additionalneeds);
    }

    /**
     * Test to verify the partial update Booking API
     */
    @Test
    public void partialUpdateBooking() {

        firstName = randomStrings.generateRandomWord(5);
        JSONObject bookingInfo = new JSONObject();
        bookingInfo.put("firstname", firstName);

        token = TokenGenerator.getToken();
        Response response = restAssuredHelper.callEndPoint(RequestType.Patch, "/" + bookingID, bookingInfo.toString(), token);

        String jsonString = response.asString();
        System.out.println(jsonString);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(from(jsonString).getString("firstname"), firstName);
    }

    /**
     * Test to verify the Delete Booking API
     */
    @Test
    public void DeleteBooking() {

        token = TokenGenerator.getToken();

        Response response = restAssuredHelper.callEndPoint(RequestType.Delete, "/" + bookingID, null, token);

        String jsonString = response.asString();
        System.out.println(jsonString);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.asString(), "Created");
    }

    /**
     * Method to crate the payload
     * return - BookingInfo payload
     */
    public String BookingInfo() {
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
        return bookingInfo.toString();
    }
}