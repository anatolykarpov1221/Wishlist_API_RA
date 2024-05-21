package com.wishlist.testsRA;
import static io.restassured.RestAssured.given;

public class EditWishlistTests extends TestBase {
//
//    String id;
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
//        System.out.println(message);
//        //Contact was added! ID: 01b40aac-2b5f-4f28-8c95-d10bb0db4171
//        String[] split = message.split(": ");
//             id= split[1];
//
//    }
//
//
//   @Test
//    public void editContactSuccessTest() {
//        // Assuming the contact with id 'id_to_edit' exists
//        //String id_to_edit = id;
//
//        given()
//                .header(AUTH, TOKEN)
//                .contentType(ContentType.JSON)
//                .body("{\"name\": \"Nikolay\", \"phone\": \"1558997339\"}")
//
//                // JSON как правильно написать для  update?
//                .when()
//                .put("contacts/" + id)
//                .then()
//                .assertThat().statusCode(200);
//
//        System.out.println("Contact with ID " + id + " has been successfully updated.");
//    }
//
//    @Test
//    public void editContactNotFoundTest() {
//
//        String id_not_found = "NON_EXISTENT_ID"+id;
//
//        given()
//                .header(AUTH, TOKEN)
//                .contentType(ContentType.JSON)
//                .body("{ name: Nikolay, phone:1558997339 }")
//                //   ???
//                .when()
//                .put("contacts/" + id_not_found)
//                .then()
//                .assertThat().statusCode(403);
//
//        System.out.println("Attempted to edit a non-existent contact with ID " + id_not_found);
//    }
//
}
