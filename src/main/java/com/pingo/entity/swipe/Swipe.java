package com.pingo.entity.swipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Swipe {
    private String swipeNo;
    private String toUserNo;
    private String fromUserNo;
    private String swipeType;
    private LocalDateTime swipeTime;
    private String swipeState;
}
