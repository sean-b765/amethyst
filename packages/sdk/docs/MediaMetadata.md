# MediaMetadata


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **string** |  | [default to undefined]
**createdAt** | **string** |  | [default to undefined]
**updatedAt** | **string** |  | [optional] [default to undefined]
**videoCodec** | **string** |  | [optional] [default to undefined]
**videoBitDepth** | **number** |  | [optional] [default to undefined]
**videoLevel** | **number** |  | [optional] [default to undefined]
**videoWidth** | **number** |  | [optional] [default to undefined]
**videoHeight** | **number** |  | [optional] [default to undefined]
**audioCodec** | **string** |  | [optional] [default to undefined]
**audioSamplerate** | **number** |  | [optional] [default to undefined]
**audioChannels** | **number** |  | [optional] [default to undefined]
**audioChannelLayout** | **string** |  | [optional] [default to undefined]
**bitrate** | **number** |  | [optional] [default to undefined]
**container** | **string** |  | [optional] [default to undefined]
**sizeBytes** | **number** |  | [optional] [default to undefined]
**durationSeconds** | **number** |  | [optional] [default to undefined]
**framerate** | **number** |  | [optional] [default to undefined]
**lastScanDateTime** | **string** |  | [optional] [default to undefined]
**lastModified** | **string** |  | [optional] [default to undefined]
**media** | [**Media**](Media.md) |  | [optional] [default to undefined]

## Example

```typescript
import { MediaMetadata } from './api';

const instance: MediaMetadata = {
    id,
    createdAt,
    updatedAt,
    videoCodec,
    videoBitDepth,
    videoLevel,
    videoWidth,
    videoHeight,
    audioCodec,
    audioSamplerate,
    audioChannels,
    audioChannelLayout,
    bitrate,
    container,
    sizeBytes,
    durationSeconds,
    framerate,
    lastScanDateTime,
    lastModified,
    media,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
