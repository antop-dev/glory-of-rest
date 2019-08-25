package org.antop.xmlrpc;

import lombok.extern.slf4j.Slf4j;
import org.antop.xmlrpc.metadata.XmlRpc;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;
import org.reflections.Reflections;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class XmlRpcEndpoint {
    private final XmlRpcServletServer xmlRpcServer;

    public XmlRpcEndpoint(XmlRpcProperties xmlRpcProperties) throws XmlRpcException {
        xmlRpcServer = new XmlRpcServletServer();

        XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
        serverConfig.setEnabledForExtensions(true);
        serverConfig.setEnabledForExceptions(true);
        serverConfig.setContentLengthOptional(false);
        serverConfig.setBasicEncoding(XmlRpcServerConfigImpl.UTF8_ENCODING);

        PropertyHandlerMapping handlerMapping = new PropertyHandlerMapping();
        log.debug("scan XML-RPC component in {}", xmlRpcProperties.getScanPackage());
        Reflections reflections = new Reflections(xmlRpcProperties.getScanPackage());
        for (Class<?> cl : reflections.getTypesAnnotatedWith(XmlRpc.class)) {
            XmlRpc xmlRpc = cl.getAnnotation(XmlRpc.class);

            addHandler(handlerMapping, xmlRpc.value(), cl);
            addHandler(handlerMapping, cl.getCanonicalName(), cl);
            for (Class<?> anInterface : cl.getInterfaces()) {
                addHandler(handlerMapping, anInterface.getCanonicalName(), cl);
            }
        }
        xmlRpcServer.setHandlerMapping(handlerMapping);
    }

    private void addHandler(PropertyHandlerMapping handlerMapping, String name, Class<?> cl) throws XmlRpcException {
        // TODO: 이름이 겹친다면?
        handlerMapping.addHandler(name, cl);
        log.info("added XML-RPC: {} ({})", name, cl);
    }

    @PostMapping(path = "/xmlrpc")
    public void xmlRpc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        xmlRpcServer.execute(request, response);
    }

}
