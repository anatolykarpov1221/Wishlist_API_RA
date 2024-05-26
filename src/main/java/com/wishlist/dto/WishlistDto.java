package com.wishlist.dto;
//import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class WishlistDto {
    private Integer id;
    private String title;
    private String  eventDate;
    private String description;
    private List<GiftDto> gifts; // Добавлено

//    public void setWishlistId(Integer wishlistId) {
//        this.wishlistId = wishlistId;
//    }
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
//
//import lombok.Builder;
//import lombok.Data;
//
//import java.util.List;
//
//@Data
//@Builder
//public class WishlistDto {
//    private Long id;
//    private String title;
//    private String description;
//    private String eventDate;
//    private List<Gift> gifts;
//
//    @Data
//    @Builder
//    public static class Gift {
//        private Long id;
//        private String title;
//        private String description;
//        private double price;
//        private String currency;
//        private String url;
//        private String imgUrl;
//        private boolean reserved;
//    }
//}