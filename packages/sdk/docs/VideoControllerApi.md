# VideoControllerApi

All URIs are relative to *http://localhost:8080*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**directPlay**](#directplay) | **GET** /api/video/direct/{mediaId} | |
|[**getHlsSegment**](#gethlssegment) | **GET** /api/video/hls/{mediaId}/{qualityProfile}/{segmentName} | |

# **directPlay**
> ResourceRegion directPlay()


### Example

```typescript
import {
    VideoControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new VideoControllerApi(configuration);

let mediaId: string; // (default to undefined)

const { status, data } = await apiInstance.directPlay(
    mediaId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **mediaId** | [**string**] |  | defaults to undefined|


### Return type

**ResourceRegion**

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getHlsSegment**
> File getHlsSegment()


### Example

```typescript
import {
    VideoControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new VideoControllerApi(configuration);

let mediaId: string; // (default to undefined)
let qualityProfile: string; // (default to undefined)
let segmentName: string; // (default to undefined)
let xHlsToken: string; // (default to undefined)

const { status, data } = await apiInstance.getHlsSegment(
    mediaId,
    qualityProfile,
    segmentName,
    xHlsToken
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **mediaId** | [**string**] |  | defaults to undefined|
| **qualityProfile** | [**string**] |  | defaults to undefined|
| **segmentName** | [**string**] |  | defaults to undefined|
| **xHlsToken** | [**string**] |  | defaults to undefined|


### Return type

**File**

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

