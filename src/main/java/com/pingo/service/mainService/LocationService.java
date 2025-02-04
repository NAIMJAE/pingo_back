package com.pingo.service.mainService;

import com.pingo.mapper.LocationMapper;
import com.pingo.util.GeoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationMapper locationMapper;
    private final StringRedisTemplate stringRedisTemplate;

    // 유저 위치 저장
    public void updateUserLocation(String userNo, double latitude, double longitude) {
        Point newPoint = new Point(longitude, latitude);

        // 1. Redis에 위치 저장
        stringRedisTemplate.opsForGeo().add("geo:user_locations", newPoint, userNo);

        // 2. 기존 위치 가져오기
        List<Point> existingPoints = stringRedisTemplate.opsForGeo().position("geo:user_locations", userNo);
        Point previousPoint = (existingPoints == null || existingPoints.isEmpty()) ? null : existingPoints.get(0);

        boolean shouldUpdateDatabase = false;
        if (previousPoint != null) {
            double distance = GeoUtils.calculateDistance(previousPoint, newPoint);
            if (distance >= 1.0) { // 1km 이상 이동 시만 DB 저장
                shouldUpdateDatabase = true;
            }
        } else {
            shouldUpdateDatabase = true;
        }

        // 3. 일정 거리 이상 이동한 경우에만 DB 저장
        if (shouldUpdateDatabase) {
            locationMapper.updateUserLocation(userNo, latitude, longitude);
        }
    }


    // 반경 내 유저 검색
    public List<String> getNearbyUsers(double latitude, double longitude, double radiusKm) {
        return locationMapper.getNearbyUsers(latitude, longitude, radiusKm);
    }

}
