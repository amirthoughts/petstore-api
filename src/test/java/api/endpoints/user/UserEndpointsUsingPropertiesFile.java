package api.endpoints.user;

import api.endpoints.Routes;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndpointsUsingPropertiesFile {

    //method to fetch url from properties file
    static ResourceBundle getURL() {
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes; //Load properties file
    }

    public static Response createUser(User payload){
        String postURL = getURL().getString("post_url");
        Response response = given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(payload)
            .when()
            .post(postURL);

        return response;
    }

    public static Response getUser(String username){
        String getURL = getURL().getString("get_url");
        Response response = given()
                .pathParam("username", username)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(getURL);

        return response;
    }

    public static Response updateUser(String username, User payload){
        String updateURL = getURL().getString("update_url");
        Response response = given()
                .pathParam("username", username)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(payload)
                .put(updateURL);

        return response;
    }

    public static Response deleteUser(String username){
        String deleteURL = getURL().getString("delete_url");
        Response response = given()
                .pathParam("username", username)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete(deleteURL);

        return response;
    }

}
