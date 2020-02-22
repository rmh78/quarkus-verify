# Quarkus - Integration Tests

## (1) Background 

This Quarkus project uses docker-compose for setting up an integration-test environment which contains:

* Postgres DB
* Kafka Broker
* Kafka Zookeeper
* Quarkus application

I'm using Mavens integration-test and verify phase to run my integration-tests against the docker-compose environment. The test-classes are normal JUnit5 tests without the Quarkus annotations ``@QuarkusTest`` and ``@NativeImageTest``. 

This mechanism addresses an integration-test szenario where all real components (postgres, kafka, etc.) and the application itself are in place for being tested from outside via REST calls. Without the need to deploy the whole stuff to your target cloud environment.

For these integration-tests i have some additional Maven plugins in place:

1. ``docker-compose-maven-plugin:up`` - start the whole integration-test environment
2. ``await-maven-plugin:Await`` - wait until the Quarkus health endpoint is UP
3. ``maven-failsafe-plugin:integration-test`` - run all integration tests (*IT.java)
4. ``docker-compose-maven-plugin:down`` - stop the integration-test environment

## (2) run locally

### (2.1) run integration-tests in JVM mode

```bash
mvn verify
```

### (2.2) run integration-tests in native mode

```bash
mvn verfiy -Pnative
```

### (2.3) run the integration-tests from vscode

1. Build and start the environment

    ```bash
    mvn package && docker-compose up --build
    ```

2. Use VSCode test-runner to run the tests against the environment

## (3) run on Jenkins

``mvn verify`` can be used at any buildpipeline too.