package com.project.countryInfo.model;

import org.springframework.stereotype.Component;

@Component
public class ResultCountryDetails {

	private boolean isCarDrivingSide;
	private double distance;
	private boolean isSameLanguages;
	
	public boolean isCarDrivingSide() {
		return isCarDrivingSide;
	}
	public void setCarDrivingSide(boolean isCarDrivingSide) {
		this.isCarDrivingSide = isCarDrivingSide;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public boolean isSameLanguages() {
		return isSameLanguages;
	}
	public void setSameLanguages(boolean isSameLanguages) {
		this.isSameLanguages = isSameLanguages;
	}
	
}
