import { z } from 'zod'

export const ForecastStatementType = z.enum(['DAY', 'NIGHT'])
export type ForecastStatementType = z.infer<typeof ForecastStatementType>

export const WeatherForecastRequest = z.object({
  start: z.string().nullable(),
  end: z.string().nullable(),
  type: ForecastStatementType.nullable(),
  placeId: z.string().nullable()
})
export type WeatherForecastRequest = z.infer<typeof WeatherForecastRequest>

export const PlaceResponse = z.object({
  id: z.string(),
  name: z.string()
})
export type PlaceResponse = z.infer<typeof PlaceResponse>

export const PlaceForecastResponse = z.object({
  name: z.string(),
  phenomenon: z.string(),
  minTemperature: z.number(),
  maxTemperature: z.number()
})
export type PlaceForecastResponse = z.infer<typeof PlaceForecastResponse>

export const WeatherForecastStatementResponse = z.object({
  type: ForecastStatementType,
  phenomenon: z.string(),
  statement: z.string(),
  minTemperature: z.number(),
  maxTemperature: z.number(),
  placeForecast: PlaceForecastResponse.nullable()
})
export type WeatherForecastStatementResponse = z.infer<typeof WeatherForecastStatementResponse>

export const GroupedForecast = z.object({
  date: z.string(),
  details: z.array(WeatherForecastStatementResponse)
})
export type GroupedForecast = z.infer<typeof GroupedForecast>

export const WeatherForecastResponse = z.object({
  forecasts: z.array(GroupedForecast)
})
export type WeatherForecastResponse = z.infer<typeof WeatherForecastResponse>
