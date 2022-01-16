# Book

## Initiate the Book project

### 0. Spring CLI

Initiate the project using spring cli with these dependencies.

- **webflux**: Build reactive web applications with Spring WebFlux and Netty.
- **data-mongodb-reactive**: Provides asynchronous stream processing with non-blocking back pressure for MongoDB.
- **flapdoodle-mongo**: Provides a platform neutral way for running MongoDB in unit tests.
- **actuator**: Supports built in (or custom) endpoints that let you monitor and manage your application - such as application health, metrics, sessions, etc.
- **cloud-stream**: Framework for building highly scalable event-driven microservices connected with shared messaging systems (requires a binder, e.g. Apache Kafka, RabbitMQ or Solace PubSub+).
- **oauth2-resource-server**: Spring Boot integration for Spring Security's OAuth2 resource server features.

```sh
spring init \
    --build gradle \
    --java-version 17 \
    --dependencies \
        webflux,data-mongodb-reactive,flapdoodle-mongo,actuator,cloud-stream,kafka,oauth2-resource-server \
    --group-id ml.mizotakhteh \
    --artifact-id book \
    --name book \
    --force ./
```

### 1. Prepare Dev Environment

- Set environment variable in a Unix environment

```sh
export spring_profiles_active=dev
```

- Run Docker compose

  prepare this containers:

    + **zookeeper**
    + **kafka**
    + **mongo**
    + **mongo-express**

### 2. Add ***Mongock*** Dependency

Add this two dependencies into the `build.gradle`

```groovy
dependencies {
//  ...
  implementation "com.github.cloudyrock.mongock:mongock-spring-v5:4.+"
  implementation "com.github.cloudyrock.mongock:mongodb-springdata-v3-driver:4.+"
//  ...
}
```

set *change-logs-scan-package* in the `application.yaml` : 

```yaml
#...
mongock:
  change-logs-scan-package:
    - ml.mizotakhteh.book.config.dbmigrations
#...
```
### 3. Add ***Jib*** Plugin Dependency

```groovy
plugins {
//  ...
  id 'com.google.cloud.tools.jib' version '3.+'
//  ...
}
```

## Test

### Get Token

```sh
TOKEN=$(curl \
  -s \
  -X POST \
  -H 'Content-type: application/x-www-form-urlencoded' \
  -d 'client_id=mizotakhteh' \
  -d 'grant_type=password' \
  -d 'username=hasan' \
  -d 'password=passw0rd' \
  https://keycloak.homesys.tk/auth/realms/homesys/protocol/openid-connect/token \
  | jq -r '.access_token')
```

### Get Books

```sh
curl \
  -s -v \
  -X GET \
  -H 'Content-Type: application/json' \
  -H "Authorization: Bearer $TOKEN" \
  localhost:8080/books
```
