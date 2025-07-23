# Java 8 Streams Demos &ndash; Index

## Demos using numbers

* [`PartitionOfEvenOddIntValues.java`](src/main/java/net/andbin/streamsdemos/numbers/PartitionOfEvenOddIntValues.java) &ndash; Given an `int[]`, partitions even and odd values.
* [`ProductOfIntValues.java`](src/main/java/net/andbin/streamsdemos/numbers/ProductOfIntValues.java) &ndash; Given an `int[]`, calculates the product of all the values.
* [`SumOfLongValuesAsBigInteger.java`](src/main/java/net/andbin/streamsdemos/numbers/SumOfLongValuesAsBigInteger.java) &ndash; Given a `long[]`, calculates the sum of all the values as `BigInteger`.

## Demos using cartesian points

See data model: [`Point2D.java`](src/main/java/net/andbin/streamsdemos/points/datamodel/Point2D.java) / [`PointsData.java`](src/main/java/net/andbin/streamsdemos/points/datamodel/PointsData.java) / [`Quadrant.java`](src/main/java/net/andbin/streamsdemos/points/datamodel/Quadrant.java)<br>
And see also: [`PointsDataChart.java`](src/main/java/net/andbin/streamsdemos/points/tool/PointsDataChart.java) JavaFX chart app

* [`CountOfPointsByQuadrant.java`](src/main/java/net/andbin/streamsdemos/points/CountOfPointsByQuadrant.java) &ndash; Given a `List<Point2D>`, counts the points in each quadrant.
* [`MaxOriginDistanceByQuadrant.java`](src/main/java/net/andbin/streamsdemos/points/MaxOriginDistanceByQuadrant.java) &ndash; Given a `List<Point2D>`, calculates the maximum distance of a point from the origin for each quadrant.

## Demos using strings

* [`ConversionOfStringsToUppercase.java`](src/main/java/net/andbin/streamsdemos/strings/ConversionOfStringsToUppercase.java) &ndash; Given a `List<String>`, converts all the strings to uppercase into a new list.
* [`FrequencyOfStringsSortedByString.java`](src/main/java/net/andbin/streamsdemos/strings/FrequencyOfStringsSortedByString.java) &ndash; Given a `List<String>` with repeated strings, calculates the frequency (number of occurrencies) of strings.
* [`GroupingOfStringsByInitial.java`](src/main/java/net/andbin/streamsdemos/strings/GroupingOfStringsByInitial.java) &ndash; Given a `List<String>`, groups strings by initial letter.
* [`GroupingOfStringsByLength.java`](src/main/java/net/andbin/streamsdemos/strings/GroupingOfStringsByLength.java) &ndash; Given a `List<String>`, groups strings by length.
