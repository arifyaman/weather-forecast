package com.example.weathercheck.client.impl;

import com.example.weathercheck.client.HttpClient;
import com.example.weathercheck.client.WeatherClient;
import com.example.weathercheck.client.impl.model.XMLForecasts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

@Slf4j
@Component
public class XMLWeatherClient extends HttpClient implements WeatherClient {

    public XMLWeatherClient(@Value("${app.client.xml.api}") String api, @Value("${app.client.xml.lang}") String lang) {
        super("%s?lang=%s".formatted(api, lang));
    }

    public XMLForecasts getForecasts() throws URISyntaxException, IOException, InterruptedException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLForecasts.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        HttpResponse<String> response = get();
        return (XMLForecasts) unmarshaller
                .unmarshal(new StringReader(response.body()));
    }
}
