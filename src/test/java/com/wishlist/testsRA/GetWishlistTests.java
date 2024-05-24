package com.wishlist.testsRA;

import com.wishlist.dto.WishlistDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetWishlistTests extends TestBase {

    @Test
    public void getWishlistsSuccessTestId1() {
        Response response = given()
                .header(AUTH, "Bearer " + TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .get("wishlists/1");

        if (response.statusCode() == 200) {
            WishlistDto wishlistsDto = response.as(WishlistDto.class);
            System.out.println(wishlistsDto);

        } else {
            // Обработка ошибки
            System.out.println("Error: " + response.statusCode());
        }
    }

    @Test
    public void getWishlistsSuccessTestId2() {
        Response response = given()
                .header(AUTH, "Bearer " + TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .get("wishlists/2");

        int statusCode = response.statusCode();
        System.out.println("Status Code: " + statusCode);

        if (statusCode == 200) {
            WishlistDto wishlistsDto = response.as(WishlistDto.class);
            System.out.println(wishlistsDto);
        } else {
            System.out.println("Error: " + statusCode);
        }
    }


    @Test
    public void getAllWishlistsSuccessTest() {
        List<WishlistDto> wishlists = given()
                .header(AUTH, "Bearer " + TOKEN)
                .when()
                .get("wishlists")
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract()
                .jsonPath()
                .getList("", WishlistDto.class);

        for (WishlistDto w : wishlists) {
            System.out.println(w);
        }
    }




}