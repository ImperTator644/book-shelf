package com.bookshelf.frontservice.controller;

import static org.apache.commons.lang3.StringUtils.SPACE;

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
            return "redirect:http://localhost:8080/";
        }
        var results = restCallClient.findBooksByQuery(query.replaceAll(SPACE, "+"));
        model.put("searchResults", results);
        model.put("previousQuery", query);
        return "books/search-results";
    }
}
