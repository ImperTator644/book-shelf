package com.luximed.client.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BnApiCallService {

    @Value("${bn.base.url}")
    private String bnBaseUrl;
    private StringBuilder urlCall;

    @PostConstruct
    private void init(){
        urlCall = new StringBuilder(bnBaseUrl);
    }

    public BnApiCallService limit(int limit) {
        return appendUrl("limit", Integer.toString(limit));
    }

    public BnApiCallService genre(String genre) {
        return appendUrl("genre", genre);
    }

    public BnApiCallService title(String title) {
        return appendUrl("title", title);
    }

    public BnApiCallService isbnIssn(String isbnIssn) {
        return appendUrl("isbnIssn", isbnIssn);
    }

    public String build() {
        var url = urlCall.toString();
        urlCall.setLength(bnBaseUrl.length());
        return url;
    }

    private BnApiCallService appendUrl(String query, String value){
        urlCall.append(query).append("=").append(value).append("&amp;");
        return this;
    }
}
