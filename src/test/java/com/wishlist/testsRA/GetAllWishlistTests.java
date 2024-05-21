package com.wishlist.testsRA;

import static io.restassured.RestAssured.given;

public class GetAllWishlistTests extends TestBase{

//    @Test
//    public void getAllContactsSuccessTest(){
//        AllContactsDto contactsDto = given()
//                .header(AUTH, TOKEN)
//                .when()
//                .get("contacts")
//                .then()
//                .assertThat().statusCode(200)
//                .extract().body().as(AllContactsDto.class);
//
//
//
//        for(ContactDto contact: contactsDto.getContacts()){
//            System.out.println(contact.getId()+"***"+contact.getName());
//            System.out.println("=================");
//        }
//    }
//    @Test
//    public void getAllContactsWithoutAuthorizationTest403NOBODY() {
//        given()
//                //.header(AUTH,"BAD")
//                .when()
//                .get("contacts")
//                .then()
//                .assertThat().statusCode(403);
//            //.assertThat().body("message", equalTo(""));
//    }
//
//
//

}
