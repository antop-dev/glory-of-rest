package org.antop.xmlrpc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "xmlrpc")
public class XmlRpcProperties {
    private String scanPackage;
}
