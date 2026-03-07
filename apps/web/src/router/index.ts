import BaseLayout from '@/layouts/BaseLayout.vue'
import PlayerLayout from '@/layouts/PlayerLayout.vue'
import HomeView from '@/views/HomeView.vue'
import LibrariesView from '@/views/LibrariesView.vue'
import LibraryDetailView from '@/views/LibraryDetailView.vue'
import PlayerView from '@/views/PlayerView.vue'
import TvSeriesDetailView from '@/views/TvSeriesDetailView.vue'
import TvSeriesSeasonDetailView from '@/views/TvSeriesSeasonDetailView.vue'
import { LayoutGrid, LibrarySquare } from 'lucide-vue-next'
import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: BaseLayout,
    children: [
      {
        path: '',
        component: HomeView,
        name: 'Home',
        meta: {
          icon: LayoutGrid,
        },
      },
      {
        path: 'libraries',
        component: LibrariesView,
        name: 'Libraries',
        meta: {
          icon: LibrarySquare,
        },
      },
      {
        path: 'libraries/:libraryId',
        component: LibraryDetailView,
        name: 'Library',
      },
      {
        path: 'tv/:tvSeriesId',
        component: TvSeriesDetailView,
        name: 'TvSeries',
        children: [
          {
            path: ':season',
            component: TvSeriesSeasonDetailView,
            name: 'TvSeason',
          },
        ],
      },
    ],
  },
  {
    path: '/watch',
    component: PlayerLayout,
    children: [
      {
        path: ':mediaId',
        component: PlayerView,
        name: 'WatchMedia',
      },
    ],
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
