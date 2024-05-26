package com.wishlist.testsRA.giftController;

import com.wishlist.dto.GiftDto;
import com.wishlist.dto.WishlistDto;
import com.wishlist.testsRA.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GiftControllerTests extends TestBase {


    String id;

    @BeforeMethod(enabled = true)
    public void precondition() {

        WishlistDto wishlistDto = WishlistDto.builder()
                .title("WishliistGiftJonnyWalkerRed")
                .eventDate("2025-06-01")
                .description("NewWishlist")
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
        System.out.println("ID: " + id);
        this.id = id;
    }

    @Test
    public void createGiftSuccessTest() {
        String wishlistId = id;

        WishlistDto wishlist = WishlistDto.builder()
                .title("WishliistGift")
                .eventDate("2025-06-01")
                .description("NewWishlist")
                .build();

        GiftDto giftDto = GiftDto.builder()
                .title("Boss")
                .description("parfum")
                .price(50)
                .currency("USD")
                .url("www.douglas.com")
                .imgUrl("/Users/anatolykarpovMAC15/tools/1.jpg")
                .wishlist(wishlist)
                .reserved(true)
                .build();


        String id = given()
                .header(AUTH, "Bearer " + TOKEN)
                .body(giftDto)
                .contentType(ContentType.JSON)
                .post("/wishlists/" + wishlistId + "/gifts")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().path("id").toString();
        System.out.println("ID: " + id);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/wishlists/" + wishlistId + "/gifts/" + id)
                .then()
                .log().all();

    }

    @Test
    public void getAllGiftsByWishlistIdTest() {
        String wishlistId = "31";    //id;

        given()
                .header(AUTH, "Bearer " + TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .get("/wishlists/" + wishlistId + "/gifts")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }
    @Test
    public void createWishlistAndGiftsAndDeleteGiftByIdTest() {

        String wishlistId = createWishlist();
        String giftId1 = createGift(wishlistId, "Silver Boss");
        String giftId2 = createGift(wishlistId, "Gold Gift");

        deleteGiftById(giftId1);
    }

    private String createWishlist() {
        WishlistDto wishlistDto = WishlistDto.builder()
                .title("MyLitlWishlist")
                .eventDate("2025-06-01")
                .description("New Parfum")
                .build();

        //делаем список
        String wishlistId = given()
                .header(AUTH, "Bearer " + TOKEN)
                .body(wishlistDto)
                .contentType(ContentType.JSON)
                .post("wishlists")
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .extract().path("id").toString();

        return wishlistId;
    }

    private String createGift(String wishlistId, String giftTitle) {
        WishlistDto wishlist = WishlistDto.builder()
                .id(1)
                .title("Example Wishlist")
                .eventDate("2025-06-01")
                .description("New Wishlist Description")
                .build();

        GiftDto giftDto = GiftDto.builder()
                .title(giftTitle)
                .description("New Gift")
                .price(50)
                .currency("USD")
                .url("www.example.com")
                .imgUrl("/path/to/image.jpg")
                .wishlist(wishlist)
                .reserved(true)
                .build();

    // Создаем подарок и возвращаем id
        String giftId = given()
                .header(AUTH, "Bearer " + TOKEN)
                .body(giftDto)
                .contentType(ContentType.JSON)
                .post("/wishlists/" + wishlistId + "/gifts")
                .then()
                .log().all()
                .assertThat().statusCode(200) // Предполагаемый код статуса при успешном создании
                .extract().path("id").toString();

        return giftId;
    }


    private void deleteGiftById(String giftId) {
        given()
                .header(AUTH, "Bearer " + TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .delete("/gifts/" + giftId)
                .then()
                .log().all()
                .assertThat().statusCode(204);

        //
    }

    @Test
    public void updateGiftByIdTest() {
        String wishlistId = "31"; // есть wishlistId
        String giftId = "4"; //  есть giftId для обновления

        GiftDto updatedGiftDto = GiftDto.builder()
                .title("Updated Parfum 200ml")
                .description("Updated description")
                .price(75)
                .currency("USD")
                .url("https://www.updatedgift.com")
                .imgUrl("/path/to/updatedimage.jpg")
                .reserved(false)
                .build();

        given()
                .header(AUTH, "Bearer " + TOKEN)
                .body(updatedGiftDto)
                .contentType(ContentType.JSON)
                .when()
                .put("/gifts/" + giftId)
                .then()
                .log().all()
                .assertThat().statusCode(200); //
    }
    @Test
    public void updateGiftByIdNegativeTest() {
        String wishlistId = "31"; //  есть wishlistId
        String giftId = "67890"; //  есть giftId для обновления

        GiftDto updatedGiftDto = GiftDto.builder()
                .title("Updated Gift")
                .description("Updated description")
                .price(75)
                .currency("USD")
                .url("www.updatedgift.com")
                .imgUrl("/path/to/updatedimage.jpg")
                .reserved(false)
                .build();

        given()
                .header(AUTH, "Bearer " + TOKEN)
                .body(updatedGiftDto)
                .contentType(ContentType.JSON)
                .when()
                .put("/gifts/" + giftId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(404) // Предполагаемый код статуса в случае, если подарок не найден
                .body("message", equalTo("Gift not found")); // Проверка на сообщение "Gift not found"
    }

}
