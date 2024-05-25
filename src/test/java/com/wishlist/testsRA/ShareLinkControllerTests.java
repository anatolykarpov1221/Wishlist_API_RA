package com.wishlist.testsRA;
import com.wishlist.dto.ShareLinkDto;
import com.wishlist.dto.WishlistDto;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ShareLinkControllerTests extends TestBase {
    @Test
    public void createShareLinkTest() {
        WishlistDto wishlist = WishlistDto.builder()
                .title("WishlistParfum")
                .eventDate("2025-06-01")
                .description("Example Description")
                .build();

        String expectedWishlistTitle = "WishlistParfum";

        // извлечение id
        String wishlistId = given()
                .header(AUTH, "Bearer " + TOKEN)
                .body(wishlist)
                .contentType(ContentType.JSON)
                .post("wishlists")
                .then()
                .assertThat().statusCode(201)
                .extract().path("id").toString();

        // Подготовка  ShareLinkDto
        ShareLinkDto shareLinkDto = ShareLinkDto.builder()
                .id(1)
                .uuid("test-uuid")
                .wishlist(wishlist)
                .build();

        // Создание Share Link
        Response response = given()
                .header(AUTH, "Bearer " + TOKEN)
                .pathParam("wishlistId", wishlistId)
                .body(shareLinkDto)
                .contentType(ContentType.JSON)
                .post("/wishlists/{wishlistId}/share")
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response();

        //  JSON объект из тела ответа
        JsonPath jsonPath = response.jsonPath();

        // Проверка тела ответа
        String actualWishlistTitle = jsonPath.getString("wishlist.title");
        Assert.assertEquals(expectedWishlistTitle, actualWishlistTitle, "Wrong title Wishlist");
    }



    @Test
    public void reserveGiftInShareLinkNegativeTest() {
        String uuid = "d5eae987-a5c2-46f5-b09e-0e2a2597df79"; // Уникальный идентификатор Share Link
        int giftId = 2; // id подарка для резервации

        // Ожидаемый  код
        int expectedStatusCode = 403;

        String expectedMessage = "Gift Reserved Successfully";

        // Отправка PUTа для резервации подарка
        Response response = given()
                .header(AUTH, "Bearer " + TOKEN)
                .pathParam("uuid", uuid)
                .pathParam("giftId", giftId)
                .contentType(ContentType.JSON)
                .put("/wishlists/share/{uuid}/reserve/{giftId}")
                .then()
                .log().all()
                .extract().response();

        // Проверка  кода
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode, "Gift does not belong to the specified wishlist");

        // Проверка сообщения
        String responseBody = response.asString();
       // Assert.assertTrue(responseBody.contains(expectedMessage), "Gift not found");
    }
    @Test
    public void getShareLinkTest() {
        String uuid = "96350395-c39a-47e1-92e4-07df803c6ec0"; // Уникальный id Share Link

        int expectedStatusCode = 200;

        // Ожидаемый (title) для Share Link
        String expectedTitle = "WishlistParfum";

        Response response = given()
                .header(AUTH, "Bearer " + TOKEN)
                .pathParam("uuid", uuid)
                .get("/wishlists/share/{uuid}")
                .then()
                .log().all()
                .extract().response();


        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode, "Неверный статус код ответа.");

        // Проверка (title) в ответе
        String responseBody = response.asString();
        Assert.assertTrue(responseBody.contains(expectedTitle), "Неверный заголовок в ответе.");
    }




}

