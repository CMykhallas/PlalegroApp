import axios from 'axios'
import { APP_CONFIG } from './config'

export const api = axios.create({
  baseURL: APP_CONFIG.apiBaseUrl,
  timeout: 15_000
})

api.interceptors.request.use((config) => {
  // Add auth headers if needed
  return config
})

api.interceptors.response.use(
  (res) => res,
  (err) => {
    // Centralized error handling
    return Promise.reject(err)
  }
)

export async function fetchContents() {
  const { data } = await api.get('/contents')
  return data as Array<{ id: string; title: string; level: number }>
}
