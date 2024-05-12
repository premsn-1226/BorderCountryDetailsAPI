package com.project.countryInfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiCallService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${countryInfo.fetchUrl}")
	private String apiUrl;

	@Value("${countryInfo.fetchCountryNameApi}")
	private String countryNameApi;

	@Value("${countryInfo.searchCriteria}")
	private String searchCtr;

	@Value("${countryInfo.fetchCountryCodeApi}")
	private String countryCodeApi;
	
	private static String url;

	public ResponseEntity<String> fetchCountryNameInfo(String name) {

		url = apiUrl + countryNameApi + name + "?" + searchCtr;
		System.out.println(url);
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return response;
	}

	public ResponseEntity<String> fetchCountryCodeInfo(String name) {
		
		url = apiUrl + countryCodeApi + name + "&" + searchCtr;
		System.out.println(url);
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return response;
	}
}
