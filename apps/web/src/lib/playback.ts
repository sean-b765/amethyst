import type { Media } from '@hls-app/sdk'
import { MediaMetadataContainerEnum } from '@hls-app/sdk'

export const CONTAINERS: Record<string, string> = {
  [MediaMetadataContainerEnum.Mkv]: 'video/x-matroska',
  [MediaMetadataContainerEnum.Mp4]: 'video/mp4',
  [MediaMetadataContainerEnum.Webm]: 'video/webm',
} as const

export const VIDEO_PROBES = [
  { label: 'h264_8bit', contentType: '; codecs="avc1.4D401E"' },
  { label: 'h264_10bit', contentType: '; codecs="avc1.6E0033"' },
  { label: 'hevc_8bit', contentType: '; codecs="hvc1.1.6.L93.B0"' },
  { label: 'hevc_10bit', contentType: '; codecs="hvc1.2.4.L120.B0"' },
  { label: 'av1_8bit', contentType: '; codecs="av01.0.05M.08"' },
  { label: 'av1_10bit', contentType: '; codecs="av01.0.10M.10"' },
  { label: 'vp9_8bit', contentType: '; codecs="vp09.00.10.08"' },
  { label: 'vp9_10bit', contentType: '; codecs="vp09.02.10.10"' },
] as const

export const AUDIO_PROBES = [
  { label: 'aac', contentType: 'audio/mp4; codecs="mp4a.40.2"' },
  { label: 'mp3', contentType: 'audio/mpeg' },
  { label: 'flac', contentType: 'audio/flac' },
  { label: 'vorbis', contentType: 'audio/webm; codecs="vorbis"' },
  { label: 'opus', contentType: 'audio/webm; codecs="opus"' },
  { label: 'ac3', contentType: 'audio/mp4; codecs="ac-3"' },
  { label: 'eac3', contentType: 'audio/mp4; codecs="ec-3"' },
] as const

async function probeCodecSupport(
  videoContentType: string,
  audioContentType?: string,
): Promise<boolean> {
  if (!('mediaCapabilities' in navigator)) return false

  const config: MediaDecodingConfiguration = {
    type: 'file',
    video: {
      contentType: videoContentType,
      width: 1920,
      height: 1080,
      bitrate: 8_000_000,
      framerate: 30,
    },
  }

  if (audioContentType) {
    config.audio = { contentType: audioContentType }
  }

  const result = await navigator.mediaCapabilities.decodingInfo(config)
  console.log(result)
  return result.supported
}

function getVideoProbeLabel(codec: string, bitDepth: number): string | null {
  const depth = `${bitDepth}bit`
  switch (codec) {
    case 'h264':
      return `h264_${depth}`
    case 'hevc':
      return `hevc_${depth}`
    case 'av1':
      return `av1_${depth}`
    case 'vp9':
      return `vp9_${depth}`
    default:
      return null
  }
}

/**
 * Given a media with metadata, check the codecs, bit depth, container to see if the browser supports it
 * @param media
 * @returns
 */
export async function canDirectPlay(media: Media): Promise<boolean> {
  const { videoCodec, videoBitDepth, audioCodec, container } = media.metadata ?? {}

  if (!container) return false
  const containerMimeType = CONTAINERS[container]
  if (!containerMimeType) return false

  if (!videoCodec || videoBitDepth == null) return false
  const videoLabel = getVideoProbeLabel(videoCodec, videoBitDepth)
  if (!videoLabel) return false

  const videoProbe = VIDEO_PROBES.find((p) => p.label === videoLabel)
  if (!videoProbe) return false

  const audioProbe = AUDIO_PROBES.find((p) => p.label === audioCodec)
  return probeCodecSupport(`${containerMimeType}${videoProbe.contentType}`, audioProbe?.contentType)
}
