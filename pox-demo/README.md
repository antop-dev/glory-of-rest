# Swamp of POX<sup>`Plain of XML`</sup> demo project

HTTP<sup>`Hyper Text Transfer Protocol`</sup>를 RPC<sup>`Remote Procedure Call`</sup> 정도의 용도로만 사용하는 API 데모 프로젝트

Test

* org.antop.pox.PoxAppointmentTest

원문

> Level 0
>
> The starting point for the model is using HTTP as a transport system for remote interactions, but without using any of the mechanisms of the web. Essentially what you are doing here is using HTTP as a tunneling mechanism for your own remote interaction mechanism, usually based on Remote Procedure Invocation.
> 
> ![Figure 2: An example interaction at Level 0](https://i.imgur.com/Mn2Sen1.png)
> 
> Let's assume I want to book an appointment with my doctor. My appointment software first needs to know what open slots my doctor has on a given date, so it makes a request of the hospital appointment system to obtain that information. In a level 0 scenario, the hospital will expose a service endpoint at some URI. I then post to that endpoint a document containing the details of my request.
> 
> ```
> POST /appointmentService HTTP/1.1
> [various other headers]
> 
> <openSlotRequest date = "2010-01-04" doctor = "mjones"/>
> The server then will return a document giving me this information
> 
> HTTP/1.1 200 OK
> [various headers]
> 
> <openSlotList>
>   <slot start = "1400" end = "1450">
>     <doctor id = "mjones"/>
>   </slot>
>   <slot start = "1600" end = "1650">
>     <doctor id = "mjones"/>
>   </slot>
> </openSlotList>
> ```
> 
> I'm using XML here for the example, but the content can actually be anything: JSON, YAML, key-value pairs, or any custom format.
> 
> My next step is to book an appointment, which I can again do by posting a document to the endpoint.
> 
> ```
> POST /appointmentService HTTP/1.1
> [various other headers]
> 
> <appointmentRequest>
>   <slot doctor = "mjones" start = "1400" end = "1450"/>
>   <patient id = "jsmith"/>
> </appointmentRequest>
> If all is well I get a response saying my appointment is booked.
> 
> HTTP/1.1 200 OK
> [various headers]
> 
> <appointment>
>   <slot doctor = "mjones" start = "1400" end = "1450"/>
>   <patient id = "jsmith"/>
> </appointment>
> ```
> 
> If there is a problem, say someone else got in before me, then I'll get some kind of error message in the reply body.
> 
> ```
> HTTP/1.1 200 OK
> [various headers]
> 
> <appointmentRequestFailure>
>   <slot doctor = "mjones" start = "1400" end = "1450"/>
>   <patient id = "jsmith"/>
>   <reason>Slot not available</reason>
> </appointmentRequestFailure>
> ```
> 
> So far this is a straightforward RPC style system. It's simple as it's just slinging plain old XML (POX) back and forth. If you use SOAP or XML-RPC it's basically the same mechanism, the only difference is that you wrap the XML messages in some kind of envelope.