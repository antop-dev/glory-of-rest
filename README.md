# REST의 영광을 향한 단계들

![Imgur](https://i.imgur.com/933vXkO.png)

(Leonard Richardson이 개발한) REST 방식의 주요 요소들을 3개의 단계로 나눈 모델. 이 모델은 리소스, HTTP 메소드(verbs), 하이퍼미디어 컨트롤을 도입한다.

## Level 0: The Swamp of POX<sup>`Plain Old XML`</sup>

HTTP<sup>`Hyper Text Transfer Protocol`</sup>를 사용하는 RPC<sup>`Remote Procedure Call`</sup> 정도에 불과하다.

* 하나의 URI<sup>`Uniform Resource Identifier`</sup>
* 하나의 Method<sup>`POST`</sup>
* 특정 규칙을 통해 원격지의 프로시저<sup>`Procedure`</sup>를 호출하고 답을 받는다.

**Demo**

* [pox-demo](./pox-demo)

REST<sup>`Representational State Transfer`</sup>를 사용하기 전 즐겨쓰던 RPC 방식

* [json-rpc-demo](./json-rpc-demo)
* [xml-rpc-demo](./xml-rpc-demo)
* [soap-ws-demo](./soap-ws-demo)
* [spring-http-demo](./spring-http-demo)

## Level 1: Resources

URI<sup>`Uniform Resource Identifier`</sup>에 리소스<sup>`Resources`</sup> 개념을 도입한다. 모든 요청을 단일 서비스 엔드포인트<sup>`endpoint`</sup>로 보내는 것이 아니라, **개별 리소스**와 통신한다.

**Demo**

* [resources-demo](./resources-demo)

## Level 2: HTTP 메소드 (Verbs)

HTTP 메소드<sup>`method`</sup>를 추가로 도입한다. 이 단계까지 구현되면 **"RESTful하다"**, **"RESTFul API"** 라고 부른다.

Demo

* [verbs-demo](./verbs-demo)

## Level 3: Hypermedia Controls

마지막 레벨은 HATEOAS<sup>`Hypertext As The Engine Of Application State`</sup>를 도입한다.

Demo

* [hateoas-demo](./hateoas-demo)
* [spring-rest-demo](./spring-data-rest)

## Reference

* [Richardson Maturity Model - Martin Fowler](https://martinfowler.com/articles/richardsonMaturityModel.html)
* [Richardson 성숙도 모델 - KSUG](https://brunch.co.kr/@pubjinson/12)
* [What is the Richardson Maturity Model? - Nordic APIs](https://nordicapis.com/what-is-the-richardson-maturity-model/)
* [Richardson Maturity Model - REST API Tutorial](https://restfulapi.net/richardson-maturity-model/)