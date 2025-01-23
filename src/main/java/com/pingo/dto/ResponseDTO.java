package com.pingo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 공통 Response DTO

@Getter
@RequiredArgsConstructor(staticName = "of")
public class ResponseDTO<T> {
    private final String resultCode; // 1 성공, 2 실패
    private final String message;
    private final T data;
}
