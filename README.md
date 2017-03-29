# latlng2dms - LatLong to DMS representation

[![GitHub issues](https://img.shields.io/github/issues/marlonlom/latlng2dms.svg?style=flat-square)](https://github.com/marlonlom/latlng2dms/issues)
![Github Releases](https://img.shields.io/github/downloads/marlonlom/latlng2dms/latest/total.svg?style=flat-square)
[![Bintray](https://img.shields.io/bintray/v/asciidoctor/maven/asciidoctorj.svg?style=flat-square)](https://github.com/marlonlom/latlng2dms)
[![Build Status](https://travis-ci.org/marlonlom/latlng2dms.svg?branch=master&style=flat-square)](https://travis-ci.org/marlonlom/latlng2dms)

Some utilities for converting latitude/longitudes to degrees/minutes/seconds format

## Examples:
- 40°26′46″ N, 79°58′56″ W
- 2°20'24" N, 5°20'24" W

## Usage:

### Import as a dependency:

Gradle:

```
compile 'com.github.marlonlom:latlng2dms:$latestVersion'
```

Maven:

```xml
<dependency>
  <groupId>com.github.marlonlom</groupId>
  <artifactId>latlng2dms</artifactId>
  <version>$latestVersion</version>
</dependency>
```

### Use it in your code:

With coordinates:

```java
final Double[] coordinates = {151.209900d, -33.865143d};
final String converted = LatsLngs.with(coordinates).toDms();
/* Will return 33°51'54" S, 151°12'35" E */
```

Or with single longitude/latitude value:

```java
final Double value = -34.206841d;
final String converted = LatsLngs.with(value).asLatitude().toDms();
/* Will return 34°12'24 S */
```


## Spread the word

If you like this library, please tell others about it :thumbsup::thumbsup:

<a href="https://twitter.com/intent/tweet?text=Trying%20to%20show%20coordinates%20more%20friendly%3F%20Check%20out%20this%20awesome%20library%20on%20Github%3A%20https://github.com/marlonlom/latlng2dms" target="_blank" title="share to twitter" style="width:100%"><img src="https://github.com/marlonlom/staticmaps_builder/blob/master/design/twitter_icon.png" title="Share on Twitter" width="35" height=35 />
<a href="https://plus.google.com/share?url=https://github.com/marlonlom/latlng2dms" target="_blank" title="share to G+" style="width:100%"><img src="https://github.com/marlonlom/staticmaps_builder/blob/master/design/googleplus_icon.png" target="_blank"  title="Share on Google+" width="35" height=35 />
<a href="https://www.facebook.com/sharer/sharer.php?u=https://github.com/marlonlom/latlng2dms" target="_blank" title="share to facebook" style="width:100%"><img src="https://github.com/marlonlom/staticmaps_builder/blob/master/design/facebook_icon.png" title="Share on Facebook" width="35" height=35 />

 - []()Follow me on **Twitter**: [**@Marlonlom**](https://twitter.com/marlonlom)
 - Contact me on **LinkedIn**: [**Marlonlom**](https://co.linkedin.com/in/marlonlom)


### License

```
Copyright 2017 marlonlom

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
