package com.wishlist.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class WishlistDto {
    private Integer id;
    private String title;
    private String eventDate;
    private String description;
    private Integer wishlistId; // Добавлено поле wishlistId

    public void setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }
}


//id	integer($int64)
//title	string
//description	string
//eventDate	string($date-time)
