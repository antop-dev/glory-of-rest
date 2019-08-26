package org.antop.http;

import org.antop.http.service.AppointmentService;
import org.antop.http.service.AppointmentServiceImpl;
import org.antop.http.service.CabBookingService;
import org.antop.http.service.CabBookingServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
public class RemoteConfig {

    @Bean(name = "/booking")
    HttpInvokerServiceExporter booking() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(new CabBookingServiceImpl());
        exporter.setServiceInterface(CabBookingService.class);
        return exporter;
    }

    @Bean(name = "/appointment")
    HttpInvokerServiceExporter appointment() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(new AppointmentServiceImpl());
        exporter.setServiceInterface(AppointmentService.class);
        return exporter;
    }

}
