package com.hackerrank.github.controller;

import com.hackerrank.github.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {
    @Autowired
    private EventService eventService;

    @DeleteMapping()
    public ResponseEntity<Void> deleteAll() {
        this.eventService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
