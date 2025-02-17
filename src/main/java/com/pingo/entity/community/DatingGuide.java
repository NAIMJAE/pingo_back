package com.pingo.entity.community;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DatingGuide {
    private String dgNo;
    private String title;
    private String contents;
    private String thumb;
    private int category;
    private String userNo;
    private int heart;
    private LocalDateTime regDate;
}
