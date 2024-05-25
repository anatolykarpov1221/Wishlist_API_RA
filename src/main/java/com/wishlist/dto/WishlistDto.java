package com.wishlist.dto;
//import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class WishlistDto {
    private Integer id;
    private String title;
    private String  eventDate;
    private String description;
    private Integer wishlistId; // Добавлено

    public void setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }
}


//id	integer($int64)
//title	string
//description	string
//eventDate	string($date-time)
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Setter
//@ToString
//@Builder
//public class WishlistDto {
//    private String title;
//    private String eventDate;
//    private String description;
//    private List<GiftDto> gifts; // Список подарков
//
//    // Метод для добавления подарка в список подарков
//    public void addGift(GiftDto gift) {
//        if (gifts == null) {
//            gifts = new ArrayList<>();
//        }
//        gifts.add(gift);
//    }
//}