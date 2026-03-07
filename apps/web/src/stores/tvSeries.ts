import { seriesApi } from '@/lib/api'
import { defineStore } from 'pinia'
import { useCrudStore } from './crudStore'
import { type TvSeries } from '@hls-app/sdk'
import { useRoute } from 'vue-router'
import { computed } from 'vue'

export const useTvSeriesStore = defineStore('tvSeries', () => {
  const route = useRoute()
  const crud = useCrudStore<TvSeries>({ api: seriesApi })

  const selectedTvSeries = computed<TvSeries | undefined>(() => {
    const id = route.params['tvSeriesId'] as string
    const m = crud.items.value.find((el) => el.id === id)
    return m
  })

  return {
    ...crud,
    selectedTvSeries,
  }
})
