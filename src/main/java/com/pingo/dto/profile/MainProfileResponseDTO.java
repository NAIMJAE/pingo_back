package com.pingo.dto.profile;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

// Login 후 MainPage에 렌더링 시킬
@Getter
@Setter
public class MainProfileResponseDTO {
    private String userNo;
    private String userName;
    private String age; // DB에는 Birth로 저장되어있어서 Birth값을 받아 age계산 후 값 할당
    private String status = "접속중"; // 추후 웹소켓으로 진행 예정
    private String distance;

    // 가공이 필요한 컬럼
    private String images;
    private String userBirth;

    // 만나이 계산하는 메서드 (DB에서 연산줄이기) 나중에 리팩터링 디벨롭
//    public String calculateAge(String birthDateStr) {
//        if (birthDateStr == null || birthDateStr.isEmpty()) {
//            return 0;
//        }
//
//        // 🎯 "1995-12-12 00:00:00.000" → "yyyy-MM-dd" 형식으로 변환
//        LocalDate birthDate = LocalDate.parse(birthDateStr.substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        LocalDate today = LocalDate.now();
//        int calculatedAge = Period.between(birthDate, today).getYears();
//
//        // 🎯 생일이 지나지 않았다면 1 빼기
//        if (today.getMonthValue() < birthDate.getMonthValue() ||
//                (today.getMonthValue() == birthDate.getMonthValue() && today.getDayOfMonth() < birthDate.getDayOfMonth())) {
//            calculatedAge -= 1;
//        }
//
//        return calculatedAge;
//    }

    // images를 List<String>으로 변환하는 유틸리티 메서드 (, 으로 구분된 이미지 나눠서 리스트에 삽입)
    public List<String> getImagesAsList() {
        return (images == null || images.isEmpty()) ? List.of() : Arrays.asList(images.split(","));
    }
}
