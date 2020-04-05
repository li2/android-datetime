[![](https://jitpack.io/v/li2/android-datetime.svg)](https://jitpack.io/#li2/android-datetime)


#  Datetime Library

This library is a wapper of [ThreeTenABP](https://github.com/JakeWharton/ThreeTenABP)


## Usage

### Formatter

```kotlin
fun LocalDateTime.toIsoLocalDateTimeString(): String
// => 2020-04-21T05:21:13

LocalDateTime.toStringWithPattern(pattern: String): String
//  "dd/MM/yyyy HH:mm:ss",   =>  21/04/2020 05:21:13
//  "dd/MM/yyyy",            =>  21/04/2020
//  "d MMM yyyy",            =>  21 Apr 2020
//  "yyyy-MM-dd",            =>  2020-04-21
//  "MMM dd'`'yy 'at' H:mm", =>  Apr 21'20 at 5:21
//  "EEE, d MMM, h:mm a",    =>  Thu, 21 Apr, 5:21 am
//  "EEE, d MMM",            =>  Thu, 21 Apr
//  "h:mm a",                =>  5:21 am
//  "hh:mm a"                =>  05:21 am

fun Month.fullDisplayName(): String // April
fun Month.shortDisplayName(): String // Apr
```

### Parser

```kotlin
fun String?.toIsoLocalDateTime(): LocalDateTime?
// "2020-04-21T05:21:13" => LocalDateTime
```

### Calculator

```kotlin
fun daysToNewYear(): Long
fun daysToChristmasEve(): Long
fun durationInDaysAndHours(from: LocalDateTime, to: LocalDateTime): Pair<Int, Int>
/** Return duration in a format "x days and y hours"*/
fun durationInDaysAndHours(context: Context, from: LocalDateTime, to: LocalDateTime): String
```

### Extensions

```kotlin
fun LocalDateTime.withTime(time: LocalTime): LocalDateTime
fun LocalDateTime?.orNow(): LocalDateTime
fun LocalDateTime.firstDayOfThisMonth(): LocalDateTime
fun LocalDateTime.millis(): Long
fun LocalDateTime.isSameDay(other: LocalDateTime): Boolean
fun LocalDateTime.isToday(): Boolean
fun LocalDateTime.isTomorrow(): Boolean
fun LocalDateTime.isYesterday(): Boolean
```


## Download

```gradle
implementation 'com.github.li2:android-datetime:latest_version'
```


## License

```
    Copyright (C) 2020 Weiyi Li

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