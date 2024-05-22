package com.wishlist.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class WishlistDto {
    private String id;
    private String title;
    private String eventDate;
    private String description;
}
//id	integer($int64)
//title	string
//description	string
//eventDate	string($date-time)
