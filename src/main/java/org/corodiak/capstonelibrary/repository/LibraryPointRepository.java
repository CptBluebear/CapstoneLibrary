package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import org.corodiak.capstonelibrary.type.entity.LocationPoint;
import org.springframework.stereotype.Repository;

public interface LibraryPointRepository {

	List<LocationPoint> findByLocationAndDistance(double longitude, double latitude, int distance);

	LocationPoint save(long locationSeq, String name, double longitude, double latitude);

	long delete(long locationSeq);

	Optional<LocationPoint> findByLocationSeq(long locationSeq);
}
