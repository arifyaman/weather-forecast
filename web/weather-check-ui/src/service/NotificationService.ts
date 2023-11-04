import { notification } from 'ant-design-vue'

export const useNotificationService = () => {
  const errorFromResponse = (e: any) => {
    const exception = e.response?.data
    if (e.response?.status == 404) {
      return error(
        `Not found ${exception.entity}`,
        `${exception.field} - ${exception.value}`
      )
    } else if (e.response?.status == 400) {
      const message = exception.errors.map((row) => `${row.field}: ${row.message}`).join(', ')

      return error(`Bad request`, message)
    } else if (e.response?.status > 499) {
      return error(`Sorry something went wrong this is from our side`)
    } else {
      return error(
        `Please verify your connection status, or it's possible that the service is presently unavailable.`
      )
    }
  }

  const success = (title: string, description: string = '') => {
    return createNotification('success', title, description)
  }

  const warning = (title: string, description: string = '') => {
    return createNotification('warning', title, description)
  }

  const error = (title: string, description: string = '') => {
    return createNotification('error', title, description, 4.5, 9)
  }

  const createNotification = (
    type: string,
    title: string,
    description: string,
    duration: number = 4.5,
    key: number = Date.now()
  ) => {
    notification[type]({
      key: key,
      message: title,
      description: description,
      duration: duration
    })
  }

  return { createNotification, error, warning, success, errorFromResponse }
}
