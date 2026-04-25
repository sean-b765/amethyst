- `y = supported (yes)`
- `p = partially supported`
- `n = not supported`

| Video Codec             | Chrome | Edge | Firefox | Firefox Android | Opera | Safari | iOS | Solution                       |
| ----------------------- | ------ | ---- | ------- | --------------- | ----- | ------ | --- | ------------------------------ |
| AVC (H264 - 8Bit)       | y      | y    | y       | y               | y     | y      | y   | directplay                     |
| AVC (H264 - 10Bit)      | n      | n    | n       | n               | n     | n      | n   | transcode                      |
| AV1 (8Bit)              | y      | y    | y       | y               | y     | p\*    | p   | directplay, transcode on apple |
| AV1 (10Bit / HDR)       | p      | p    | p       | p               | p     | p      | p   | transcode                      |
| HEVC (H265 - 8Bit)      | p      | p    | n       | n               | p     | y      | y   | directplay on apple            |
| HEVC (H265 - 10Bit HDR) | p      | p    | n       | n               | p     | y      | y   | directplay on apple            |

\* Safari supports AV1 on M3 MacBooks and later, iPhone 15 Pro, and iPhone 16 and later.
