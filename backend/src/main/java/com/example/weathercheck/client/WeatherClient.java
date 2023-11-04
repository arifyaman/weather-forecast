package com.example.weathercheck.client;

import com.example.weathercheck.client.impl.model.XMLForecasts;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

public interface WeatherClient {
    XMLForecasts getForecasts() throws URISyntaxException, IOException, InterruptedException, JAXBException;
}
