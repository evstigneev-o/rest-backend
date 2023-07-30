package qa.guru.restbackend;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import qa.guru.restbackend.domain.LoginInfo;
import qa.guru.restbackend.domain.UserInfo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static specs.GetAllUsers.getAllRequestSpec;

public class BankControllerTest {

    @Test
    public void successfulGetAllUsersTest() {
        UserInfo[] userList = given(getAllRequestSpec)
                .get("getAll")
                .then()
                .statusCode(200)
                .extract().as(UserInfo[].class);
        assertThat(userList.length, equalTo(3));
    }

    @Test
    public void bankControllerTest() {
        LoginInfo user = new LoginInfo("Oleg", "123");
        given()
                .baseUri("http://localhost")
                .port(8080)
                .basePath("user")
                .contentType(ContentType.JSON)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .body(user)
                .post("login")
                .then()
                .statusCode(200)
                .body("userName", equalTo(user.getUserName()));
    }
}
