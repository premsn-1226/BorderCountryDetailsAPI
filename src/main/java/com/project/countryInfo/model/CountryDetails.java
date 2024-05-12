package com.project.countryInfo.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class CountryDetails {
	
	private JSONArray borders;
	private double latitude;
	private double longitude;
	private ArrayList<String> languages;
	private String carDrivingSide;
	private String code;
	private String officialName;
	
	public JSONArray getBorders() {
		return borders;
	}
	public void setBorders(JSONArray borders) {
		this.borders = borders;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public ArrayList<String> getLanguages() {
		return languages;
	}
	public void setLanguages(ArrayList<String> languages) {
		this.languages = languages;
	}
	public String getCarDrivingSide() {
		return carDrivingSide;
	}
	public void setCarDrivingSide(String carDrivingSide) {
		this.carDrivingSide = carDrivingSide;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOfficialName() {
		return officialName;
	}
	public void setOfficialName(String officialName) {
		this.officialName = officialName;
	}
	@Override
	public String toString() {
		return "CountryDetails [borders=" + borders + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", languages=" + languages + ", carDrivingSide=" + carDrivingSide + ", code=" + code
				+ ", officialName=" + officialName + "]";
	}

}
