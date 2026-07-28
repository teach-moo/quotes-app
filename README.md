# Quotes App

First piece of a bigger project I'm working on. Right now it's a pretty simple quotes CRUD API, but the end goal is to use it as the target app for an incident response/observability platform running on k3s.

For now, it stores quotes, serves them through REST, and exposes metrics so Prometheus has something real to work with once I get to that part.

Built with Spring Boot + Postgres. Eventually it'll be running on a k3s cluster in DigitalOcean.

## Running it locally

You'll need JDK 17 and Docker Desktop.

Start Postgres: `docker compose up -d`

Run app: `.\mvnw.cmd spring-boot:run`

App should be up at http://localhost:8081.

## Stuff to mess with:

`/api/quotes` - CRUD

`/api/quotes/random` - gives you a random quote

`/actuator/health` - should return UP if Postgres is connected

`/actuator/prometheus` - raw metrics; this is the important one for what I'm building toward

One thing to remember: Postgres is running in Docker. If your machine's been off, run docker compose up -d again before starting the app or it won't boot.


## Tests
`.\mvnw.cmd test`

There are two types right now: quick unit tests using a mocked repository, and an integration test that spins up a disposable Postgres instance with Testcontainers.

The integration test is mainly there to make sure the actual JPA/Hibernate setup works against Postgres instead of everything looking good because the business logic passed with mocks.