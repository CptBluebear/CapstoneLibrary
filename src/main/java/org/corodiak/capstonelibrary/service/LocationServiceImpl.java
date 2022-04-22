package org.corodiak.capstonelibrary.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.corodiak.capstonelibrary.Exception.SearchResultNotExistException;
import org.corodiak.capstonelibrary.repository.LibraryPointRepository;
import org.corodiak.capstonelibrary.type.entity.LocationPoint;
import org.corodiak.capstonelibrary.type.vo.LocationPointVo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

	private final LibraryPointRepository repository;

	@Override
	public boolean addLocation(long locationSeq, String name, double longitude, double latitude) {
		LocationPoint locationPoint = repository.save(locationSeq, name, longitude, latitude);
		return true;
	}

	@Override
	public LocationPointVo findByLocationSeq(long locationSeq) {
		Optional<LocationPoint> locationPoint = repository.findByLocationSeq(locationSeq);
		if(!locationPoint.isPresent()) {
			throw new SearchResultNotExistException();
		}
		return new LocationPointVo(locationPoint.get());
	}

	@Override
	public boolean removeLocation(long locationSeq) {
		long result = repository.delete(locationSeq);
		return result == 1;
	}

	@Override
	public List<LocationPointVo> findByLocationAndDistance(double longitude, double latitude, int distance) {
		List<LocationPoint> locationPointList = repository.findByLocationAndDistance(longitude, latitude, distance);
		List<LocationPointVo> results = locationPointList.stream()
			.map(e -> new LocationPointVo(e))
			.collect(Collectors.toList());
		return results;
	}
}
