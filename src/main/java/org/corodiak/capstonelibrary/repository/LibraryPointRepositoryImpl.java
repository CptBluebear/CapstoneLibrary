package org.corodiak.capstonelibrary.repository;

import java.util.List;
import java.util.Optional;

import org.corodiak.capstonelibrary.type.entity.LocationPoint;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LibraryPointRepositoryImpl implements LibraryPointRepository {

	private final MongoTemplate mongoTemplate;

	@Override
	public List<LocationPoint> findByLocationAndDistance(double longitude, double latitude, int distance) {
		Criteria criteria = Criteria.where("location")
			.nearSphere(new GeoJsonPoint(new Point(longitude, latitude)))
			.maxDistance(distance);
		Query query = new Query(criteria);
		List<LocationPoint> results = mongoTemplate.find(query, LocationPoint.class);
		return results;
	}

	@Override
	public LocationPoint save(long locationSeq, String name, double longitude, double latitude) {
		LocationPoint locationPoint = new LocationPoint();
		locationPoint.setLocationSeq(locationSeq);
		locationPoint.setName(name);
		locationPoint.setLocation(new GeoJsonPoint(new Point(longitude, latitude)));
		return mongoTemplate.save(locationPoint);
	}

	@Override
	public long delete(long locationSeq) {
		Criteria criteria = Criteria.where("locationSeq").is(locationSeq);
		Query query = new Query(criteria);
		DeleteResult result = mongoTemplate.remove(query, LocationPoint.class);
		return result.getDeletedCount();
	}

	@Override
	public Optional<LocationPoint> findByLocationSeq(long locationSeq) {
		Criteria criteria = Criteria.where("locationSeq").is(locationSeq);
		Query query = new Query(criteria);
		return Optional.ofNullable(mongoTemplate.findOne(query, LocationPoint.class));
	}
}
