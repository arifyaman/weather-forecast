package com.example.weathercheck.domain.forecast.api.v1;

import com.example.weathercheck.helper.IntegrationTest;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.jayway.jsonpath.JsonPath.compile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class WeatherForecastControllerIntegrationTest extends IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private final String url = "/api/v1/weather-forecast";

    @Test
    void whenGetPlacesThenReturnOkWithCorrectBody() throws Exception {
        String responseContent = mockMvc.perform(
                        MockMvcRequestBuilders.get(url + "/places")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JsonPath jsonPath = compile("$[*].id");
        List<String> ids = jsonPath.read(responseContent);
        assertThat(ids).contains(
                "2f68f323-b6b3-4741-b525-e1a6469f6739",
                "16e4a582-b9ed-4705-9326-aeb38d3d9966",
                "47f61e7b-d78d-4c2a-8e6e-fc2d69b8d6fb",
                "dffc6362-b89c-42e8-93b8-1539b42965c7",
                "589e61e8-8e33-4bbf-a6e8-3a20571456f6",
                "d8aaac3c-d133-4414-a4ee-2d3874a8e2ef"
        );

        jsonPath = compile("$[*].name");
        List<String> names = jsonPath.read(responseContent);
        assertEquals(6, names.size());
    }

    @Test
    void whenGetForecastWithNoParamThenReturnOkWithCorrectBody() throws Exception {
        String responseContent = mockMvc.perform(
                        MockMvcRequestBuilders.get(url)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        JsonPath jsonPath = JsonPath.compile("$.forecasts");
        List<Object> forecasts = jsonPath.read(responseContent);
        assertThat(forecasts).isNotNull().isNotEmpty().hasSize(4); // Assumes there are 4 forecasts.

        List<String> forecastDates = JsonPath.read(responseContent, "$.forecasts[*].date");
        List<String> expectedDates = List.of("2023-11-05", "2023-11-06", "2023-11-07", "2023-11-08");
        assertThat(forecastDates).containsExactlyElementsOf(expectedDates);
    }

    @Test
    void whenGetForecastWithStartDateThenReturnOkWithCorrectBody() throws Exception {
        String responseContent = mockMvc.perform(
                        MockMvcRequestBuilders.get(url+"?start=2023-11-06")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        JsonPath jsonPath = JsonPath.compile("$.forecasts");
        List<Object> forecasts = jsonPath.read(responseContent);
        assertThat(forecasts).isNotNull().isNotEmpty().hasSize(3); // Assumes there are 4 forecasts.

        List<String> forecastDates = JsonPath.read(responseContent, "$.forecasts[*].date");
        List<String> expectedDates = List.of("2023-11-06", "2023-11-07", "2023-11-08");
        assertThat(forecastDates).containsExactlyElementsOf(expectedDates);
    }

    @Test
    void whenGetForecastWithEndDateThenReturnOkWithCorrectBody() throws Exception {
        String responseContent = mockMvc.perform(
                        MockMvcRequestBuilders.get(url+"?end=2023-11-07")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        JsonPath jsonPath = JsonPath.compile("$.forecasts");
        List<Object> forecasts = jsonPath.read(responseContent);
        assertThat(forecasts).isNotNull().isNotEmpty().hasSize(3);

        List<String> forecastDates = JsonPath.read(responseContent, "$.forecasts[*].date");
        List<String> expectedDates = List.of("2023-11-05", "2023-11-06", "2023-11-07");
        assertThat(forecastDates).containsExactlyElementsOf(expectedDates);
    }

    @Test
    void whenGetForecastWithStartAndEndDateThenReturnOkWithCorrectBody() throws Exception {
        String responseContent = mockMvc.perform(
                        MockMvcRequestBuilders.get(url+"?start=2023-11-07&end=2023-11-07")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        JsonPath jsonPath = JsonPath.compile("$.forecasts");
        List<Object> forecasts = jsonPath.read(responseContent);
        assertThat(forecasts).isNotNull().isNotEmpty().hasSize(1);

        List<String> forecastDates = JsonPath.read(responseContent, "$.forecasts[*].date");
        List<String> expectedDates = List.of("2023-11-07");
        assertThat(forecastDates).containsExactlyElementsOf(expectedDates);
    }
}