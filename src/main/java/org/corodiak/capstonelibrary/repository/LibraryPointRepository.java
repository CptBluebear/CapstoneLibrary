package org.corodiak.capstonelibrary.repository;

import java.util.List;

import org.corodiak.capstonelibrary.type.entity.LocationPoint;
import org.springframework.stereotype.Repository;

public interface LibraryPointRepository {

	List<LocationPoint> findByLocationAndDistance(double longitude, double latitude, int distance);

	LocationPoint save(long locationSeq, String name, double longitude, double latitude);
}
