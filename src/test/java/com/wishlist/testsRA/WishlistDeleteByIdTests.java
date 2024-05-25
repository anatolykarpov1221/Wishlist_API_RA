package com.wishlist.testsRA;

import com.wishlist.dto.WishlistDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WishlistDeleteByIdTests extends TestBase {

    String id;


    @BeforeMethod
    public void precondition() {
        WishlistDto wishlistDto = WishlistDto.builder()
                .title("Petr")
                .eventDate("2024-11-11")
                .description("petr@spb.com")
                .build();
        String id = given()
                .header(AUTH, "Bearer " + TOKEN)
//                .header(AUTH,"Bearer"+TOKEN)
                .body(wishlistDto)
                .contentType(ContentType.JSON)
                .post("wishlists")
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .extract().path("id").toString();
        System.out.println("ID: " + id);

        this.id = id;

    }

    @Test
    public void deleteWishlistByIdSuccessTest1() {
        RestAssured.registerParser("text/plain", Parser.JSON);
        given()
                .header(AUTH, "Bearer " + TOKEN)
                .delete("wishlists/" + id)
                .then()
                .assertThat().statusCode(200)
                .contentType("text/plain;charset=UTF-8")
                .assertThat().body(equalTo("Wishlist deleted successfully"));
    }


    @Test
    public void deleteWishlistByInvalidIdTest() {
        given()
                .header(AUTH, "Bearer " + TOKEN)
                .delete("wishlists/" + 700)
                .then()
                .assertThat().statusCode(404)
                .contentType("text/plain;charset=UTF-8")
                .assertThat().body(equalTo("Wishlist not found"));
    }

    @Test
    public void deleteWishlistByInvalidIdFormatTest() {
        given()
                .header(AUTH, "Bearer " + TOKEN)
                .delete("wishlists/" + "A")
                .then()
                .assertThat().statusCode(500)
                .contentType("text/plain;charset=UTF-8")
                .assertThat()
                .body(equalTo("Unexpected error occurred: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: \"A\""));
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


