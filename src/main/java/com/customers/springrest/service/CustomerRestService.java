package com.customers.springrest.service;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerRestService {
	
	    @Autowired
	    private RestTemplate restTemplate;

	    public String getMessage() {
	        String result;
	        try {
	            String httpResult = restTemplate.getForObject("http://localhost:8090/api/customers", String.class);
	            result = "Message SUCCESS result: " + httpResult;
	        } catch (HttpStatusCodeException e) {
	            result = "Get FAILED with HttpStatusCode: " + e.getStatusCode() + "|" + e.getStatusText();
	        } catch (RuntimeException e) {
	            result = "Get FAILED\n" + ExceptionUtils.getFullStackTrace(e);
	        }
	        return result;
	    }
	    
	    public String postMessage() {
	    	 String result;
		        try {
		        	 String stringRequest = "anything";
		        	 HttpHeaders headers = new HttpHeaders();
		            
		             // Data attached to the request.
		             HttpEntity<String> requestBody = new HttpEntity<>(stringRequest, headers);
		            
		             String httpResult = restTemplate.postForObject("http://localhost:8090/api/customers/create", requestBody, String.class);
		            result = "Message SUCCESS result: " + httpResult;
		        } catch (HttpStatusCodeException e) {
		            result = "Get FAILED with HttpStatusCode: " + e.getStatusCode() + "|" + e.getStatusText();
		        } catch (RuntimeException e) {
		            result = "Get FAILED\n" + ExceptionUtils.getFullStackTrace(e);
		        }
		        return result;
	    }
	    
	    public String putMessage() {
	    	String result;
	        try {
	        	 String stringRequest = "anything";
	        	 HttpHeaders headers = new HttpHeaders();
	            
	             // Data attached to the request.
	             HttpEntity<String> requestBody = new HttpEntity<>(stringRequest, headers);
	           
	            restTemplate.put("http://localhost:8090/api/customers", requestBody, String.class);
	            result = "Message SUCCESS result: resultSuccess";
	        } catch (HttpStatusCodeException e) {
	            result = "Get FAILED with HttpStatusCode: " + e.getStatusCode() + "|" + e.getStatusText();
	        } catch (RuntimeException e) {
	            result = "Get FAILED\n" + ExceptionUtils.getFullStackTrace(e);
	        }
	        return result;
	    }
	    
	    public String deleteMessage() {
	    	String result;
	        try {
	        	 String stringRequest = "anything";
	        	 HttpHeaders headers = new HttpHeaders();
	            
	             // Data attached to the request.
	             HttpEntity<String> requestBody = new HttpEntity<>(stringRequest, headers);
	            
	            restTemplate.delete("http://localhost:8090/api/customers/delete", requestBody, String.class);
	            result = "Message SUCCESS result: resultSuccess";
	        } catch (HttpStatusCodeException e) {
	            result = "Get FAILED with HttpStatusCode: " + e.getStatusCode() + "|" + e.getStatusText();
	        } catch (RuntimeException e) {
	            result = "Get FAILED\n" + ExceptionUtils.getFullStackTrace(e);
	        }
	        return result;
	    }
	    
	    @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
}
