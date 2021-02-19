package com.ssibongee.daangnmarket.post.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Location {

    private static final int EARTH_RADIUS = 6371;

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;

    /**
     * Haversine 공식을 이용한 거리계산
     * @param location 사용자의 위치정보
     * @return distance
     */
    public double calculateDistance(Location location) {
        double longitudeDistance = toRadian(location.getLongitude() - this.longitude);
        double latitudeDistance = toRadian(location.getLatitude() - this.latitude);

        double a = Math.pow(Math.sin(latitudeDistance / 2), 2)
                + Math.cos(toRadian(location.getLongitude())) * Math.cos(toRadian(this.latitude))
                * Math.sin(longitudeDistance / 2) * Math.sin(latitudeDistance / 2);
        double angularDistance = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return angularDistance * EARTH_RADIUS;
    }

    public double toRadian(double position) {
        return position * Math.PI / 180;
    }
}
