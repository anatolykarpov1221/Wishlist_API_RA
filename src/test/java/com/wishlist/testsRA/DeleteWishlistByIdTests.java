package com.wishlist.testsRA;

import static io.restassured.RestAssured.given;

public class DeleteWishlistByIdTests extends TestBase{//   String id;
//
//    @BeforeMethod
//    public void precondition(){
//        ContactDto contactDto = ContactDto.builder()
//                .name("Petr")
//                .lastName("Perviy")
//                .email("petr@spb.com")
//                .phone("1234565478")
//                .address("Berlin")
//                .description("tzar")
//                .build();
//        String message = given()
//                .header(AUTH,TOKEN)
//                .body(contactDto)
//                .contentType(ContentType.JSON)
//                .post("contacts")
//                .then()
//                .assertThat().statusCode(200)
//                .extract().path("message");
//           System.out.println(message);
//        //Contact was added! ID: 01b40aac-2b5f-4f28-8c95-d10bb0db4171
//        String[] split = message.split(": ");
//        id= split[1];
//
//        }
//
//
//    @Test
//    public void deleteContactByIdSuccessTest(){
//
//        given()
//                .header(AUTH, TOKEN)
//                .delete("contacts/" + id)
//                .then()
//                .assertThat().statusCode(200)
//                //.extract().path(("message"));
//        //System.out.println(message);
//                .assertThat().body("message",equalTo("Contact was deleted!"));
//
//    }
//    @Test
//    public void deleteContactByInvalidIdTest() {
//        given()
//                .header(AUTH, TOKEN)
//                .delete("contacts/" + "false_id")
//                .then()
//                .assertThat().statusCode(400)
//            .assertThat().body("message", equalTo("Contact with id: false_id not found in your contacts!"))
//        ;}
//
}
