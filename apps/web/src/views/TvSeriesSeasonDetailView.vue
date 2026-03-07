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

const route = useRoute()
const router = useRouter()
const tvSeriesStore = useTvSeriesStore()
const { selectedTvSeries } = storeToRefs(tvSeriesStore)

function getEpisodeHref(episode: Media) {
  return router.resolve({ name: 'WatchMedia', params: { mediaId: episode.id } }).href
}

const episodes = computed(() => {
  const { season } = route.params
  return (
    selectedTvSeries.value?.tvSeasons?.find((tvSeason) => String(tvSeason.season) === season)
      ?.mediaItems ?? []
  )
})
</script>

<template>
  <div
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
          The TV Season you requested was not found.
          <Button variant="link" @click="() => router.back()"> Go back </Button>
        </EmptyContent>
      </Empty>
    </template>
    <template v-else>
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
