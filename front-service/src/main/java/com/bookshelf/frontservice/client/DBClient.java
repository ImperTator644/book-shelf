package com.bookshelf.frontservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "database-service")
public interface DBClient {}
