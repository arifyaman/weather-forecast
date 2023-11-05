<template>
  <div class="overflow">
    <a-skeleton :loading="loadingData || weatherForecastStore.state.loading">
      <a-empty v-if="weatherForecastStore.state.forecastResponse.forecasts.length == 0"
      >
        <template #description>
          <span>
            We couldn't find a forecast that matches the criteria you selected. Please double-check the dates or location you provided.
          </span>
        </template>
      </a-empty>

      <a-space direction="vertical">
        <a-card
          v-for="forecast in weatherForecastStore.state.forecastResponse.forecasts"
          :key="forecast.date"
          style="width: 100%"
          :title="forecast.date"
          :tab-list="forecast.tabs"
          :active-tab-key="forecast.selectedKey"
          @tabChange="
            (key: string) => {
              forecast.selectedKey = key
            }
          "
        >
          <template #customTab="item">
            <span v-if="item.key === 'tab1'">
              {{ item.key }}
            </span>
          </template>

          <p class="centered-text">
            <b>{{ getSelectedDetails(forecast).phenomenon }}</b>
          </p>
          <p>
            <b>Min Temperature:</b> {{ getSelectedDetails(forecast).minTemperature }}°C
          </p>
          <p>
            <b>Max Temperature:</b> {{ getSelectedDetails(forecast).maxTemperature }}°C
          </p>
          <p>{{ getSelectedDetails(forecast).statement }}</p>

          <a-card
            v-if="getSelectedDetails(forecast).placeForecast"
            style="width: 100%"
            :title="getSelectedDetails(forecast).placeForecast.name"
          >
            <p class="centered-text">
              <b>{{ getSelectedDetails(forecast).placeForecast.phenomenon }}</b>
            </p>
            <p v-if="getSelectedDetails(forecast).placeForecast.minTemperature">
              <b>Min Temperature:</b>
              {{ getSelectedDetails(forecast).placeForecast.minTemperature }}°C
            </p>
            <p v-if="getSelectedDetails(forecast).placeForecast.maxTemperature">
              <b>Max Temperature:</b>
              {{ getSelectedDetails(forecast).placeForecast.maxTemperature }}°C
            </p>
          </a-card>
        </a-card>
      </a-space>
    </a-skeleton>
  </div>
</template>
<script lang="ts" setup>
import { onMounted } from 'vue'
import { ref } from 'vue'
import { useWeatherForecastStore } from '@/store/weatherForecastStore'
import { TransformedForecast } from '@/types/weather/weather.types';

const weatherForecastStore = useWeatherForecastStore()

const loadingData = ref<boolean>(true)

const getSelectedDetails = (forecast: TransformedForecast) => {
  return forecast.details[forecast.selectedKey]
}

onMounted(async () => {
  await weatherForecastStore.loadForecasts()

  loadingData.value = false
})
</script>
<style scoped>

.centered-text {
  text-align: center;
}
.overflow {
  max-height: 100vh;
  overflow: scroll;
}
.overflow::-webkit-scrollbar {
  display: none;
}
</style>
