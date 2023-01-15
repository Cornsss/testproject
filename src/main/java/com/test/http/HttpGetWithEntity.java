package com.test.http;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

public class HttpGetWithEntity extends HttpEntityEnclosingRequestBase {
    private final static String METHOD_NAME = "GET";

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }
    public HttpGetWithEntity() {
        super();
    }
    public HttpGetWithEntity(final URI uri) {
        super();
        setURI(uri);
    }
    public HttpGetWithEntity(final String uri, String token) {
        super();
        setURI(URI.create(uri));
        addHeader("Authorization", "Bearer "+token);
    }
}