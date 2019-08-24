package org.antop.xmlrpc.service;

import org.antop.xmlrpc.metadata.XmlRpc;

@XmlRpc("Calculator")
public class CalculatorService implements Adder, Subtracter {

    @Override
    public int add(int i1, int i2) {
        return i1 + i2;
    }

    @Override
    public int subtract(int i1, int i2) {
        return i1 - i2;
    }

}
