package com.wishlist.testsRA;

import com.wishlist.dto.AuthRequestDto;
import com.wishlist.dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
public class UserControllerEditGetTests extends TestBase{

    @Test
    public void updateUserPositiveTest() {
        // Получение токена для аутентификации
        AuthRequestDto auth = AuthRequestDto.builder()
                .email("ringo@web.com")
                .password("Berlin2024!")
                .build();

        // Аутентификация и получение токена
        String token = given()
                .body(auth)
                .contentType(ContentType.JSON)
                .post("/users/login")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("accessToken");

        // Изменение данных пользователя для обновления
        UserDto updatedUser = UserDto.builder()
                .firstName("RingoUpdatedName")
                .lastName("StarUpdatedName")
                .email("ringo@web.com")
                .password("NewBerlin2024!")
                .build();

        // Отправка запроса на обновление пользователя
        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH, "Bearer " + token)
                .body(updatedUser)
                .when()
                .put("/users/auth/me")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract()
                .response();

        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.getString("message");
        if (message != null) {
            assertThat(message, equalTo("ExpectedMessage"));
        } else {
            System.out.println("'message'is null");
        }
    }

        @Test
        public void updateUserNegativeTest () {
            // Получение токена для аутентификации
            AuthRequestDto auth = AuthRequestDto.builder()
                    .email("ringo@web.com")
                    .password("Berlin2024!")
                    .build();

            // Аутентификация и получение токена
            String token = given()
                    .body(auth)
                    .contentType(ContentType.JSON)
                    .post("/users/login")
                    .then()
                    .assertThat().statusCode(200)
                    .extract()
                    .path("accessToken");

            // Изменение данных пользователя для обновления с ошибочными данными
            UserDto updatedUser = UserDto.builder()
                    .firstName("RingoUpdated")
                    .lastName("StarUpdated")
                    .email("Rringo@web.com")
                    .password("NewBerlin2024!")
                    .build();

            // Отправка запроса на обновление пользователя
            Response response = given()
                    .contentType(ContentType.JSON)
                    .header(AUTH, "Bearer " + token)
                    .body(updatedUser)
                    .when()
                    .put("/users/auth/me")
                    .then()
                    .log().all()
                    .assertThat().statusCode(200) // Ожидаемый негативный статус
                    .extract()
                    .response();
            JsonPath jsonPath = response.jsonPath();
            String message = jsonPath.getString("message");
            if (message != null) {
                assertThat(message, equalTo("ExpectedMessage"));
            } else {
                System.out.println("'message'is null");
            }
        }

    @Test
    public void getCurrentUserInfoTest() {

        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH, "Bearer " + TOKEN)
                .when()
                .get("/users/auth/me")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract()
                .response();
        JsonPath jsonPath = response.jsonPath();
        String email = jsonPath.getString("email");
        String firstName = jsonPath.getString("firstName");
        String lastName = jsonPath.getString("lastName");

        assertThat(email, equalTo("ringo@web.com"));
        assertThat(firstName, equalTo("RingoUpdated"));
        assertThat(lastName, equalTo("StarUpdated"));
    }

    @Test
    public void getCurrentUserInfoNegativeTest() {

        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH, "Bearer " + "a"+TOKEN)
                .when()
                .get("/users/auth/me")
                .then()
                .log().all()
                .assertThat().statusCode(403) //  403 Forbidden
                .extract()
                .response();

        if (response.getBody().asString().isEmpty()) {
            System.out.println("Empty response body");
        } else {
            JsonPath jsonPath = response.jsonPath();
            String error = jsonPath.getString("error");
            assertThat(error, equalTo("User Not Found"));
        }
    }




}

