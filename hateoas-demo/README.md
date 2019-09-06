#  Hypermedia Controls demo project

HATEOAS<sup>`Hypertext As The Engine Of Application State`</sup>를 사용한 데모 프로젝트

Test

* org.antop.hateoas.HateoasApplicationTest

원문

> Level 3 - Hypermedia Controls
>
> The final level introduces something that you often hear referred to under the ugly acronym of HATEOAS (Hypertext As The Engine Of Application State). It addresses the question of how to get from a list open slots to knowing what to do to book an appointment.
> 
> ![Figure 5: Level 3 adds hypermedia controls](https://i.imgur.com/xtLCXRO.png)
> 
> We begin with the same initial GET that we sent in level 2
> 
> ```
> GET /doctors/mjones/slots?date=20100104&status=open HTTP/1.1
> Host: royalhope.nhs.uk
> But the response has a new element
> 
> HTTP/1.1 200 OK
> [various headers]
> 
> <openSlotList>
>   <slot id = "1234" doctor = "mjones" start = "1400" end = "1450">
>      <link rel = "/linkrels/slot/book" 
>            uri = "/slots/1234"/>
>   </slot>
>   <slot id = "5678" doctor = "mjones" start = "1600" end = "1650">
>      <link rel = "/linkrels/slot/book" 
>            uri = "/slots/5678"/>
>   </slot>
> </openSlotList>
> ```
> 
> Each slot now has a link element which contains a URI to tell us how to book an appointment.
> 
> The point of hypermedia controls is that they tell us what we can do next, and the URI of the resource we need to manipulate to do it. Rather than us having to know where to post our appointment request, the hypermedia controls in the response tell us how to do it.
> 
> The POST would again copy that of level 2
> 
> ```
> POST /slots/1234 HTTP/1.1
> [various other headers]
> 
> <appointmentRequest>
>   <patient id = "jsmith"/>
> </appointmentRequest>
> And the reply contains a number of hypermedia controls for different things to do next.
> 
> HTTP/1.1 201 Created
> Location: http://royalhope.nhs.uk/slots/1234/appointment
> [various headers]
> 
> <appointment>
>   <slot id = "1234" doctor = "mjones" start = "1400" end = "1450"/>
>   <patient id = "jsmith"/>
>   <link rel = "/linkrels/appointment/cancel"
>         uri = "/slots/1234/appointment"/>
>   <link rel = "/linkrels/appointment/addTest"
>         uri = "/slots/1234/appointment/tests"/>
>   <link rel = "self"
>         uri = "/slots/1234/appointment"/>
>   <link rel = "/linkrels/appointment/changeTime"
>         uri = "/doctors/mjones/slots?date=20100104&status=open"/>
>   <link rel = "/linkrels/appointment/updateContactInfo"
>         uri = "/patients/jsmith/contactInfo"/>
>   <link rel = "/linkrels/help"
>         uri = "/help/appointment"/>
> </appointment>
> ```
> 
> One obvious benefit of hypermedia controls is that it allows the server to change its URI scheme without breaking clients. As long as clients look up the "addTest" link URI then the server team can juggle all URIs other than the initial entry points.
> 
> A further benefit is that it helps client developers explore the protocol. The links give client developers a hint as to what may be possible next. It doesn't give all the information: both the "self" and "cancel" controls point to the same URI - they need to figure out that one is a GET and the other a DELETE. But at least it gives them a starting point as to what to think about for more information and to look for a similar URI in the protocol documentation.
> 
> Similarly it allows the server team to advertise new capabilities by putting new links in the responses. If the client developers are keeping an eye out for unknown links these links can be a trigger for further exploration.
> 
> There's no absolute standard as to how to represent hypermedia controls. What I've done here is to use the current recommendations of the REST in Practice team, which is to follow ATOM (RFC 4287) I use a <link> element with a uri attribute for the target URI and a rel attribute for to describe the kind of relationship. A well known relationship (such as self for a reference to the element itself) is bare, any specific to that server is a fully qualified URI. ATOM states that the definition for well-known linkrels is the Registry of Link Relations . As I write these are confined to what's done by ATOM, which is generally seen as a leader in level 3 restfulness.