package com.bookshelf.restcall.api;

import com.bookshelf.restcall.dto.BookResponse;
import com.bookshelf.restcall.service.BnApiCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "api/books")
@Slf4j
public class TestController {
    private final RestTemplate restTemplate;
    private final BnApiCallService bnApiCallService;

    public TestController(RestTemplate restTemplate, BnApiCallService bnApiCallService) {
        this.restTemplate = restTemplate;
        this.bnApiCallService = bnApiCallService;
    }

    @GetMapping(value = "/jeden")
    public BookResponse bookResponseJeden() {
        var url = bnApiCallService.genre("Haiku").limit(1).build();
        log.info("URL {}", url);
        return restTemplate.getForObject(url, BookResponse.class);
    }

    @GetMapping(value = "/trzy")
    public BookResponse bookResponseTrzy() {
        var url = bnApiCallService.limit(4).title("Człowiek nietoperz").build();
        log.info("URL {}", url);
        return restTemplate.getForObject(url, BookResponse.class);
    }

    @GetMapping(value = "/cztery")
    public BookResponse bookResponseCztery() {
        var url = bnApiCallService.limit(4).isbnIssn("8372983747").build();
        log.info("URL {}", url);
        return restTemplate.getForObject(url, BookResponse.class);
    }
}
