package com.pingo.dto.profile;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

// Login 후 MainPage에 렌더링 시킬
@Getter
@Setter
public class MainProfileResponseDTO {
    private String userNo;
    private String userName;
    private String age; // DB에는 Birth로 저장되어있어서 Birth값을 받아 age계산 로직 후 값 할당
    private String status = "접속중"; // 추후 웹소켓으로 진행 예정
    private String distance;
    private List<String> images;
}
