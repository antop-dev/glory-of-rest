![](https://img.shields.io/github/issues/antop-dev/glory-of-rest.svg)
![](https://img.shields.io/github/forks/antop-dev/glory-of-rest.svg)
![](https://img.shields.io/github/stars/antop-dev/glory-of-rest.svg)
![](https://img.shields.io/github/license/antop-dev/glory-of-rest.svg)
[![Build Status](https://travis-ci.com/antop-dev/glory-of-rest.svg?branch=master)](https://travis-ci.com/antop-dev/glory-of-rest)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=antop-dev_glory-of-rest&metric=alert_status)](https://sonarcloud.io/dashboard?id=antop-dev_glory-of-rest)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=antop-dev_glory-of-rest&metric=coverage)](https://sonarcloud.io/dashboard?id=antop-dev_glory-of-rest)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=antop-dev_glory-of-rest&metric=bugs)](https://sonarcloud.io/dashboard?id=antop-dev_glory-of-rest)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=antop-dev_glory-of-rest&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=antop-dev_glory-of-rest)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=antop-dev_glory-of-rest&metric=code_smells)](https://sonarcloud.io/dashboard?id=antop-dev_glory-of-rest)

# REST의 영광을 향한 단계들

![Imgur](https://i.imgur.com/933vXkO.png)

(Leonard Richardson이 개발한) REST 방식의 주요 요소들을 3개의 단계로 나눈 모델. 이 모델은 리소스, HTTP 메소드(verbs), 하이퍼미디어 컨트롤을 도입한다.

## Level 0: The Swamp of POX<sup>`Plain Old XML`</sup>

HTTP<sup>`Hyper Text Transfer Protocol`</sup>를 사용하는 RPC<sup>`Remote Procedure Call`</sup> 정도에 불과하다.

* 하나의 URI<sup>`Uniform Resource Identifier`</sup>
* 하나의 HTTP 메소드<sup>`method`</sup>를 사용 (보통 `POST`)
* 특정 규칙을 통해 원격지의 프로시저<sup>`Procedure`</sup>를 호출하고 답을 받는다.

**Demo**

* [pox-demo](./pox-demo)

REST<sup>`Representational State Transfer`</sup>를 사용하기 전 즐겨쓰던 RPC 방식

* [json-rpc-demo](./json-rpc-demo)
* [xml-rpc-demo](./xml-rpc-demo)
* [soap-ws-demo](./soap-ws-demo)
* [spring-http-demo](./spring-http-demo)

## Level 1: Resources

URI<sup>`Uniform Resource Identifier`</sup>에 리소스<sup>`Resources`</sup> 개념을 도입한다.

**레벨0 → 레벨1**

* 모든 요청을 단일 서비스 엔드포인트<sup>`endpoint`</sup>로 보내는 것이 아니라, **개별 리소스**와 통신한다.

**Demo**

* [resources-demo](./resources-demo)

## Level 2: HTTP 메소드 (Verbs)

HTTP 메소드<sup>`method`</sup>를 추가로 도입한다. 이 단계까지 구현되면 **"RESTful하다"**, **"RESTFul API"** 라고 부른다.

**레벨1(레벨0) → 레벨2**

* HTTP 사용법에 가능한 가깝게 HTTP 메소드를 사용하여 요청한다.
* [HTTP 응답 코드](https://ko.wikipedia.org/wiki/HTTP_%EC%83%81%ED%83%9C_%EC%BD%94%EB%93%9C)를 사용하여 응답한다.

**Demo**

* [verbs-demo](./verbs-demo)

## Level 3: Hypermedia Controls

마지막 레벨은 HATEOAS<sup>`Hypertext As The Engine Of Application State`</sup>를 도입한다.

**레벨2 → 레벨3**

  * 앤드포인트 URL이 정해지고 나면 이를 변경하기 어렵다는 REST API의 단점을 보완한다.
  * 자원의 상태를 고려하지 않는 디자인을 보완한다.

Demo

* [hateoas-demo](./hateoas-demo)
* [bank-api-demo](./bank-api-demo)
* [spring-data-rest-demo](./spring-data-rest-demo)

## Reference

* [Richardson Maturity Model - Martin Fowler](https://martinfowler.com/articles/richardsonMaturityModel.html)
* [Richardson 성숙도 모델 - KSUG](https://brunch.co.kr/@pubjinson/12)
* [What is the Richardson Maturity Model? - Nordic APIs](https://nordicapis.com/what-is-the-richardson-maturity-model/)
* [Richardson Maturity Model - REST API Tutorial](https://restfulapi.net/richardson-maturity-model/)

파일

* [Postman 요청 테스트](./assets/postman-collection.json) ([Importing Postman data](https://learning.getpostman.com/docs/postman/collections/data_formats/#importing-postman-data))