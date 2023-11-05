<template>
  <div class="greetings">
    <h1 class="green">{{ msg }}</h1>
    <h3>
      You can access past or future weather forecast information by specifying the desired dates and
      location.
    </h3>
    <a-space direction="vertical">
      <a-row justify="start" :gutter="[16, 16]">
        <a-col>
          <a-date-picker
            v-model:value="weatherForecastStore.state.searchParams.start"
            id="start-date-input"
            :format="defaultDateFormat"
            :disabled-date="disabledStartDate"
            :valueFormat="defaultDateFormat"
            :placeholder="'Start date'"
            @change="search"
          />
        </a-col>
        <a-col>
          <a-date-picker
            v-model:value="weatherForecastStore.state.searchParams.end"
            id="end-date-input"
            :format="defaultDateFormat"
            :disabled-date="disabledEndDate"
            :valueFormat="defaultDateFormat"
            :placeholder="'End date'"
            @change="search"
          />
        </a-col>
      </a-row>
      <a-row>
        <a-col>
          <a-auto-complete
            :name="'selected-place'"
            v-model:value="value"
            label-in-value
            :options="placeOptions"
            style="width: 200px"
            :dropdown-match-select-width="252"
            :filterOption="filterOption"
            @select="onSelect"
            @search="search"
          >
            <template #option="item">
              <div class="space-between">
                <span>
                  {{ item.value }}
                </span>
              </div>
            </template>
            <a-input-search
              name="select-place-search-input"
              placeholder="Select your place"
              allow-clear
              enter-button
            ></a-input-search>
          </a-auto-complete>
        </a-col>
      </a-row>
    </a-space>
  </div>
</template>

<script setup lang="ts">
import { weatherForecastService } from '@/service/WeatherForecastService'
import { PlaceResponse } from '@/types/weather/weather.types'
import { onMounted, ref } from 'vue'
import { useWeatherForecastStore } from '@/store/weatherForecastStore'
import { defaultDateFormat } from '@/types/dateFormat'
import dayjs from 'dayjs'
import { Dayjs } from 'dayjs'
import { useNotificationService } from '@/service/NotificationService'

const notificationService = useNotificationService()
const errorFromResponse = notificationService.errorFromResponse
const weatherForecastStore = useWeatherForecastStore()

defineProps<{
  msg: string
}>()

interface PlaceOption {
  value?: string
  label?: PlaceResponse
}

const value = ref<PlaceOption>({ value: '' })
const placeOptions = ref<PlaceOption[]>([])

const filterOption = (input: string, option: PlaceOption) => {
  return option.value.toUpperCase().indexOf(input.toUpperCase()) >= 0
}

const onSelect = async (option: PlaceOption) => {
  weatherForecastStore.state.searchParams.placeId = option.label.id
  loadForecasts()
}

const loadForecasts = () => {
  const start = dayjs(weatherForecastStore.state.searchParams.start)
  const end = dayjs(weatherForecastStore.state.searchParams.end)
  if (end < start) weatherForecastStore.state.searchParams.end = start.format(defaultDateFormat)
  weatherForecastStore.loadForecasts()
}

const disabledStartDate = (current: Dayjs) => {
  return (
    (current && current > dayjs().add(4, 'day')) ||
    (current && current < dayjs().subtract(6, 'month'))
  )
}

const disabledEndDate = (current: Dayjs) => {
  const start = dayjs(weatherForecastStore.state.searchParams.start)
  return (current && current < start) || (current && current > start.add(1, 'month'))
}

const search = async (searchText: string) => {
  if (searchText === '') {
    weatherForecastStore.state.searchParams.placeId = null
  }
  loadForecasts()
}

onMounted(async () => {
  try {
    const placeListResponse = await weatherForecastService.getPlaces()
    placeOptions.value = placeListResponse.data.map((p: PlaceResponse) => ({
      value: p.name,
      label: p
    }))
  } catch (error) {
    errorFromResponse(error)
  }
})
</script>

<style scoped>
h1 {
  font-weight: 500;
  font-size: 2.6rem;
  position: relative;
  top: -10px;
}

h3 {
  font-size: 1.2rem;
}
.greetings {
  margin: 20px 0 20px;
}

.greetings h1,
.greetings h3 {
  text-align: center;
}

@media (min-width: 1024px) {
  .greetings h1,
  .greetings h3 {
    text-align: left;
  }
}
</style>
