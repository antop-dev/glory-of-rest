# Resources demo project

리소스 개념<sup>`resources`</sup>을 사용한 API 데모 프로젝트

Test

* org.antop.resources.ResourcesAppointmentTest

원문

> Level 1 - Resources
> 
> The first step towards the Glory of Rest in the RMM is to introduce resources. So now rather than making all our requests to a singular service endpoint, we now start talking to individual resources.
> 
> ![Figure 3: Level 1 adds resources](https://i.imgur.com/TAT0uzM.png)
> 
> So with our initial query, we might have a resource for given doctor.
> 
> ```
> POST /doctors/mjones HTTP/1.1
> [various other headers]
> 
> <openSlotRequest date = "2010-01-04"/>
> The reply carries the same basic information, but each slot is now a resource that can be addressed individually.
> 
> HTTP/1.1 200 OK
> [various headers]
> 
> 
> <openSlotList>
>   <slot id = "1234" doctor = "mjones" start = "1400" end = "1450"/>
>   <slot id = "5678" doctor = "mjones" start = "1600" end = "1650"/>
> </openSlotList>
> ```
>
> With specific resources booking an appointment means posting to a particular slot.
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
> If all goes well I get a similar reply to before.
> 
> ```
> HTTP/1.1 200 OK
> [various headers]
> 
> <appointment>
>   <slot id = "1234" doctor = "mjones" start = "1400" end = "1450"/>
>   <patient id = "jsmith"/>
> </appointment>
> ```
> 
> The difference now is that if anyone needs to do anything about the appointment, like book some tests, they first get hold of the appointment resource, which might have a URI like http://royalhope.nhs.uk/slots/1234/appointment, and post to that resource.
> 
> To an object guy like me this is like the notion of object identity. Rather than calling some function in the ether and passing arguments, we call a method on one particular object providing arguments for the other information.