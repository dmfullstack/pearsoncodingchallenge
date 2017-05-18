# pearsoncodingchallenge

This application implements RESTFull service using java and spring.
This is spring boot application with embedded tomcat server.

How to run application:
Prerequisites:Maven,java1.8

steps:
1)clone repository
2)cd to repository home
3)cd codingchallenge/codingchallenge
4)run command : mvn clean install spring-boot:run

Endpoints available:

1)To retrieve all stores (if orderBy param is not given default sort is by CITY)

localhost:8080/stores?orderBy=CITY (GET)
localhost:8080/stores?orderBy=OPENED_DATE (GET)

2)To retrieve single store by id

localhost:8080/{id} (GET)

3)To create store

localhost:8080/stores (POST), requestbody storedata json 

example for request body:
{
  "storeId": "testStore"
}

Note:Stores details from stores.csv are cached for 1 hr. So any changes made in stores.csv wont be picked up when endpoints are called
