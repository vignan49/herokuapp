package testsuite.apitests;


import constants.RequestType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.path.json.JsonPath.from;

/**
 * @author vignan
 * @since 10/11/22
 * Class that has tests for validating different get booking API's.
 */
public class GetBookings extends BaseTests {

    private String firstName = "Alex";
    private String lastName = "Dominguez";
    private String checkInDate = "2021-03-29";
    private String checkOutDate = "2014-05-21";

    /**
     * Test to verify the get Bookings API
     */
    @Test
    public void getBookings() {

        Response response = restAssuredHelper.callEndPoint(RequestType.Get, "/", null, null);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    /**
     * Test to verify the get Bookings By first name API
     */
    @Test
    public void getBookingsByFirstName() {

        Response response = restAssuredHelper.callEndPoint(RequestType.Get, "?firstname=" + firstName, null, null);
        String jsonString = response.asString();
    }

    /**
     * Test to verify the get Bookings by lastname API
     */
    @Test
    public void getBookingsByLastName() {

        Response response = restAssuredHelper.callEndPoint(RequestType.Get, "?lastname=" + lastName, null, null);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    /**
     * Test to verify the get Bookings By FirstName And LastName API
     */
    @Test
    public void getBookingsByFirstNameAndLastName() {

        Response response = restAssuredHelper.callEndPoint(RequestType.Get, "?firstname=" + firstName + "&lastname=" + lastName, null, null);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    /**
     * Test to verify the get Bookings By Checkin API
     */
    @Test
    public void getBookingsByCheckIn() {

        Response response = restAssuredHelper.callEndPoint(RequestType.Get, "?checkin=" + checkInDate, null, null);
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    /**
     * Test to verify the get Bookings By Checkout API
     */
    @Test
    public void getBookingsByCheckOut() {

        Response response = restAssuredHelper.callEndPoint(RequestType.Get, "?checkout=" + checkOutDate, null, null);
        Assert.assertEquals(response.getStatusCode(), 200);


    }

    /**
     * Test to verify the get Bookings By Checkin and Checkout API
     */
    @Test
    public void getBookingsByCheckInAndCheckOut() {

        Response response = restAssuredHelper.callEndPoint(RequestType.Get, "?checkin=" + checkInDate + "&checkout=" + checkOutDate, null, null);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    /**
     * Test to verify the get Bookings By ID API
     */
    @Test
    public void getBookingByID() {

        Response response = restAssuredHelper.callEndPoint(RequestType.Get, "/6155", null, null);

        Assert.assertEquals(response.getStatusCode(), 200);
        String jsonString = response.asString();
        Assert.assertEquals(from(jsonString).getString("firstname"), "Guoqiang");
    }
}
