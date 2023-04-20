package api.test;

import api.endpoints.user.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTests {

    @Test(priority = 1, dataProvider ="Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userId, String username, String firstName, String lastName, String email, String password, String phoneNumber ) {

        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUsername(username);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phoneNumber);

        Response response = UserEndpoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider ="UserNames", dataProviderClass = DataProviders.class)
    public void testGetUser(String userName) {
        Response response = UserEndpoints.getUser(userName);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3, dataProvider ="UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUser(String userName) {
        Response response = UserEndpoints.deleteUser(userName);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
