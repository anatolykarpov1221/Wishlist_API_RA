package com.wishlist.testsRA.shareLinkController;
import com.wishlist.dto.ShareLinkDto;
import com.wishlist.dto.WishlistDto;
import com.wishlist.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ShareLinkControllerTests extends TestBase {
    @Test
    public void createShareLinkTest() {
        WishlistDto wishlist = WishlistDto.builder()
                .title("WishlistParfum2")
                .eventDate("2025-06-01")
                .description("Example2 Description")
                .build();

        String expectedWishlistTitle = "WishlistParfum2";

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
    public void reserveGiftInShareLinkTest() {
        String uuid = "b36fe08b-4cfd-4de6-81b4-31cb6e3f1031"; // Уникальный идентификатор Share Link
        int giftId = 7; // Id подарка для резервации

        // Ожидаемый код ответа
        int expectedStatusCode = 200;

        // Ожидаемое сообщение
        String expectedMessage = "Gift Reserved Successfully";

        // Отправка PUT запроса для резервации подарка
        Response response = given()
                .header(AUTH, "Bearer " + TOKEN)
                .pathParam("uuid", uuid)
                .pathParam("giftId", giftId)
                .contentType(ContentType.JSON)
                .put("/wishlists/share/{uuid}/reserve/{giftId}")
                .then()
                .log().all()
                .extract().response();

        // Проверка, что ответ не равен null
        if (response != null) {
            JsonPath jsonPath = response.jsonPath();

            // Получение списка подарков из Share Link
            List<Integer> giftIds = jsonPath.getList("sharedGifts.giftId");

            // Проверка, что giftIds не равен null
            if (giftIds != null) {
                // Проверка, что подарок для резервации находится в списке желаемого
                if (!giftIds.contains(giftId)) {
                    Assert.fail("Gift does not belong to the specified wishlist");
                }
                // Проверка статус кода ответа
                int actualStatusCode = response.getStatusCode();
                Assert.assertEquals(expectedStatusCode, actualStatusCode, "Unexpected status code");

                // Проверка сообщения об успешном резервировании
                String responseBody = response.asString();
                Assert.assertTrue(responseBody.contains(expectedMessage), "Reservation message not found");
            } else {
                System.out.println("giftIds is null, unable to retrieve giftIds");
            }
        } else {
            System.out.println("Response is null, unable to process the request");
        }
    }


    @Test
    public void getShareLinkTest() {
        String uuid = "b36fe08b-4cfd-4de6-81b4-31cb6e3f1031"; // Уникальный id Share Link

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
        Assert.assertEquals(expectedStatusCode, actualStatusCode, "incorrect Code");


        String responseBody = response.asString();
        Assert.assertTrue(responseBody.contains(expectedTitle), "incorrect Title");
    }




}

