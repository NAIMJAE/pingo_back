package com.pingo.controller;

import com.pingo.dto.location.LocationRequest;
import com.pingo.service.mainService.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class LocationController {

    private final LocationService locationService;

    // 위치 업데이트 API
    @PostMapping("/location/update")
    public ResponseEntity<String> updateUserLocation(@RequestBody LocationRequest request) {
        locationService.updateUserLocation(request.getUserNo(), request.getLatitude(), request.getLongitude());
        return ResponseEntity.ok("위치 정보가 저장되었습니다.");
    }

    // 반경 내 유저 검색 API
    @GetMapping("/location/nearby")
    public List<String> getNearbyUsers(@RequestParam double latitude,
                                       @RequestParam double longitude,
                                       @RequestParam double radiusKm) {
        return locationService.getNearbyUsers(latitude, longitude, radiusKm);
    }
}
