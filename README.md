# pearsoncodingchallenge

This application implements RESTFull service in java.

How to run application:
Prerequisites:Maven,java1.8

steps:
1.clone repository
2.cd to repository home
3.cd codingchallenge/codingchallenge
4.run command : mvn clean install spring-boot:run

Endpoints available:

1)To retrieve all stores
localhost:8080/stores?orderBy=CITY (GET)
localhost:8080/stores?orderBy=OPENED_DATE (GET)

2)To retrieve single store by id
localhost:8080/{id} (GET)

3) To create store
localhost:8080/stores (POST), requestbody storedata json 
example for request body:
{
  "storeId": "testStore"
}

