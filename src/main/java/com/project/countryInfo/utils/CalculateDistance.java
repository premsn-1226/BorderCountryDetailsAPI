package com.project.countryInfo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CalculateDistance {
	
	@Value("${countryInfo.earthRadius}")
	private double EARTH_RADIUS_MILES;

	public int calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convert latitude and longitude from degrees to radians
		lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
 
        // Haversine formula 
        double dlon = lon2 - lon1; 
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                 + Math.cos(lat1) * Math.cos(lat2)
                 * Math.pow(Math.sin(dlon / 2),2);
             
        double c = 2 * Math.asin(Math.sqrt(a));

        // Calculate the distance
        double distance = EARTH_RADIUS_MILES * c;
        
        int resultDistance = (int) distance;

        return resultDistance;
    }
}
