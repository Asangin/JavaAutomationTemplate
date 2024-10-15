# Template for Java Automation

> A project designed to serve as a template for future projects, built on the General Test Automation Architecture (
> gTAA).

## Rebuild project

```bash
 mvn clean install -DskipTests
 ```

## Junit 5 Execution Layer

### Run test suite

```bash
mvn -pl Junit5ExecutionLayer clean -Dtest=Smoke test
```

### Run allure report

```bash
allure serve Junit5ExecutionLayer/target/allure-results 
```

## TestNG Execution Layer

### Run test suite

```bash
mvn -pl TestNgExecutionLayer clean ...
```

### Run allure report

```bash
allure serve TestNgExecutionLayer/target/allure-results 
```

## Update project version

```bash
mvn versions:set -DnewVersion=1.0.2-SNAPSHOT
```