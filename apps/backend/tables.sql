/*
  Configs
*/

CREATE TABLE configuration (
  id bigint not null,
  admin_user_created boolean not null,
  media_directory varchar(255),
  tmdb_api_key varchar(255),
  transcode_directory varchar(255),
  primary key (id)
)

CREATE TABLE system_event (
  id varchar(255) not null,
  created_at timestamp not null,
  updated_at timestamp,
  message varchar(255),
  resource_id varchar(255) not null,
  type tinyint not null check (type between 0 and 4),
  primary key (id)
)

CREATE TABLE user (
  id varchar(255) not null,
  created_at timestamp not null,
  updated_at timestamp,
  password varchar(255) not null,
  roles blob,
  username varchar(255) not null unique,
  primary key (id)
)

/*
  Media
*/

CREATE TABLE library (
  id varchar(255) not null,
  created_at timestamp not null,
  updated_at timestamp,
  name varchar(255) not null,
  order_index integer,
  path varchar(255) not null unique,
  slug varchar(255) not null unique,
  type tinyint not null check (type between 0 and 4),
  primary key (id)
)

CREATE TABLE media (
  id varchar(255) not null,
  created_at timestamp not null,
  updated_at timestamp,
  path varchar(255) not null unique,
  library_id varchar(255),
  media_info_id varchar(255) unique,
  metadata_id varchar(255) unique,
  tv_season_id varchar(255),
  primary key (id)
)

CREATE TABLE media_info (
  id varchar(255) not null,
  created_at timestamp not null,
  updated_at timestamp,
  banner varchar(255),
  description varchar(255),
  episode integer,
  name varchar(255) not null,
  release_date date,
  season integer,
  tagline varchar(255),
  thumbnail varchar(255),
  type varchar(255) check (type in ('MOVIE', 'TV', 'MUSIC')),
  primary key (id)
)

CREATE TABLE media_metadata (
  id varchar(255) not null,
  created_at timestamp not null,
  updated_at timestamp,
  audio_channel_layout varchar(255),
  audio_channels integer,
  audio_codec varchar(255),
  audio_samplerate integer,
  bitrate bigint,
  container varchar(255) check (
    container in (
      'MP4',
      'MKV',
      'WEBM',
      'AVI',
      'MPEG_TS',
      'MOV',
      'OGG',
      'MP3',
      'WAV',
      'FLAC',
      'AAC',
      'OPUS',
      'UNKNOWN'
    )
  ),
  duration_seconds float,
  framerate bigint,
  last_modified timestamp,
  last_scan_date_time timestamp,
  size_bytes bigint,
  video_bit_depth integer,
  video_codec varchar(255),
  video_height integer,
  video_level integer,
  video_width integer,
  primary key (id)
)

CREATE TABLE tv_series (
  id varchar(255) not null,
  created_at timestamp not null,
  updated_at timestamp,
  banner varchar(255),
  description varchar(255),
  external_id varchar(255) unique,
  name varchar(255) not null,
  release_date date,
  tagline varchar(255),
  thumbnail varchar(255),
  library_id varchar(255),
  primary key (id)
)

CREATE TABLE tv_season (
  id varchar(255) not null,
  created_at timestamp not null,
  updated_at timestamp,
  banner varchar(255),
  description varchar(255),
  external_id varchar(255) unique,
  name varchar(255) not null,
  release_date date,
  season integer,
  thumbnail varchar(255),
  tv_series_id varchar(255),
  primary key (id)
)