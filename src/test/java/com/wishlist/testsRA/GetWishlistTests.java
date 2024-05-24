package com.wishlist.testsRA;

import com.wishlist.dto.WishlistDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static com.wishlist.testsRA.Helper.getAllWishLists;
import static io.restassured.RestAssured.given;

public class GetWishlistTests extends TestBase {

    @Test
    public void getWishlistsSuccessTestId1() {
        String wishlistId = "27";
        Response response = given()
                .header(AUTH, "Bearer " + TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .get("/wishlists/" + wishlistId);

        if (response.statusCode() == 200) {
            WishlistDto wishlistsDto = response.as(WishlistDto.class);
            if (wishlistsDto != null) {
                System.out.println(wishlistsDto);
            } else {
                System.out.println("Wishlist not found.");
            }
        } else {
            // Обработка ошибки
            System.out.println("Error: " + response.statusCode());
            System.out.println("Message: " + response.getBody().asString());
        }
    }


    @Test
    public void getWishlistsNegativeTestId() {
        String wishlistId = "129";
        Response response = given()
                .header(AUTH, "Bearer " + TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .get("/wishlists/" + wishlistId)
                .then()
                .log().all()
                .extract().response(); // Заменяем .as() на .response() для работы с Response

        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);

        if (statusCode == 200) {
            WishlistDto wishlistsDto = response.as(WishlistDto.class);
            System.out.println(wishlistsDto);
        } else {
            // Обрабатываем случай ошибки с кодом статуса не равным 200
            System.out.println("Error: " + statusCode);
            System.out.println("Message: " + response.getBody().asString());
        }
    }



    @Test

    public void getAllWishlistsSuccessTest() {
        getAllWishLists();
    }



}