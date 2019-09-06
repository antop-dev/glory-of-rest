# Verbs demo project

HTTP 메소드<sup>`method`</sup>를 사용한 데모 프로젝트

Test

* org.antop.verbs.VerbsApplicationTest

원문

> Level 2 - HTTP Verbs
>
> I've used HTTP POST verbs for all my interactions here in level 0 and 1, but some people use GETs instead or in addition. At these levels it doesn't make much difference, they are both being used as tunneling mechanisms allowing you to tunnel your interactions through HTTP. Level 2 moves away from this, using the HTTP verbs as closely as possible to how they are used in HTTP itself.
> 
> ![Figure 4: Level 2 addes HTTP verbs](https://i.imgur.com/R8rUe8M.png)
> 
> For our the list of slots, this means we want to use GET.
> 
> GET /doctors/mjones/slots?date=20100104&status=open HTTP/1.1
> Host: royalhope.nhs.uk
> The reply is the same as it would have been with the POST
> 
> ```
> HTTP/1.1 200 OK
> [various headers]
> 
> <openSlotList>
>   <slot id = "1234" doctor = "mjones" start = "1400" end = "1450"/>
>   <slot id = "5678" doctor = "mjones" start = "1600" end = "1650"/>
> </openSlotList>
> ```
> 
> At Level 2, the use of GET for a request like this is crucial. HTTP defines GET as a safe operation, that is it doesn't make any significant changes to the state of anything. This allows us to invoke GETs safely any number of times in any order and get the same results each time. An important consequence of this is that it allows any participant in the routing of requests to use caching, which is a key element in making the web perform as well as it does. HTTP includes various measures to support caching, which can be used by all participants in the communication. By following the rules of HTTP we're able to take advantage of that capability.
> 
> To book an appointment we need an HTTP verb that does change state, a POST or a PUT. I'll use the same POST that I did earlier.
> 
> ```
> POST /slots/1234 HTTP/1.1
> [various other headers]
> 
> <appointmentRequest>
>   <patient id = "jsmith"/>
> </appointmentRequest>
> ```
> 
> The trade-offs between using POST and PUT here are more than I want to go into here, maybe I'll do a separate article on them some day. But I do want to point out that some people incorrectly make a correspondence between POST/PUT and create/update. The choice between them is rather different to that.
> 
> Even if I use the same post as level 1, there's another significant difference in how the remote service responds. If all goes well, the service replies with a response code of 201 to indicate that there's a new resource in the world.
> 
> ```
> HTTP/1.1 201 Created
> Location: slots/1234/appointment
> [various headers]
> 
> <appointment>
>   <slot id = "1234" doctor = "mjones" start = "1400" end = "1450"/>
>   <patient id = "jsmith"/>
> </appointment>
> ```
> 
> The 201 response includes a location attribute with a URI that the client can use to GET the current state of that resource in the future. The response here also includes a representation of that resource to save the client an extra call right now.
> 
> There is another difference if something goes wrong, such as someone else booking the session.
> 
> ```
> HTTP/1.1 409 Conflict
> [various headers]
> 
> <openSlotList>
>   <slot id = "5678" doctor = "mjones" start = "1600" end = "1650"/>
> </openSlotList>
> ```
> 
> The important part of this response is the use of an HTTP response code to indicate something has gone wrong. In this case a 409 seems a good choice to indicate that someone else has already updated the resource in an incompatible way. Rather than using a return code of 200 but including an error response, at level 2 we explicitly use some kind of error response like this. It's up to the protocol designer to decide what codes to use, but there should be a non-2xx response if an error crops up. Level 2 introduces using HTTP verbs and HTTP response codes.
> 
> There is an inconsistency creeping in here. REST advocates talk about using all the HTTP verbs. They also justify their approach by saying that REST is attempting to learn from the practical success of the web. But the world-wide web doesn't use PUT or DELETE much in practice. There are sensible reasons for using PUT and DELETE more, but the existence proof of the web isn't one of them.
> 
> The key elements that are supported by the existence of the web are the strong separation between safe (eg GET) and non-safe operations, together with using status codes to help communicate the kinds of errors you run into.