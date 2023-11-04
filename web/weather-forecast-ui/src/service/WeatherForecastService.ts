import http from '@/http-common'
import type { AxiosResponse } from 'axios'
import type { WeatherForecastResponse, WeatherForecastRequest, PlaceResponse } from '@/types/weather/weather.types'

const context = '/v1/weather-forecast'

export const weatherForecastService = {
  getForecast: (
    request: WeatherForecastRequest
  ): Promise<AxiosResponse<WeatherForecastResponse>> => {
    return http.get<WeatherForecastResponse>(context, { params: request })
  },
  getPlaces: (
  ): Promise<AxiosResponse<PlaceResponse[]>> => {
    return http.get<PlaceResponse[]>(`${context}/places`)
  }
}
