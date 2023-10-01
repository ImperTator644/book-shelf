package com.bookshelf.frontservice.controller;

import com.bookshelf.frontservice.client.RestCallClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/search")
public class SearchController {
    private final RestCallClient restCallClient;

    public SearchController(RestCallClient restCallClient) {
        this.restCallClient = restCallClient;
    }

    @PostMapping()
    public String getBookOverviewPage(@RequestParam(required = false) String query, ModelMap model) {
        if (query == null) {
            return "books/search-results";
        }
        var results = restCallClient.findBooksByQuery(query.replaceAll(StringUtils.SPACE, "+"));
        model.put("searchResults", results);
        model.put("previousQuery", query);
        return "books/search-results";
    }

    @GetMapping
    public String redirectToMainPage() {
        return "books/search-results";
    }
}
