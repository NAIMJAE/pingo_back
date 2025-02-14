package com.pingo.entity.community;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlaceReview {
    private String prNo;
    private String placeName;
    private String thumb;
    private String addressName;
    private String roadAddressName;
    private String userNo;
    private String contents;
    private String category;
    private double latitude;
    private double longitude;
    private int heart;
}
