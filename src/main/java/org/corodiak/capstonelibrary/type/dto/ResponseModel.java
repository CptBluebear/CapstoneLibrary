package org.corodiak.capstonelibrary.type.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseModel {

	private HttpStatus httpStatus;

	private String message;

	private Map<String, Object> data;

	public ResponseModel() {
		data = new HashMap<>();
	}

	public void addData(String name, Object data) {
		this.data.put(name, data);
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
