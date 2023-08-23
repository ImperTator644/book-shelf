package com.bookshelf.frontservice.controller;

import com.bookshelf.frontservice.client.RestCallClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/search")
public class SearchController {
    private final RestCallClient restCallClient;

    public SearchController(RestCallClient restCallClient) {
        this.restCallClient = restCallClient;
    }

    @GetMapping()
    public String getBookOverviewPage(@RequestParam(required = false) String query, ModelMap model) {
        if (query == null) {
            return "forward:/";
        }
        var results = restCallClient.findBooksByQuery(query);
        model.put("searchResults", results);
        return "books/search-results";
    }
}
