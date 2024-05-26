package com.wishlist.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ShareLinkDto {
    private Integer id;
    private String uuid;
    private WishlistDto wishlist;
}


//{
    //  "id": 0,
    //  "uuid": "string",
    //  "wishlist": {
    //    "id": 0,
    //    "title": "string",
    //    "description": "string",
    //    "eventDate": "2024-05-25T08:44:03.824Z"
    //  }
    //}

