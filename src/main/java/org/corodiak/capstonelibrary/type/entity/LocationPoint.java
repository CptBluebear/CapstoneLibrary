package org.corodiak.capstonelibrary.type.entity;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "CapstoneLibrary")
@Data
public class LocationPoint {

	@Id
	private ObjectId id;

	private String name;

	private Long locationSeq;

	private GeoJsonPoint location;

}
