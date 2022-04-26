# Range kata


Implement a class `Range` to present a range of elements having natural order.

To create a `Range` instance, simply give it a `lowerbound` and a `upperbound`. A `Range` can be used to check if it contains a value.

## Requirements

- Develop the `Range` class from level to level. Please note that you only need to develop on the source file `Range.java`. There is no need to create multiple versions of such class.
- You must add more tests in `RangeTest.java` as you progress through the levels. There are several failing tests written so that you can get started quickly for Level 1.
- All tests in `RangeTest` MUST pass.
- Each level MUST be a completed by a Git commit.
- Please commit **directly** to the `master` or `main` branch.

## Class `Range` *public* API

```java
class Range {
  public static Range of(int lowerbound, int upperbound);

  public boolean contains(int value);

  public int lowerbound();
  public int upperbound();
}
```

## Level 1 -  A Range for Numbers

Class `Range` can be used with `int`.

```
Range validAgesForWatchingPorn = Range.of(13, 100);


validAgesForWatchingPorn.contains(5); // false
validAgesForWatchingPorn.contains(13); // true
validAgesForWatchingPorn.contains(22); // true
validAgesForWatchingPorn.contains(100); // true
validAgesForWatchingPorn.contains(101); // false

```

The following constraints **MUST** be implemented:

- `Range` must be *immutable*. Once created, there is no way to change its `lowerbound` and `upperbound`.
- `Range` must provide a _static factory method_ namely `of(int, int)` to create a new instance.
- It is not allowed to create a `Range` with `lowerbound > upperbound`.
- The method `contains(x)` must returns `true` only if 
`lowerbound <= x <=upperbound`.

## Level 2 - More Types of `Range`

Mathmatically, a `Range` can be `open`, `closed`, `openClosed` or `closedOpen`.

```
// open range excludes both bounds
// 4..5(..6..)7..8..9..10
//      ^^^^^
(5, 7)


// closed range includes both bounds
// 4..[5..6..7]..8..9..10
//    ^^^^^^^^^
[5, 7]

// open closed excludes lowerbound but includes upperbound
// 4..5(..6..7]..8..9..10
//     ^^^^^^^^
(5, 7]

// closed open includes lowerbound but excludes upperbound
// 4..[5..6..)7..8..9..10
//    ^^^^^^^
[5, 7)

```

- Extend `Range` such that it can support all of the above types. (This implies the method `of(int,int`) will be renamed to `closed(int, it)`)

```
Range open = Range.open(5, 7);
open.contains(5); //false

Range closed = Range.closed(5, 7);
closed.contains(5); // true

Range openClosed = Range.openClosed(5, 7);
openClosed.contains(5); // false
openClosed.contains(7); // true

Range closedOpen = Range.closedOpen(5, 7);
closedOpen.contains(5); // true;
closedOpen.contains(7); // false;

```


## Level 3 - Make it generic with all `Comparable<T>` types

Extends the `Range` such that it can supports any types implementing `Comparable` interface.

The `Comparable` interface is implemented by several Java types (i.e `String`, )


```
Range<String> text = Range.open("abc", "xyz");

Range<BigDecimal> decimals = Range.open(BigDecimal.valueOf("1.32432"), BigDecimal.valueOf("1.324323423423423423423"));

Range<LocalDate> dates = Range.closed(LocalDate.of(2016, Month.SEPTEMBER, 11), LocalDate.of(2017, Month.JUNE, 30)));

```

## Level 4 - Open-ended `Range`s

We want to have `Range` to support an open-ended style with `Infinitive`. For example:

```
Range<Integer> lessThanFive = Range.lessThan(5); // [Infinitive, 5)
lessThanFive.contains(5); // false;
lessThanFive.contains(-9000); // true;

Range<Integer> atLeastFive = Range.atLeast(5); // [5, Infinitive]
atLeastFive.contains(5); // true;
atLeastFive.contains(4); // false;

Range<Integer> atMostFive = Range.atMost(5); // [Infinitive, 5]
atMostFive.contains(5); // true
atMostFive.contains(-234234); // true;
atMostFive.contains(6); // false;


Range<LocalDate> afterEpoch = Range.greaterThan(LocalDate.of(1900, Month.JANUARY, 1)); // (1900-01-01, Infinitive]
afterEpoch.contains(LocalDate.of(2016, Month.JULY, 28)); // true;
afterEpoch.contains(LocalDate.of(1750, Month.JANUARY, 1)); // false;


Range<String> all = Range.all(); // [Infinitive, Infinitive]
all.contains("anything"); // true;
all.contains(""); // true;
all.contains(null); // true;
```

## Level 5 - Introduce useful `toString()`

Implement `toString()` method for class `Range`. The `toString()` should represent the type and the bounds of the current `Range` instance.

```java
Range<Integer> lessThen100 = Range.lessThen(100);
assert lessThen100.toString() == "[Infinitive, 100)"

Range<LocalDate> within2020 = Range.closed(
  LocalDate.of(1, JANUARY, 2020),
  LocalDate.of(31, DECEMBER, 2020)
)

assert within2020 == "[2020-01-01, 2020-12-31]"
```

## Level 6 - Parse a `Range` notation.

It is possible to create a new `Range` using the result from `Range#toString()`.

This level tests your API design skills so we won't provide much information here. Please decide the signature of `parse` yourself.

```java
String rangeString = Range.lessThan(100).toString();
Range<Integer> lessThan100 = Range.parse(rangeString, ...);
assert lessThan100.toString() == "[Infinitive, 100)"
assert lessThan100.contains(99);
```

## Meta

v2021.12.02

- Update Requirements to avoid confusions (some candidates ended up create six versions of `Range.java`).
- Refactor levels' names.
- Add an assertion for Level 6 to ensure that the `Range#parse` works correctly with the original type.

v2020.11.20

- Add level 6.
- Rename levels to reflect its true intent.
- Added _visualization_ of types of Ranges.

v2020.06.01
- Initial