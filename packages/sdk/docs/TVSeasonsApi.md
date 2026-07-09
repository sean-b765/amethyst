# TVSeasonsApi

All URIs are relative to *http://localhost:8080*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**create2**](#create2) | **POST** /api/seasons | |
|[**deleteById2**](#deletebyid2) | **DELETE** /api/seasons/{id} | |
|[**deleteByIds2**](#deletebyids2) | **DELETE** /api/seasons | |
|[**findAll2**](#findall2) | **GET** /api/seasons | |
|[**findById2**](#findbyid2) | **GET** /api/seasons/{id} | |
|[**findByIds2**](#findbyids2) | **POST** /api/seasons/fetch | |
|[**findByTvSeries**](#findbytvseries) | **GET** /api/seasons/{tvSeriesId}/seasons | |
|[**upsert2**](#upsert2) | **PUT** /api/seasons | |

# **create2**
> TvSeason create2(tvSeason)


### Example

```typescript
import {
    TVSeasonsApi,
    Configuration,
    TvSeason
} from './api';

const configuration = new Configuration();
const apiInstance = new TVSeasonsApi(configuration);

let tvSeason: TvSeason; //

const { status, data } = await apiInstance.create2(
    tvSeason
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **tvSeason** | **TvSeason**|  | |


### Return type

**TvSeason**

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **deleteById2**
> object deleteById2()


### Example

```typescript
import {
    TVSeasonsApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new TVSeasonsApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.deleteById2(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**object**

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

# **deleteByIds2**
> object deleteByIds2(requestBody)


### Example

```typescript
import {
    TVSeasonsApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new TVSeasonsApi(configuration);

let requestBody: Array<string>; //

const { status, data } = await apiInstance.deleteByIds2(
    requestBody
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **requestBody** | **Array<string>**|  | |


### Return type

**object**

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **findAll2**
> Array<TvSeason> findAll2()


### Example

```typescript
import {
    TVSeasonsApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new TVSeasonsApi(configuration);

const { status, data } = await apiInstance.findAll2();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**Array<TvSeason>**

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

# **findById2**
> TvSeason findById2()


### Example

```typescript
import {
    TVSeasonsApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new TVSeasonsApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.findById2(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**TvSeason**

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

# **findByIds2**
> Array<TvSeason> findByIds2(requestBody)


### Example

```typescript
import {
    TVSeasonsApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new TVSeasonsApi(configuration);

let requestBody: Array<string>; //

const { status, data } = await apiInstance.findByIds2(
    requestBody
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **requestBody** | **Array<string>**|  | |


### Return type

**Array<TvSeason>**

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **findByTvSeries**
> Array<TvSeason> findByTvSeries()


### Example

```typescript
import {
    TVSeasonsApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new TVSeasonsApi(configuration);

let tvSeriesId: string; // (default to undefined)

const { status, data } = await apiInstance.findByTvSeries(
    tvSeriesId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **tvSeriesId** | [**string**] |  | defaults to undefined|


### Return type

**Array<TvSeason>**

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

# **upsert2**
> TvSeason upsert2(tvSeason)


### Example

```typescript
import {
    TVSeasonsApi,
    Configuration,
    TvSeason
} from './api';

const configuration = new Configuration();
const apiInstance = new TVSeasonsApi(configuration);

let tvSeason: TvSeason; //

const { status, data } = await apiInstance.upsert2(
    tvSeason
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **tvSeason** | **TvSeason**|  | |


### Return type

**TvSeason**

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

