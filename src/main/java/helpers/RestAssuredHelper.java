package helpers;

import constants.Header;
import constants.RequestType;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author vignan
 * @since 10/11/22
 * helper class to process the API request
 */
public class RestAssuredHelper {

    /**
     *Method to process the API reuest and return the response
     *
     * @param requestType request type to process the API
     * @param url api url
     * @param payload api body
     * @param token for authorization purpose
     * return API response
     */
    public Response callEndPoint(RequestType requestType, String url, String payload, String token)
    {
        RequestSpecification request = RestAssured.given();

        request.header(Header.Accept, Header.ApplicationJson);
        if(!requestType.equals("Delete"))
        request.header(Header.ContentType, Header.ApplicationJson);

        if(!requestType.equals("Create")||!requestType.equals("Get"))
            request.header(Header.Cookie, "token="+token);

        if(payload != null)
        {
            request.body(payload);
        }

        Response response = null;

        switch(requestType)
        {
            case Delete:
                response = request.delete(url);
                break;
            case Get:
                response = request.get(url);
                break;
            case Patch:
                response = request.patch(url);
                break;
            case Post:
                response = request.post(url);
                break;
            case Put:
                response = request.put(url);
                break;
            default:
                throw new UnsupportedOperationException("Request type is not supported.");
        }

        return response;
    }
}
