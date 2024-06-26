package com.harcanjo.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIConsumption {

	public String getData(String urlAddress) {
	    HttpClient client = HttpClient.newHttpClient();
	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create(urlAddress))
	            .build();
	    HttpResponse<String> response = null;
	    try {
	        response = client
	                .send(request, HttpResponse.BodyHandlers.ofString());
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    } catch (InterruptedException e) {
	        throw new RuntimeException(e);
	    }

	    String json = response.body();
	    return json;
	}
}
