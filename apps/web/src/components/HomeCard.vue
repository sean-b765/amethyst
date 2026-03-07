<!--
  File contains the component that displays the featured movie
  to the user at the top of the Homepage. Basic display has been made,
  though currents lacks functionality (i.e. buttons)
-->

<script setup lang="ts">
import { Button } from '@/components/ui/button'
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from '@/components/ui/card'
import { getImage } from '@/lib/utils'
import { type MediaInfo } from '@hls-app/sdk'
import { truncate } from 'lodash'
import moment from 'moment'
import { computed } from 'vue'
import { useRouter } from 'vue-router'

const { id, name, releaseDate, description, banner } = defineProps<Partial<MediaInfo>>()
const router = useRouter()
const link = computed(() => {
  return router.resolve({ name: 'WatchMedia', params: { mediaId: id } }).href
})
</script>

<template>
  <Card
    class="home-card flex flex-col flex-1 w-full max-w-full relative rounded-lg overflow-hidden"
  >
    <div
      v-if="banner"
      class="banner w-full h-full z-0 rounded-lg scale-105"
      :style="{ backgroundImage: `url('${getImage(banner)}')` }"
    >
      <div class="overlay rounded-lg"></div>
    </div>
    <CardHeader class="z-10">
      <CardTitle class="text-4xl font-bold text-white text-shadow-black text-shadow-sm">
        {{ name }}
      </CardTitle>
      <CardDescription class="max-w-1/3 text-white text-shadow-black text-shadow-sm">
        {{ moment(releaseDate).get('year') }}
      </CardDescription>
    </CardHeader>
    <CardContent class="z-10">
      <p
        class="description text-white text-shadow-sm text-shadow-black opacity-85"
        style="max-width: clamp(200px, 50%, 600px)"
      >
        {{ truncate(description, { length: 200 }) }}
      </p>
    </CardContent>
    <CardFooter class="mt-5 gap-5 z-10">
      <RouterLink :to="link">
        <Button variant="default" class="cursor-pointer"> Watch now </Button>
      </RouterLink>
    </CardFooter>
  </Card>
</template>

<style scoped lang="scss">
.home-card {
  height: 70vh;
}
.title {
  font-size: 3rem;
  font-weight: bold;
}

/* Adjusts width and length of the banner */
.banner {
  overflow: hidden;
  position: absolute;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: top center;
  width: 100%;
  will-change: transform;
  animation: bg-breathe 40s ease-in-out infinite;
}

.description {
  font-size: 0.9rem;
}

@keyframes bg-breathe {
  0% {
    transform: scale(1);
  }
  50% {
    transform: scale(1.04);
  }
  100% {
    transform: scale(1);
  }
}
</style>
