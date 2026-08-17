# Java Unit Testing

A focused Java project that demonstrates how to write clear, maintainable unit tests with **JUnit 6** and **Mockito 5**, and how to assess test effectiveness with **Pitest mutation testing**. It is designed as a practical learning portfolio: each mini-project introduces a realistic testing need, from pure business logic to dependencies such as repositories and user accounts.

## Why code quality matters

Code quality is not only about making code work today—it is about making change safe tomorrow. Well-structured unit tests provide fast feedback, document expected behavior, protect against regressions, and make refactoring less risky. They encourage small, focused classes with explicit dependencies, which makes production code easier to understand and maintain.

This repository follows the Arrange–Act–Assert (AAA) pattern and covers successful paths, edge cases, invalid input, and expected exceptions.

## Projects

| Package | Mini-project | What it demonstrates |
| --- | --- | --- |
| `com.thiago.calculator` | Calculator | Fundamental JUnit assertions for arithmetic operations, boundary conditions, and exceptions—without test doubles. |
| `com.thiago.ecommerce` | Order service | Isolating a service from its repository, stubbing return values and failures, verifying interactions, parameterized tests, and capturing saved arguments. |
| `com.thiago.authms` | Authentication service | Testing login and registration flows with mocked repositories and users, including valid credentials, missing users, invalid passwords, and duplicate registrations. |
| `com.thiago.bankguard` | Transaction validator | Testing rule-based transaction screening, including invalid amounts, failed-attempt blocks, international and PIX review thresholds, and boundary values. |

## Test doubles: replacing collaborators in a unit test

A **test double** is a controllable replacement for a real dependency. It lets a test focus on one unit of behavior instead of requiring a database, network service, or complex object graph. This makes tests fast, deterministic, and easy to diagnose.

| Double | What it is and how it works | Why it matters |
| --- | --- | --- |
| **Dummy** | An object passed only because a method needs an argument; the test does not rely on its behavior. For example, an `Order` used only to invoke `placeOrder`. | Keeps a test focused when a required input is not the subject of the assertion. |
| **Stub** | A preconfigured replacement that returns specific data, such as a repository returning an order for a known ID. In Mockito, this is commonly configured with `doReturn(...).when(...)`. | Creates predictable test scenarios without real infrastructure. |
| **Mock** | A replacement whose interactions are verified after the test, such as confirming that `repository.save(...)` was called once. Mockito creates mocks with `@Mock`; `verify(...)` checks the collaboration. | Ensures the unit communicates with its dependencies correctly. |
| **Spy** | A wrapper around a real object: real methods run by default, while selected methods can be stubbed or verified. | Useful when most real behavior is valuable but a small part needs observation or control; use sparingly to keep tests simple. |
| **Fake** | A lightweight working implementation, such as an in-memory repository, rather than a programmable mock. | Provides realistic behavior without the cost and fragility of an external system. |

The e-commerce and authentication test suites use Mockito mocks and stubs to isolate services, verify repository calls, and capture arguments. The authentication package also includes an `InMemoryUserRepository` as an example of a fake implementation.

## Stack

- Java 21
- Maven
- JUnit Jupiter 6.1.2
- Mockito 5.22.0
- PIT Mutation Testing 1.25.9

## Run the tests

Ensure Java 21 and Maven are installed, then run this command from the project root:

```bash
mvn test
```

Maven compiles the project and executes every test under `src/test/java`.

## Mutation testing with Pitest a.k.a PIT

Traditional tests confirm that the application behaves as expected. **Mutation testing** goes further: PIT deliberately introduces small changes—called *mutants*—into the production code, then runs the test suite against each change. This measures whether the tests would detect realistic defects, not just whether they execute a line of code.

- **Killed mutant:** at least one test failed after PIT changed the code. This is the desired result; the test suite caught the simulated defect.
- **Survived mutant:** all tests passed after the change. This highlights a potential gap in the test suite.
- **No coverage:** no test executed the changed code.

PIT complements code coverage: high line coverage means code was executed, while a strong mutation score shows that the assertions can detect incorrect behavior.

### Run mutation tests

From the project root, run:

```bash
mvn test-compile org.pitest:pitest-maven:mutationCoverage
```

PIT compiles the project, generates mutants for the production classes, runs the JUnit test suite, and creates an HTML report.

### View the mutation report

After a successful run, open `target/pit-reports/index.html` in a browser. On macOS, run:

```bash
open target/pit-reports/index.html
```

The report shows the mutation score for each class and method, the mutation applied, and the test that killed it when one is available. Start with surviving mutants: they are useful prompts for adding missing assertions, edge-case tests, or validation scenarios.

## Project structure

```text
src/
├── main/java/com/thiago/
│   ├── calculator/
│   ├── ecommerce/
│   ├── authms/
│   └── bankguard/
└── test/java/com/thiago/
    ├── calculator/
    ├── ecommerce/
    ├── authms/
    └── bankguard/
```

## What this project demonstrates

- Writing readable JUnit tests with descriptive names and `@DisplayName`
- Organizing related scenarios with nested test classes
- Testing normal behavior, validation rules, and exceptions
- Running parameterized tests for multiple input values
- Using Mockito annotations: `@Mock`, `@InjectMocks`, and `@Captor`
- Stubbing collaborators and verifying interactions
- Using argument captors to assert the values sent to dependencies
- Assessing test-suite effectiveness with PIT mutation testing
