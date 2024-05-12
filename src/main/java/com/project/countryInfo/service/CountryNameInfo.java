package com.project.countryInfo.service;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.countryInfo.utils.ReadJsonResponse;

@Service
public class CountryNameInfo {

	@Autowired
	private ApiCallService apiCall;

	@Autowired
	private ReadJsonResponse readJsonResponse;

	public void getDetails(String name) {

		ResponseEntity<String> response = apiCall.fetchCountryNameInfo(name);

		String responseString = response.getBody();
		JSONArray jsonResponse = new JSONArray(responseString);
		readJsonResponse.readJson(jsonResponse, "country");

	}
}
