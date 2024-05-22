package com.wishlist.testsRA;

import com.wishlist.dto.WishlistDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static javax.print.attribute.standard.MediaSizeName.A;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteWishlistByIdTests extends TestBase{

    String id;


    @BeforeMethod
    public void precondition(){
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
           System.out.println("ID: " +id);

        this.id = id;

        }
    @Test
    public void deleteContactByIdSuccessTest1() {
        RestAssured.registerParser("text/plain", Parser.JSON); // Register a custom parser
        given()
                .header(AUTH, "Bearer " + TOKEN)
                .delete("wishlists/" + id)
                .then()
                .assertThat().statusCode(200)
                .contentType("text/plain;charset=UTF-8")
                .assertThat().body(equalTo("Wishlist deleted successfully"));
    }


    @Test
    public void deleteContactByInvalidIdTest() {
        given()
                .header(AUTH, "Bearer " + TOKEN)
                .delete("wishlists/" + 700)
                .then()
                .assertThat().statusCode(404)
                .contentType("text/plain;charset=UTF-8")
            .assertThat().body(equalTo("Wishlist not found"));
    }
    @Test
    public void deleteContactByInvalidIdFormatTest() {
        given()
                .header(AUTH, "Bearer " + TOKEN)
                .delete("wishlists/" + "A")
                .then()
                .assertThat().statusCode(500)
                .contentType("text/plain;charset=UTF-8")
                .assertThat()
                .body(equalTo("Unexpected error occurred: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: \"A\""));
    }
    //this is bag
    //Да, вы можете оформить это как отчет об ошибке (BUG REPORT) для сервера. Ошибка возникает при попытке удалить ресурс с недопустимым форматом идентификатора (в данном случае строковое значение "A").
    //
    //В отчете об ошибке вы можете указать следующую информацию:
    //
    //1. Описание проблемы: Указать, что при попытке удалить ресурс с недопустимым форматом идентификатора сервер возвращает ошибку 500, вместо ожидаемого сообщения "Wishlist not found".
    //
    //2. Шаги для воспроизведения: Указать шаги, которые приводят к возникновению ошибки, например:
    //   - Отправить DELETE-запрос на URL "wishlists/A" с заголовком AUTH.
    //
    //3. Ожидаемое поведение: Указать, что ожидалось получить статус-код 500 и сообщение "Wishlist not found".
    //
    //4. Фактическое поведение: Указать, что сервер возвращает статус-код 500 и сообщение "Unexpected error occurred: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: "A"".
    //
    //5. Версия сервера и другая контекстная информация: Указать версию сервера, используемые библиотеки и другую контекстную информацию, которая может быть полезной для разработчиков при исправлении ошибки.
    //
    //6. Логи и трассировка стека: Если у вас есть доступ к логам или трассировке стека, вы можете приложить их к отчету об ошибке для дополнительной информации.
    //
    //7. Ожидаемое решение: Указать, что ожидается исправление ошибки, чтобы сервер корректно обрабатывал запросы с недопустимым форматом идентификатора и возвращал соответствующее сообщение.
    //
    //При оформлении отчета об ошибке старайтесь быть максимально ясными и информативными, чтобы разработчики могли легко воспроизвести и исправить проблему.
}
