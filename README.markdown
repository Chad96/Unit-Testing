# SA-ID Validator

A Java-based command-line tool to validate South African ID numbers, ensuring correct format, valid date of birth, gender, citizenship, and Luhn checksum. Built with Gradle and tested using JUnit.

## Features

- Validates 13-digit South African ID numbers.
- Checks:
  - 13-digit length and numeric format.
  - Valid date of birth (year: 00-99, month: 01-12, day: month-specific, including leap years).
  - Gender code (0000-9999, typically 0000-4999 female, 5000-9999 male).
  - Citizenship (0 for citizen, 1 for permanent resident).
  - Luhn checksum for integrity.
- Interactive CLI for continuous ID validation.
- Comprehensive JUnit tests for robust validation.
- Gradle-based build for easy setup.

## Prerequisites

Ensure the following are installed:

```bash
# Java Development Kit (JDK) 21
java -version  # Should output version 21.0.x (e.g., 21.0.6)

# Gradle (wrapper included in project)
./gradlew --version  # Wrapper ensures correct version

# Git (optional, for cloning)
git --version
```

A terminal (e.g., MINGW64 on Windows, Bash on Linux/Mac) is required.

## Installation

### Clone the Repository

```bash
# Clone (if not already done)
git clone <repository-url>
cd SA-ID

# Or navigate to project directory
cd ~/OneDrive/Repositories/SA-ID
```

### Verify Project Structure

Confirm the following structure:

```
SA-ID/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   └── java/
│   │   │       └── validate_sa_id/
│   │   │           ├── IdValidatorApp.java
│   │   │           ├── ValidateSald.java
│   │   └── test/
│   │       └── java/
│   │           └── validate_sa_id/
│   │               └── ValidateSaldTest.java
├── build.gradle
├── gradle/
├── gradlew
├── gradlew.bat
├── settings.gradle
├── README.md
```

Verify with:

```bash
tree -L 5
```

### Clean the Project

Remove stale build artifacts:

```bash
./gradlew clean
```

## Usage

### Running the Application

The CLI prompts for a 13-digit ID number, validates it, and shows if it’s valid. Enter `quit` to exit.

```bash
./gradlew run
```

Example output:

```
South African ID Validator
Enter a 13-digit ID number (or 'quit' to exit):
2001016800084
ID 2001016800084 is valid
Enter another ID number (or 'quit' to exit):
2001015000084
ID 2001015000084 is invalid
Enter another ID number (or 'quit' to exit):
quit
Exiting validator.
```

Alternative (if Gradle run fails):

```bash
cd app/build/classes/java/main
java validate_sa_id.IdValidatorApp
```

### Running Tests

Run JUnit tests to verify validation logic:

```bash
./gradlew test
```

View test report if needed:

```bash
start app/build/reports/tests/test/index.html
```

## Project Structure

```plaintext
SA-ID/
├── app/
│   ├── src/
│   │   ├── main/java/validate_sa_id/
│   │   │   ├── IdValidatorApp.java    # CLI for user input
│   │   │   ├── ValidateSald.java      # Validation logic
│   │   ├── test/java/validate_sa_id/
│   │   │   ├── ValidateSaldTest.java  # JUnit tests
├── build.gradle                       # Gradle build config
├── settings.gradle                    # Project settings
├── gradlew                            # Gradle wrapper (Unix)
├── gradlew.bat                        # Gradle wrapper (Windows)
├── README.md                          # Project documentation
```

## Validation Logic

The `ValidateSald.java` validates ID numbers (YYMMDDSSSSCAZ):

- **Length**: 13 digits.
- **Format**: Numeric only.
- **Date**:
  - Year (YY): 00-99.
  - Month (MM): 01-12.
  - Day (DD): Month-specific (e.g., 01-31 for January, 01-28/29 for February with leap year).
- **Gender (SSSS)**: 0000-9999 (0000-4999 female, 5000-9999 male).
- **Citizenship (C)**: 0 (citizen), 1 (permanent resident).
- **Checksum (Z)**: Luhn algorithm:
  - Double every second digit (right to left).
  - Subtract 9 from doubled values > 9.
  - Sum must be divisible by 10.

Example valid ID: `2001016800084` (male, valid checksum).

## Testing

The `ValidateSaldTest.java` includes 12 test cases:

- `testValidIdNumbers`: Validates `2001014800086`, `2909035800085`.
- `testInvalidLength`: Checks incorrect lengths.
- `testNonNumeric`: Tests non-numeric input.
- `testInvalidYear`: Tests invalid years.
- `testInvalidMonth`: Tests invalid months.
- `testInvalidDay`: Tests invalid days.
- `testInvalidGender`: Tests invalid gender codes.
- `testInvalidCitizenship`: Tests invalid citizenship.
- `testInvalidChecksum`: Tests invalid checksums.
- `testInvalidDayForFebruary`: Tests invalid February days.
- `testGenderFemale`: Validates `2001014999086` (female).
- `testGenderMale`: Validates `2001016800084` (male).

Run tests:

```bash
./gradlew test
```

## Troubleshooting

### Runtime Error: NoSuchElementException

Error:

```
Exception in thread "main" java.util.NoSuchElementException: No line found
```

**Cause**: `System.in` is closed or empty (common in MINGW64).

**Fixes**:

- Run interactively:

```bash
cd app/build/classes/java/main
java validate_sa_id.IdValidatorApp
```

- Ensure MINGW64 provides an interactive console.
- Debug with stacktrace:

```bash
./gradlew run --stacktrace
```

### Test Failure: testGenderMale

**Cause**: Incorrect ID or Luhn algorithm.

**Fixes**:

- Verify `ValidateSaldTest.java` uses `2001016800084`.
- Confirm `ValidateSald.java` uses `n -= 9` in `isValidLuhn`.
- Debug by adding to `isValidLuhn`:

```java
System.out.println("Digit: " + n + ", Doubled: " + (alternate ? n * 2 : n));
```

Run tests:

```bash
./gradlew test
```

### Other Issues

- **Compilation Errors**:

```bash
ls app/src/main/java/validate_sa_id/
ls app/src/test/java/validate_sa_id/
```

- **JDK Version**:

```bash
java -version  # Ensure 21.0.x
```

- **Gradle Issues**:

```bash
./gradlew cleanBuildCache
```

## Contributing

1. Fork the repository.
2. Create a branch:

```bash
git checkout -b feature/your-feature
```

3. Commit changes:

```bash
git commit -m "Add your feature"
```

4. Push:

```bash
git push origin feature/your-feature
```

5. Open a pull request.

Include tests and follow Java coding standards.

## License

MIT License. See [LICENSE](LICENSE) (create if needed).