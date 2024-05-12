package com.project.countryInfo.exception;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private String status="Status";
	private String message="Message";

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public ResponseEntity<Map> handleMissingParameterException(MissingServletRequestParameterException ex) {
		String name = ex.getParameterName();

		JSONObject json = new JSONObject();
		json.put(status, ex.getStatusCode().value());
		json.put(message, "Required Argument [" + name + "] Not Correctly Specified");
		
		return new ResponseEntity<Map>(json.toMap(), new LinkedMultiValueMap<>(),HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseBody
	public ResponseEntity<Map> handleNoResourceFoundException(NoResourceFoundException ex) {
		String path = ex.getResourcePath();

		JSONObject json = new JSONObject();
		json.put(status, ex.getStatusCode().value());
		json.put(message, "Page Not Found");
		json.put("path", path);
		return new ResponseEntity<Map>(json.toMap(), new LinkedMultiValueMap<>(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseBody
	public ResponseEntity<Map> handleHttpClientErrorException(HttpClientErrorException ex) {

		JSONObject json = new JSONObject();
		json.put(status, ex.getStatusCode().value());
		json.put(message, "Country Name Not Found");
		return new ResponseEntity<Map>(json.toMap(), new LinkedMultiValueMap<>(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public ResponseEntity<Map> handleIllegalArgumentException(IllegalArgumentException ex) {

		JSONObject json = new JSONObject();
		json.put(status, 500);
		json.put(message, ex.getMessage());
		return new ResponseEntity<Map>(json.toMap(), new LinkedMultiValueMap<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
