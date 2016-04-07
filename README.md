Hystrix Demo
=========================
This is a demonstration of the circuit breaker pattern with Netflix Hystrix and Spring Cloud

**Build**
```
mvn clean install
```
**Run Services**

Downstream System

```
cd downstream-system
mvn spring-boot:run
```
Service
```
cd service
mvn spring-boot:run
```
Test with client app
```
cd client
mvn spring-boot:run
```

Test with cURL
```
curl localhost:8080/demo
```
Set downstream latency with cURL (for example to 5 seconds)
```
curl -X POST localhost:9090/downstream/responseseconds/5
```
**Monitor Hystrix Stream in dashboard**

Open in browser:
http://localhost:8080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8080%2Fhystrix.stream&title=Hystrix%20Demo
