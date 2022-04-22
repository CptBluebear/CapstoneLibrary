package org.corodiak.capstonelibrary.type.vo;

import org.corodiak.capstonelibrary.type.entity.LocationPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class LocationPointVo {
	private Long locationSeq;
	private String name;
	private GeoJsonPoint location;

	public LocationPointVo(LocationPoint locationPoint) {
		this.locationSeq = locationPoint.getLocationSeq();
		this.name = locationPoint.getName();
		this.location = locationPoint.getLocation();
	}
}
