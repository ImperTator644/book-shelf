package com.bookshelf.frontservice.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
@Slf4j
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {

        return (httpResponse.getStatusCode().isError());
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        log.warn("Service responded with status {}", httpResponse.getRawStatusCode());
    }
}
