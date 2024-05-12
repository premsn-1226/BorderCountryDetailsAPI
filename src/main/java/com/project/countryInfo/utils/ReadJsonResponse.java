package com.project.countryInfo.utils;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.countryInfo.model.BorderCountryDetails;
import com.project.countryInfo.model.CountryDetails;

@Component
public class ReadJsonResponse {

	@Autowired
	private CountryDetails countryDetails;

	@Autowired
	private BorderCountryDetails borderCountryDetails;

	public void readJson(JSONArray json, String state) {

		for (int i = 0; i < json.length(); i++) {
			JSONObject jsonObject = json.getJSONObject(i);

			String officialName = jsonObject.getJSONObject("name").getString("common");
			JSONArray borders = jsonObject.getJSONArray("borders");
			JSONObject languages = jsonObject.getJSONObject("languages");
			String code = jsonObject.getString("cioc");
			String carDrivingSide = jsonObject.getJSONObject("car").getString("side");

			JSONArray latLngArray = jsonObject.getJSONObject("capitalInfo").getJSONArray("latlng");
			double latitude = 0;
			double longitude = 0;
			if (latLngArray.length()!=0) {
				latitude = latLngArray.getDouble(0);
				longitude = latLngArray.getDouble(1);
			}

			Iterator<String> languagesKeys = languages.keys();
			ArrayList<String> languagesList = new ArrayList<>();

			while (languagesKeys.hasNext()) {
				String languageKey = languagesKeys.next();
				languagesList.add(languageKey);
			}

			if (state.equals("country")) {
				countryDetails.setBorders(borders);
				countryDetails.setLatitude(latitude);
				countryDetails.setLongitude(longitude);
				countryDetails.setLanguages(languagesList);
				countryDetails.setCarDrivingSide(carDrivingSide);
				countryDetails.setCode(code);
				countryDetails.setOfficialName(officialName);
			} else {
				borderCountryDetails.setBorders(borders);
				borderCountryDetails.setLatitude(latitude);
				borderCountryDetails.setLongitude(longitude);
				borderCountryDetails.setLanguages(languagesList);
				borderCountryDetails.setCarDrivingSide(carDrivingSide);
				borderCountryDetails.setCode(code);
				borderCountryDetails.setOfficialName(officialName);
			}

		}
	}
}
