# NumberParser
**Location**: [src/main/java/equation/NumberParser](src/main/java/equation/NumberParser.java)

**Since**: September 22<sup>nd</sup>, 2019
## Purpose
The `NumberParser` is the class responsible for converting a given `String` to an `ArrayList` of `String`s.

The `NumberParser` operates by being given a `String` with a mathematical expression inside of it.
Note that these are not found in the code, but rather pseudocode examples showing how it works.

```java
String math = "y = 2x + 10";
```

The first operation it preforms it cutting the `String` into a `char` array.

```java
char[] indChars = {'y', ' ', '=', ' ', '2', 'x', ' ', '+', ' ', '1', '0'}
```
It will then use a method to generate the `AggregateType` (See below) of each `char`.

```java
AggregateType[] charTypes = {LEGAL_TEXT, WHITE_SPACE, LEGAL_TEXT, WHITESPACE, NUMBER, LEGAL_TEXT, WHITESPACE, LEGAL_TEXT, WHITESPACE, NUMBER, NUMBER}
```

A `for` loop will then loop over the individual characters.
```java
StringBuilder builder = new StringBuilder();
for each character in "y = mx + b" {

  if the current character is WHITE_SPACE
    ignore it
    
  if last character == NUMBER or DECIMAL_POINT
  and current character == NUMBER or DECIMAL_POINT
    append current character to StringBuilder
}
```

### AggregateType
An enum representing the different types of characters the parser must deal with.
- `LEGAL_TEXT`
  - Represents characters that are allowed to be in a mathematical equation, such as letters (For variable names), parentheses, or operation signs (+, -, /, \*)
- `ILLEGAL_TEXT`
  - Represents characters that aren't allowed in a mathematical equation, such as exclaimation points, tilde, grave accents, or at signs.
- `WHITE_SPACE`
  - Represents whitespace characters such as spaces, newlines, and tab characters.
- `NUMBER`
  - Represents a number character (0 - 9)
- `DECIMAL_POINT`
  - Represents a decimal point character
