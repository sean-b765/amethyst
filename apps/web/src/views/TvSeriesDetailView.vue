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
