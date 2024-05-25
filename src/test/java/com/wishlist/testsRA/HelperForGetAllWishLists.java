package com.wishlist.testsRA;

import com.wishlist.dto.WishlistDto;

import java.util.List;

import static io.restassured.RestAssured.given;

public class HelperForGetAllWishLists extends TestBase {

    static void getAllWishLists() {
        List<WishlistDto> wishlists = given()
                .header(AUTH, "Bearer " + TOKEN)
                .when()
                .get("wishlists")
                .then()
                .log().all()
                .extract()
                .jsonPath()
                .getList("", WishlistDto.class);

        if (wishlists != null && !wishlists.isEmpty()) {
            for (WishlistDto w : wishlists) {
                System.out.println(w);
            }
        } else {
            System.out.println("No wishlists found.");
        }
    }
}

