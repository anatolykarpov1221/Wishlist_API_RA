package com.wishlist.testsRA.giftController;
import com.wishlist.dto.GiftDto;
import com.wishlist.dto.WishlistDto;
import com.wishlist.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GiftControllerDeleteAnottherTest extends TestBase {

        static String giftId; // Статическая переменная для ID

        @BeforeMethod
        public void precondition() {
            WishlistDto wishlist = WishlistDto.builder()
                    .title("WishliistMusic")
                    .eventDate("2025-06-01")
                    .description("Deep Purple")
                    .build();

            String wishlistId = given()
                    .header(AUTH, "Bearer " + TOKEN)
                    .body(wishlist)
                    .contentType(ContentType.JSON)
                    .post("wishlists")
                    .then()
                    .log().all()
                    .assertThat().statusCode(201)
                    .extract().path("id").toString();

            System.out.println("Wishlist ID: " + wishlistId);

            GiftDto giftDto = GiftDto.builder()
                    .title("Machine Head")
                    .description("disk vinil 180gr")
                    .price(50)
                    .currency("USD")
                    .url("www.douglas.com")
                    .imgUrl("/Users/anatolykarpovMAC15/tools/1.jpg")
                    .wishlist(wishlist)
                    .reserved(true)
                    .build();

            giftId = given()
                    .header(AUTH, "Bearer " + TOKEN)
                    .body(giftDto)
                    .contentType(ContentType.JSON)
                    .post("/wishlists/" + wishlistId + "/gifts")
                    .then()
                    .log().all()
                    .assertThat().statusCode(200)
                    .extract().path("id").toString();

            System.out.println("Gift ID: " + giftId);
        }

        @Test
        public void createGiftSuccessTest() {
            precondition(); // Вызов метода precondition() перед созданием подарка

            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/wishlists/" + giftId)
                    .then()
                    .log().all();
        }

        @Test
        private void deleteGiftByIdSuccessTest() {
            precondition(); // Создание предусловия (например, создание списка и получение ID подарка)

            Response response = given()
                    .header(AUTH, "Bearer " + TOKEN)
                    .contentType(ContentType.JSON)
                    .when()
                    .delete("/gifts/" + giftId);

            if (response.statusCode() == 204) {
                System.out.println("Gift with ID " + giftId + " deleted successfully.");
            } else {
                System.out.println("Error deleting gift. Status Code: " + response.statusCode());
                System.out.println("Message: " + response.getBody().asString());

                // Проверка на сообщение об ошибке
                String errorMessage = response.jsonPath().getString("message");
                assertThat("Gift deletion unsuccessful. Error message: " + errorMessage, errorMessage, equalTo("Expected error message"));
            }
        }

    }


