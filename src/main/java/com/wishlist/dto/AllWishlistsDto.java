package com.wishlist.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Setter
@Getter
@ToString


public class AllWishlistsDto {
    private List<WishlistDto> wishlists;

}
