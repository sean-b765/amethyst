<script setup lang="ts">
import { storeToRefs } from 'pinia'
import MediaCard from '@/components/MediaCard.vue'
import { AlertCircle } from 'lucide-vue-next'
import Empty from '@/components/ui/empty/Empty.vue'
import EmptyHeader from '@/components/ui/empty/EmptyHeader.vue'
import EmptyMedia from '@/components/ui/empty/EmptyMedia.vue'
import EmptyTitle from '@/components/ui/empty/EmptyTitle.vue'
import EmptyContent from '@/components/ui/empty/EmptyContent.vue'
import Button from '@/components/ui/button/Button.vue'
import { useTvSeriesStore } from '@/stores/tvSeries'
import { Media } from '@hls-app/sdk'
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { getImage } from '@/lib/utils'
import moment from 'moment'
import { truncate } from 'lodash'

const route = useRoute()
const router = useRouter()
const tvSeriesStore = useTvSeriesStore()
const { selectedTvSeries } = storeToRefs(tvSeriesStore)

function getEpisodeHref(episode: Media) {
  return router.resolve({ name: 'WatchMedia', params: { mediaId: episode.id } }).href
}

const season = computed(() => {
  const { season } = route.params

  return selectedTvSeries.value?.tvSeasons?.find((tvSeason) => String(tvSeason.season) === season)
})

const episodes = computed(() => {
  return season.value?.mediaItems ?? []
})
</script>

<template>
  <div
    class="w-full h-auto max-h-[calc(100vh-96px)] rounded-md grid gap-3 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-5 xl:grid-cols-6 overflow-y-auto"
  >
    <template v-if="!season">
      <Empty class="col-span-full">
        <EmptyHeader>
          <EmptyMedia>
            <AlertCircle />
          </EmptyMedia>
          <EmptyTitle>Not found</EmptyTitle>
        </EmptyHeader>
        <EmptyContent>
          The TV Season you requested was not found.
          <Button variant="link" @click="() => router.back()"> Go back </Button>
        </EmptyContent>
      </Empty>
    </template>
    <template v-else>
      <Card
        class="col-span-full flex flex-col flex-1 h-96 w-full max-w-full relative rounded-md overflow-hidden"
      >
        <div
          v-if="season.banner"
          class="absolute bg-cover bg-center w-full h-full z-0 rounded-lg"
          :style="{ backgroundImage: `url('${getImage(season.banner)}')` }"
        >
          <div class="overlay rounded-lg"></div>
        </div>
        <CardHeader class="z-10">
          <CardTitle class="text-white text-shadow-black text-shadow-sm">
            <span class="text-4xl font-bold mr-3">
              {{ selectedTvSeries?.name }}
            </span>
            <span class="text-3xl opacity-80">
              {{ season.name }}
            </span>
          </CardTitle>
          <CardDescription class="max-w-2/3 text-white text-shadow-black text-shadow-sm">
            {{ moment(season.releaseDate).get('year') }}
          </CardDescription>
        </CardHeader>
        <CardContent class="z-10">
          <p class="description max-w-2/3 text-white text-shadow-sm text-shadow-black opacity-85">
            {{ truncate(season.description, { length: 500 }) }}
          </p>
        </CardContent>
      </Card>
      <template v-if="episodes.length">
        <MediaCard
          v-for="episode of episodes"
          :key="episode.id"
          :id="episode.id"
          :name="episode.info?.name"
          :thumbnail="episode.info?.thumbnail"
          :hover-thumbnail="episode.info?.banner"
          :release-date="episode.info?.releaseDate"
          :href="getEpisodeHref(episode)"
        />
      </template>
    </template>
  </div>
</template>
