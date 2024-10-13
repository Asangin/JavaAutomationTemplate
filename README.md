# Template for Java Automation

> A project designed to serve as a template for future projects, built on the General Test Automation Architecture (gTAA).
> 


## Rebuild project

```bash
 mvn clean install -DskipTests
 ```

## Run Junit5 test suite

```bash
mvn -pl TestExecutionLayer -Dtest=SuiteDemo test
```

## Run Cucumber suite

```bash
mvn test
```

```bash
mvn -pl TestExecutionLayer,TestDefinitionLayer test -Dgroups="regression"
```

## Run allure report

```bash
allure serve TestExecutionLayer/target/allure-results 
```

## Update project version

```bash
mvn versions:set -DnewVersion=1.0.2-SNAPSHOT
```