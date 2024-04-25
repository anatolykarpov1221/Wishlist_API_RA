package com.phonebook.testsRA;

import com.phonebook.dto.AllContactsDto;
import com.phonebook.dto.ContactDto;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetAllContactsTests extends TestBase{

    @Test
    public void getAllContactsSuccessTest(){
        AllContactsDto contactsDto = given()
                .header(AUTH, TOKEN)
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(AllContactsDto.class);



        for(ContactDto contact: contactsDto.getContacts()){
            System.out.println(contact.getId()+"***"+contact.getName());
            System.out.println("=================");
        }
    }
    @Test
    public void getAllContactsWithoutAuthorizationTest403NOBODY() {
        given()
                //.header(AUTH,"BAD")
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(403);
            //.assertThat().body("message", equalTo(""));
    }




}
