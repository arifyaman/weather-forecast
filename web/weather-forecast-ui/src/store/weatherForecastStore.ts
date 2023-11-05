import { defineStore } from 'pinia'
import { ref } from 'vue'
import { weatherForecastService } from '@/service/WeatherForecastService'
import {
  ForecastStatementType,
  WeatherForecastResponse,
  WeatherForecastRequest,
  WeatherForecastStatementResponse,
  TransformedWeatherForecastResponse,
} from '@/types/weather/weather.types'
import { useNotificationService } from '@/service/NotificationService'

export const useWeatherForecastStore = defineStore('weatherForecastStore', () => {
  const notificationService = useNotificationService()
  const errorFromResponse = notificationService.errorFromResponse

  const state = ref({
    forecastResponse: ref<TransformedWeatherForecastResponse>({}),
    searchParams: ref<WeatherForecastRequest>({}),
    loading: false
  })

  const transformDetailsToMap = (
    data: WeatherForecastResponse
  ): TransformedWeatherForecastResponse => {
    const transformedData: TransformedWeatherForecastResponse = {
      forecasts: data.forecasts.map((forecast) => {
        const detailsMap: Record<string, WeatherForecastStatementResponse> = {}
        const tabs = []
        forecast.details.forEach((detail) => {
          tabs.push({ key: detail.type, tab: getTabName(detail.type) })
          detailsMap[detail.type] = detail
        })
        return {
          date: forecast.date,
          tabs: tabs,
          selectedKey: tabs[0].key,
          details: detailsMap
        }
      })
    }
    return transformedData
  }

  const getTabName = (type: ForecastStatementType): string => {
    return type === 'DAY' ? 'Day' : 'Night'
  }

  const reset = () => {
    ;(state.value.loading = false), (state.value.modal = false)
  }

  const loadForecasts = async () => {
    state.value.loading = true
    try {
      const response = await weatherForecastService.getForecast(state.value.searchParams)
      state.value.forecastResponse = transformDetailsToMap(response.data)

      state.value.selected = response.data
      state.value.loading = false
    } catch (e) {
      errorFromResponse(e)
      state.value.loading = false
    }
  }

  return { state, loadForecasts, reset }
})
