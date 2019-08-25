package org.antop.xmlrpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({XmlRpcProperties.class})
public class XmlRpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmlRpcApplication.class, args);
    }

}
