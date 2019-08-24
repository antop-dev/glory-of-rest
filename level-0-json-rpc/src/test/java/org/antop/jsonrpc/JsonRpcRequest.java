package org.antop.jsonrpc;

import lombok.Data;

@Data
public class JsonRpcRequest {
    private String jsonrpc = "2.0";
    private int id;
    private String method;
    private Object params;

    public static JsonRpcRequest of(int id, String method, Object params) {
        JsonRpcRequest request = new JsonRpcRequest();
        request.id = id;
        request.method = method;
        request.params = params;

        return request;
    }

}
