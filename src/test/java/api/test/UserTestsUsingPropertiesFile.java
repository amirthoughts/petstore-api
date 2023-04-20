package api.test;

import api.endpoints.user.UserEndpoints;
import api.endpoints.user.UserEndpointsUsingPropertiesFile;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTestsUsingPropertiesFile {

    Faker faker;
    User userPayload;

    public Logger logger;

    @BeforeClass
    public void setUp() {
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("****************** Creating the user ********************************");
        Response response = UserEndpointsUsingPropertiesFile.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("****************** User is created ********************************");
    }

    @Test(priority = 2)
    public void testGetUser() {
        logger.info("****************** Reading the user info ********************************");
        Response response = UserEndpointsUsingPropertiesFile.getUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("****************** User info is displayed ********************************");
    }

    @Test(priority = 3)
    public void testUpdateUser() {

        logger.info("****************** Updating the user info ********************************");
        //update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndpointsUsingPropertiesFile.updateUser(userPayload.getUsername(), userPayload);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 200);

        //Checking data after update
        Response responseAfterUpdate = UserEndpointsUsingPropertiesFile.getUser(userPayload.getUsername());
        responseAfterUpdate.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
        logger.info("****************** User info is updated ********************************");
    }

    @Test(priority = 4)
    public void testDeleteUser() {
        logger.info("****************** Deleting the user ********************************");
        Response response = UserEndpointsUsingPropertiesFile.deleteUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("****************** User is deleted ********************************");
    }
}
