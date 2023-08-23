package com.bookshelf.restcall.api;

import com.bookshelf.restcall.dto.VolumeInfo;
import com.bookshelf.restcall.service.GoogleBookApiCallService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/book-search")
public class BookSearchController {

    private final GoogleBookApiCallService googleBookApiCallService;

    public BookSearchController(GoogleBookApiCallService googleBookApiCallService) {
        this.googleBookApiCallService = googleBookApiCallService;
    }

    @GetMapping()
    public List<VolumeInfo> findBooksByQuery(@RequestParam(value = "q") String query) {
        return googleBookApiCallService.findBooksByQuery(query);
    }
}
