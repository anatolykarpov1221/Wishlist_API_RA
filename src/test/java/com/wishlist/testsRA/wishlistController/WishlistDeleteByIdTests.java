package com.wishlist.testsRA.wishlistController;

import com.wishlist.dto.WishlistDto;
import com.wishlist.testsRA.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class WishlistDeleteByIdTests extends TestBase {

    private static String wishlistId;
    ;
    @BeforeMethod
    public void precondition() {
        if (wishlistId == null) {
            WishlistDto wishlistDto = WishlistDto.builder()
                    .title("Petr")
                    .eventDate("2024-11-11")
                    .description("petr@spb.com")
                    .build();
            wishlistId = given()
                    .header(AUTH, "Bearer " + TOKEN)
                    .body(wishlistDto)
                    .contentType(ContentType.JSON)
                    .post("wishlists")
                    .then()
                    .log().all()
                    .assertThat().statusCode(201)
                    .extract().path("id").toString();
            System.out.println("ID: " + wishlistId);
            //this.wishlistId=wishlistId;
        }
    }



    @Test
    public void deleteWishlistByIdSuccessTest() {
        precondition();
        //String wishlistId = "27";
        Response response = given()
                .header(AUTH, "Bearer " + TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .delete("/wishlists/" + wishlistId);

        if (response.statusCode() == 204) {
            System.out.println("Wishlist with ID " + wishlistId + " deleted successfully.");
        } else {
            System.out.println("Error deleting wishlist. Status Code: " + response.statusCode());
            System.out.println("Message: " + response.getBody().asString());
        }
    }





    @Test
    public void deleteWishlistByInvalidIdTest() {
        given()
                .header(AUTH, "Bearer " + TOKEN)
                .delete("wishlists/" + 700)
                .then()
                .assertThat().statusCode(404)
                .contentType("application/json")
                .assertThat().body("message", equalTo("Wishlist with id 700 does not exist"));
    }



    @Test
    public void deleteWishlistByInvalidIdFormatTest() {
        given()
                .header(AUTH, "Bearer " + TOKEN)
                .delete("wishlists/" + "A")
                .then()
                .assertThat().statusCode(500)
                .contentType("application/json")
                .assertThat()
                .body("message", equalTo("Unexpected error occurred: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: \"A\""));
    }


}

    //this is bag
    //(BUG REPORT) для сервера. Ошибка возникает при попытке удалить ресурс с недопустимым форматом идентификатора (в данном случае строковое значение "A").

    //1. Описание проблемы: при попытке удалить ресурс с недопустимым форматом идентификатора сервер возвращает ошибку 500, вместо ожидаемого сообщения "Wishlist not found".
    //
    //2. Шаги для воспроизведения:
    //   - Отправить DELETE-запрос на URL "wishlists/A" с заголовком AUTH.
    //
    //3. Ожидаемое поведение: ожидалось получить статус-код 500 и сообщение "Wishlist not found".
    //
    //4. Фактическое поведение:  сервер возвращает статус-код 500 и сообщение "Unexpected error occurred: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: "A"".
    //
    //5. Версия сервера
    //
    //6. Логи и трассировка стека:
    //
    //7. Ожидаемое решение:  ожидается исправление ошибки, чтобы сервер корректно обрабатывал запросы с недопустимым форматом идентификатора и возвращал соответствующее сообщение.


