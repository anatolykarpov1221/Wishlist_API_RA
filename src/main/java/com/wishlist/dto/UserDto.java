package com.wishlist.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
