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
    private final JAXBContext jaxbContext;
    private final Unmarshaller unmarshaller;

    public XMLWeatherClient(@Value("${app.client.xml.lang}") String lang) {
        super("https://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=%s".formatted(lang));
        try {
            this.jaxbContext = JAXBContext.newInstance(XMLForecasts.class);
            this.unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            log.error("Cannot create XMLClient please check logs for JAXB", e);
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public XMLForecasts getForecasts() throws URISyntaxException, IOException, InterruptedException, JAXBException {
        HttpResponse<String> response = get();
        return (XMLForecasts) unmarshaller
                .unmarshal(new StringReader(response.body()));
    }

}
