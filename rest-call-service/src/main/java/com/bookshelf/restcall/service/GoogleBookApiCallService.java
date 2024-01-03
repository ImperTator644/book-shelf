package com.bookshelf.restcall.service;

import static org.apache.commons.lang3.StringUtils.SPACE;

import com.bookshelf.restcall.dto.BookData;
import com.bookshelf.restcall.dto.BooksResponse;
import com.bookshelf.restcall.dto.VolumeInfo;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class GoogleBookApiCallService {
    private static final int MAX_RESULTS = 20;
    private static final String ENGLISH_LANG = "en";
    private static final String QUERY_PATTERN = "key=%s&maxResults=%d&q=intitle:%s";
    private static final String AUTHOR_QUERY_PATTERN = "key=%s&maxResults=%d&q=inauthor:%s";

    @Value("${google.books.api.url}")
    private String googleApiUrl;

    @Value("${google.books.api.token}")
    private String googleToken;

    private final RestTemplate restTemplate;

    public GoogleBookApiCallService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<VolumeInfo> findBooksByQuery(String query) {
        var result = findBooksByTitle(query).orElse(Collections.emptyList());
        result.addAll(findBooksByAuthor(query).orElse(Collections.emptyList()));
        log.info("Found {} results", result.size());
        return result;
    }

    public List<VolumeInfo> findBooksByQueryAuthor(String query) {
        var result = this.findAllBooksByAuthor(query.replaceAll(SPACE, "+")).orElse(Collections.emptyList());
        log.info("Found {} results", result.size());
        return result;
    }

    private Optional<List<VolumeInfo>> findBooksByTitle(String title) {
        var url = String.format(googleApiUrl + QUERY_PATTERN, googleToken, MAX_RESULTS, title);
        return getGoogleBooks(url);
    }

    private Optional<List<VolumeInfo>> findBooksByAuthor(String author) {
        var url = String.format(googleApiUrl + AUTHOR_QUERY_PATTERN, googleToken, MAX_RESULTS, author);
        return getGoogleBooks(url);
    }

    private Optional<List<VolumeInfo>> findAllBooksByAuthor(String author) {
        var url = String.format(googleApiUrl + AUTHOR_QUERY_PATTERN, googleToken, 40, author);
        return getGoogleBooks(url);
    }

    private Optional<List<VolumeInfo>> getGoogleBooks(String url) {
        log.info("Searching books with Google API, url = {}", url);
        var response = Optional.ofNullable(restTemplate.getForObject(url, BooksResponse.class));
        return response.map(
                booksResponse -> Optional.ofNullable(booksResponse.getItems()).orElse(Collections.emptyList()).stream()
                        .filter(bookData -> {
                            if (ENGLISH_LANG.equals(bookData.getVolumeInfo().getLanguage())) {
                                setThumbnails(bookData);
                                return true;
                            }
                            return false;
                        })
                        .map(BookData::getVolumeInfo)
                        .collect(Collectors.toList()));
    }

    private void setThumbnails(BookData bookData) {
        var volumeInfo = bookData.getVolumeInfo();
        if (volumeInfo.getImageLinks() == null) {
            volumeInfo.setThumbnail("/images/empty-book-cover.webp");
            volumeInfo.setSmallThumbnail("/images/empty-book-cover.webp");
        } else {
            var imageLinks = volumeInfo.getImageLinks();
            volumeInfo.setThumbnail(imageLinks.getThumbnail());
            volumeInfo.setSmallThumbnail(imageLinks.getSmallThumbnail());
        }
    }
}
