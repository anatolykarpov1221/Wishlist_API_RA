package com.wishlist.testsRA;
import com.wishlist.dto.WishlistDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.lang.Math.log;
import static org.hamcrest.Matchers.equalTo;

public class EditWishlistTests extends TestBase {

    String id;

    @BeforeMethod
    public void precondition(){

            WishlistDto wishlistDto = WishlistDto.builder()
                    .title("WishliistForEdit")
                    .eventDate("2025-05-05")
                    .description("EditFormatField")
                    .build();
            String id = given()
                    .header(AUTH, "Bearer " + TOKEN)
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
    public void editWishlistSuccessTest() {    given()
            .header(AUTH, "Bearer " + TOKEN)
            .contentType(ContentType.JSON)
            .body("{\"title\": \"UpdatedTitle\", \"eventDate\": \"2025-05-05\", \"description\": \"UpdatedDescription\"}")
            .when()
            .put("wishlists/" + id)
            .then()
            .assertThat().statusCode(200)
            //.assertThat().body(equalTo("Wishlist deleted successfully"))
            .assertThat().body(equalTo("Wishlist updated successfully"))
            .log().all();
        System.out.println("Wishlist with ID " + id + " has been successfully updated.");}

    @Test
    public void editWishlistNegativeTest() {
        Response response = given()
                .header(AUTH, "Bearer " + TOKEN)
                .contentType(ContentType.JSON)
                //некорректные данные
                .body("{\"title\": \"UpdatedTitle\", \"eventDate\": \"2025-05-05\", \"description\": \"UpdatedDescription\"}")
                .when()
                .put("wishlists/" + id);

        int statusCode = response.getStatusCode();

        if (statusCode == 400) {
            // Ожидаемый статус код для успешного негативного теста
            System.out.println("Wishlist with ID " + id + " was not successfully updated due to invalid data provided. Status code: " + statusCode);
        } else {
            // Фактический статус код не соответствует ожиданиям
            System.out.println("Error: Unexpected status code. Expected 400, but got: " + statusCode);
        }

        response.then().log().all();
    }


}
