## Project

[Amethyst] — [a self-hosted solution for a home media server, similar to plex/jellyfin].

## Stack

- [Java 21, Vue.js + TypeScript] monorepo
- [Nx] as the tooling for monorepo.
- [Java 21 + Spring Boot] in [apps/backend] for the backend
- [Vue.js 3 + TypeScript] in [apps/web] for the frontend
- [SQLite as the DB]
- [shadcn for ui components in the front-end]

## Structure

A Nx monorepo

- [apps] — [the main applications]
- [apps/web/] — [vue.js frontend]
- [apps/backend/] — [java+spring boot backend]
- [packages] — [shared libraries]
- [packages/sdk/] - [typescript sdk generatged via openapitools on the swagger openapi spec]
- [docs/] — [documentation + diagrams]

## Commands

- dev: `[pnpm dev]` to run both backend and frontend, `[pnpm dev:backend]` `[pnpm dev:web]` for backend and frontend, respectively
- test: `[pnpm test]`
- generate-sdk: `[pnpm generate-sdk]` requires the backend to be running and available, it hits `http://localhost:8080/v3/api-docs`

## Conventions

- [apps/web]: camelCase naming always as per TypeScript standards
- [apps/web/src/components/ui]: shadcn is meant to be a functionally-complete UI component library. try to avoid modifying components in this folder, unless absolutely necessary
- [apps/web]: icons are provided by Lucide.dev
- [apps/backend]: camelCase java naming always
- [apps/backend]: uses layered architecture, controller -> service -> repository
- on the topic of media transcoding and playback, see [docs/playback]
