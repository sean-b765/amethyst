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
import { TvSeason } from '@hls-app/sdk'
import { RouterView, useRoute, useRouter } from 'vue-router'
import Card from '@/components/ui/card/Card.vue'
import { getImage } from '@/lib/utils'
import moment from 'moment'
import { truncate } from 'lodash'
import { CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'

const router = useRouter()
const route = useRoute()
const tvSeriesStore = useTvSeriesStore()
const { selectedTvSeries } = storeToRefs(tvSeriesStore)

function getSeasonHref(tvSeason: TvSeason) {
  return router.resolve({ name: 'TvSeason', params: { season: tvSeason.season } }).href
}
</script>

<template>
  <RouterView v-if="route.params.season" />
  <div
    v-else
    class="w-full h-auto max-h-[calc(100vh-96px)] rounded-md grid gap-3 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-5 xl:grid-cols-6 overflow-y-auto"
  >
    <template v-if="!selectedTvSeries">
      <Empty class="col-span-full">
        <EmptyHeader>
          <EmptyMedia>
            <AlertCircle />
          </EmptyMedia>
          <EmptyTitle>Not found</EmptyTitle>
        </EmptyHeader>
        <EmptyContent>
          The TV Series you requested was not found.
          <Button variant="link" @click="() => router.back()"> Go back </Button>
        </EmptyContent>
      </Empty>
    </template>
    <template v-else>
      <Card
        class="col-span-full flex flex-col flex-1 h-96 w-full max-w-full relative rounded-md overflow-hidden"
      >
        <div
          v-if="selectedTvSeries.banner"
          class="absolute bg-cover bg-center w-full h-full z-0 rounded-lg"
          :style="{ backgroundImage: `url('${getImage(selectedTvSeries.banner)}')` }"
        >
          <div class="overlay rounded-lg"></div>
        </div>
        <CardHeader class="z-10">
          <CardTitle class="text-4xl font-bold text-white text-shadow-black text-shadow-sm">
            {{ selectedTvSeries.name }}
          </CardTitle>
          <CardDescription class="max-w-1/3 text-white text-shadow-black text-shadow-sm">
            {{ moment(selectedTvSeries.releaseDate).get('year') }}
          </CardDescription>
        </CardHeader>
        <CardContent class="z-10">
          <p class="description max-w-2/3 text-white text-shadow-sm text-shadow-black opacity-85">
            {{ truncate(selectedTvSeries.description, { length: 500 }) }}
          </p>
        </CardContent>
      </Card>
      <template v-if="selectedTvSeries.tvSeasons?.length">
        <MediaCard
          v-for="season of selectedTvSeries.tvSeasons"
          :key="season.id"
          :id="season.id"
          :name="season.name"
          :thumbnail="season.thumbnail"
          :release-date="season.releaseDate"
          :href="getSeasonHref(season)"
        />
      </template>
    </template>
  </div>
</template>
