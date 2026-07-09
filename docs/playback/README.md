We will try to direct play on the client first. If direct play is not supported, as found by the method outlined in [./client-side-probe.md](./client-side-probe.md), we will launch an ffmpeg worker thread to transcode, and use HLS.

1. Some video and audio codecs are playable directly in your web browser or client. This playback method is called direct play. If the client is incompatible with a file type, the backend must transcode on-demand to the tmp directory, and the client will request the tmp segments.
2. When transcoding, we choose to format the output stream as HLS (Http Live Streaming) - this splits the files into segments, which will then be fetched from the browser when needed. We will always transcode to **h264 8-bit** + **aac**.
