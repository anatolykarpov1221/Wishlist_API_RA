package com.wishlist.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
    @Setter
    @Getter
    @ToString
    @Builder

    public class GiftDto {
        private Integer id;
        private String title;
        private String description;
        private Number price;
        private String currency;
        private String url;
        private String imgUrl;
        private WishlistDto wishlist; //  тип поля на объект WishlistDto
        private Boolean reserved;
    }

////   id	integer($int64)
//    title	string
//    description	string
//    price	number
//    currency	string
//    url	string
//    imgUrl	string
//    wishlist	Wishlist{
//    id	integer($int64)
//    title	string
//    description	string
//    eventDate	string($date-time)
//    reserved	boolean
