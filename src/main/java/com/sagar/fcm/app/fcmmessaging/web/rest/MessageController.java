package com.sagar.fcm.app.fcmmessaging.web.rest;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sagar.fcm.app.fcmmessaging.constants.Constants;

@RestController
@RequestMapping(value="/api",consumes=MediaType.APPLICATION_JSON_VALUE,produces={MediaType.APPLICATION_JSON_VALUE,MediaType.TEXT_HTML_VALUE})
public class MessageController {
	
	
	@PostMapping(value="/test")
	public boolean test(){
		return true;
	}
	
	
	@PostMapping(value="/notify",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String sendNotification(@RequestBody Object data){
		
		RestTemplate restClient 	= 	new RestTemplate();
		HttpHeaders headers 		=   new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(Constants.TEXT_AUTHORIZATION, Constants.FCM_AUTHORIZATION_KEY);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(data,headers);
		
		ResponseEntity<String> response = restClient.postForEntity(Constants.FCM_SERVER_API_URL, httpEntity, String.class);
		
		System.out.println("******************** NOTIFICATION SUBMITTED*******************");
		
		return response.getBody();
	}
	

}
