package org.antop.http;

import org.antop.http.service.AppointmentService;
import org.antop.http.service.CabBookingService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract public class SpringHttpApplicationTests {
    @LocalServerPort
    private int port;

    protected final CabBookingService cabBookingservice() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:" + port + "/booking");
        invoker.setServiceInterface(CabBookingService.class);
        invoker.afterPropertiesSet();
        return (CabBookingService) invoker.getObject();
    }

    protected final AppointmentService appointmentService() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:" + port + "/appointment");
        invoker.setServiceInterface(AppointmentService.class);
        invoker.afterPropertiesSet();
        return (AppointmentService) invoker.getObject();
    }

}
