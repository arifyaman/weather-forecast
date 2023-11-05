package com.example.weathercheck;

import com.example.weathercheck.client.impl.XMLWeatherClient;
import com.example.weathercheck.helper.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


class WeatherForecastApplicationTests extends IntegrationTest {
    @SpyBean
    private XMLWeatherClient xmlWeatherClient;

    @Test
    void contextLoadsAndDoesntCallClientGetForecasts() throws JAXBException, URISyntaxException, IOException, InterruptedException {
        verify(xmlWeatherClient, never()).getForecasts();
    }

}
