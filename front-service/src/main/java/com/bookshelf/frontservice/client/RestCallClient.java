package com.bookshelf.frontservice.client;

import com.bookshelf.frontservice.dto.BookDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "rest-call-service")
public interface RestCallClient {
    @GetMapping(value = "/api/book-search")
    List<BookDto> findBooksByQuery(@RequestParam String q);
}
