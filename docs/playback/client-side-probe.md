Using the below list, we can probe the compatibility on the client-side for a list of supported video and audio codecs. Then when the client goes to play their media, we can determine when directplay is possible.

```js
// the check:
navigator.mediaCapabilities.decodingInfo({
  type: 'file',
  video: {
    contentType: 'video/mp4; codecs="avc1.4D401E"',
    width: 1920,
    height: 1080,
    bitrate: 8_000_000,
    framerate: 30,
  },
}); // returns Promise<{ powerEfficient, smooth, supported }>
```

```js
const VIDEO_PROBES = [
  {
    label: 'h264_8bit',
    contentType: 'video/mp4; codecs="avc1.4D401E"',
  },
  {
    label: 'h264_10bit',
    contentType: 'video/mp4; codecs="avc1.6E0033"',
  },

  {
    label: 'hevc_8bit',
    contentType: 'video/mp4; codecs="hvc1.1.6.L93.B0"',
  },
  {
    label: 'hevc_10bit',
    contentType: 'video/mp4; codecs="hvc1.2.4.L120.B0"',
  },

  {
    label: 'av1_8bit_mp4',
    contentType: 'video/mp4; codecs="av01.0.05M.08"',
  },
  {
    label: 'av1_10bit_mp4',
    contentType: 'video/mp4; codecs="av01.0.10M.10"',
  },

  {
    label: 'av1_8bit_webm',
    contentType: 'video/webm; codecs="av01.0.05M.08"',
  },

  {
    label: 'vp9_8bit',
    contentType: 'video/webm; codecs="vp09.00.10.08"',
  },
  {
    label: 'vp9_10bit',
    contentType: 'video/webm; codecs="vp09.02.10.10"',
  },
];
```

```js
const AUDIO_PROBES = [
  {
    label: 'aac',
    contentType: 'audio/mp4; codecs="mp4a.40.2"',
  },
  {
    label: 'mp3',
    contentType: 'audio/mpeg',
  },
  {
    label: 'flac',
    contentType: 'audio/flac',
  },
  {
    label: 'vorbis',
    contentType: 'audio/webm; codecs="vorbis"',
  },
  {
    label: 'opus',
    contentType: 'audio/webm; codecs="opus"',
  },
  {
    label: 'ac3',
    contentType: 'audio/mp4; codecs="ac-3"',
  },
  {
    label: 'eac3',
    contentType: 'audio/mp4; codecs="ec-3"',
  },
];
```
