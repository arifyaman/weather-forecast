package com.example.weathercheck.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class HttpClient {
    private final String url;
    private final java.net.http.HttpClient client;

    protected HttpClient(String url) {
        this.url = url;
        this.client = java.net.http.HttpClient.newHttpClient();
    }

    public HttpResponse<String> get() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
