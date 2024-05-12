package com.project.countryInfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.countryInfo.service.CountryCodeInfo;
import com.project.countryInfo.service.CountryNameInfo;

@RestController
@RequestMapping("/api/v1")

public class GetCountryDetailsController {

	@Autowired
	private CountryNameInfo countryNameInfo;

	@Autowired
	private CountryCodeInfo countryCodeInfo;

	@GetMapping("/countryDetails")
	
	public List<Map> getCountryDetails(@RequestParam(value = "name") String name) {

		countryNameInfo.getDetails(name);
		List<Map> resultArray = countryCodeInfo.getDetails(name);

		return resultArray;
	}
}
