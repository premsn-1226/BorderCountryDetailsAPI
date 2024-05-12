package com.project.countryInfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

import com.project.countryInfo.model.BorderCountryDetails;
import com.project.countryInfo.model.CountryDetails;
import com.project.countryInfo.model.ResultCountryDetails;
import com.project.countryInfo.utils.CalculateDistance;
import com.project.countryInfo.utils.ReadJsonResponse;

@Service
public class CountryCodeInfo {

	@Autowired
	private ApiCallService apiCallService;

	@Autowired
	private ReadJsonResponse readJsonResponse;

	@Autowired
	private CountryDetails countryDetails;

	@Autowired
	private BorderCountryDetails borderCountryDetails;

	@Autowired
	private ResultCountryDetails resultCountryDetails;

	@Autowired
	private CalculateDistance distanceCalculator;

	public List<Map> getDetails(String name) {
		List<Map> resultArray = new ArrayList<>();
		System.out.println(countryDetails.toString());

		if (countryDetails.getOfficialName() != null) {

			JSONArray countryNames = countryDetails.getBorders();
			ArrayList<String> mainCountryLanguage = countryDetails.getLanguages();

			String[] bordersList = new String[countryNames.length()];
			JSONArray jsonArrayResponse = new JSONArray();
			JSONObject jsonObjectResponse = new JSONObject();

			for (int i = 0; i < countryNames.length(); i++) {

				JSONObject countryDataJson = new JSONObject();

				// re-init the values each itr
				resultCountryDetails.setCarDrivingSide(false);
				resultCountryDetails.setSameLanguages(false);
				resultCountryDetails.setDistance(0);
				borderCountryDetails.setLongitude(0);
				borderCountryDetails.setLatitude(0);

				ResponseEntity<String> response = apiCallService.fetchCountryCodeInfo(countryNames.getString(i));

				String responseString = response.getBody();
				JSONArray jsonResponse = new JSONArray(responseString);

				// reading the data
				readJsonResponse.readJson(jsonResponse, "border");
				ArrayList<String> borderCountryLanguage = borderCountryDetails.getLanguages();
				System.out.println(borderCountryDetails.toString());

				// adding names
				bordersList[i] = borderCountryDetails.getOfficialName();

				// validation for same language
				for (String mainLanguage : mainCountryLanguage) {
					for (String borderLanguage : borderCountryLanguage) {
						if (mainLanguage.equals(borderLanguage)) {
							resultCountryDetails.setSameLanguages(true);
						}
					}
				}

				// validation for car driving side
				if (borderCountryDetails.getCarDrivingSide().equals(countryDetails.getCarDrivingSide())) {
					resultCountryDetails.setCarDrivingSide(true);
				} else {
					resultCountryDetails.setCarDrivingSide(false);
				}

				// calling method for distance calculation
				int distance = 0;
				if (countryDetails.getLatitude() != 0 && borderCountryDetails.getLatitude() != 0) {
					distance = distanceCalculator.calculateDistance(countryDetails.getLatitude(),
							countryDetails.getLongitude(), borderCountryDetails.getLatitude(),
							borderCountryDetails.getLongitude());
				}

				countryDataJson.put("officialName", borderCountryDetails.getOfficialName());
				countryDataJson.put("carDrivingSide", resultCountryDetails.isCarDrivingSide());
				countryDataJson.put("officialLanguage", resultCountryDetails.isSameLanguages());
				countryDataJson.put("approxDistance", distance + " Miles");

				jsonArrayResponse.put(countryDataJson);

			}

			jsonObjectResponse.put("borderCountries", bordersList);
			jsonObjectResponse.put("countriesInfo", jsonArrayResponse);

			String resultString = "";
			for (int i = 0; i < bordersList.length; i++) {
				if (i == bordersList.length - 1) {
					resultString += bordersList[i] + ".";
				} else {
					resultString += bordersList[i] + ", ";
				}
			}

			jsonObjectResponse.put("result", "Bordering Countries of " + countryDetails.getOfficialName() + " are " + resultString);

			ResponseEntity<Map> resultResponse = new ResponseEntity<Map>(jsonObjectResponse.toMap(),
					new LinkedMultiValueMap<>(), HttpStatus.OK);

			resultArray.add(resultResponse.getBody());

		}

		return resultArray;

	}
}
