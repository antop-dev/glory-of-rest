package org.antop.xmlrpc.service;

import org.antop.xmlrpc.XmlRpcApplicationTests;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CalculatorServiceTest extends XmlRpcApplicationTests {

    @Test
    public void addRegular() throws Exception {
        XmlRpcClient client = getXmlRpcClient();
        // make the a regular call
        Object[] params = new Object[]{2, 3};
        Integer sum = (Integer) client.execute("Calculator.add", params);
        // verify
        assertThat(sum, equalTo(5));
    }

    @Test
    public void addDynamicProxy() throws Exception {
        Adder adder = getDynamicProxy(Adder.class);
        int sum = adder.add(2, 4);

        assertThat(sum, equalTo(6));
    }

    @Test
    public void subtractRegular() throws Exception {
        XmlRpcClient client = getXmlRpcClient();
        // make the a regular call
        Object[] params = new Object[]{2, 3};
        Integer result = (Integer) client.execute("Calculator.subtract", params);
        // verify
        assertThat(result, equalTo(-1));
    }

    @Test
    public void subtractDynamicProxy() throws Exception {
        Subtracter adder = getDynamicProxy(Subtracter.class);
        int result = adder.subtract(2, 4);

        assertThat(result, equalTo(-2));
    }
}