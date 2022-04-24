package org.corodiak.capstonelibrary.service;

import java.util.List;
import java.util.Map;

import org.corodiak.capstonelibrary.type.vo.LocationPointVo;

public interface LocationService {

	boolean addLocation(long locationSeq, String name, double longitude, double latitude);

	LocationPointVo findByLocationSeq(long locationSeq);

	boolean removeLocation(long locationSeq);

	Map<Long, LocationPointVo> findByLocationAndDistance(double longitude, double latitude, int distance);

}
