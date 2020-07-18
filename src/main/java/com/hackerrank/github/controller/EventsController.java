package com.hackerrank.github.controller;

import com.hackerrank.github.model.Event;
import com.hackerrank.github.service.EventService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/events")
public class EventsController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Event event) {

        try {
            this.eventService.create(event);
        } catch (BadHttpRequest badHttpRequest) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@PathVariable Long id) {
        try {
            Event event = this.eventService.getEventById(id);
            return new ResponseEntity<>(event,HttpStatus.OK);
        } catch (BadHttpRequest badHttpRequest) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {
        return new ResponseEntity<>(this.eventService.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/repos/{repoID}")
    public ResponseEntity<List<Event>> getByRepoId(@PathVariable Long repoID) {
        Optional<List<Event>>  eventByRepoId = this.eventService.getEventByRepoId(repoID);
        if(!eventByRepoId.isPresent()){
           return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(eventByRepoId.get(), HttpStatus.OK);

    }

    @GetMapping("/actors/{actorID}")
    public ResponseEntity<List<Event>> getByActorId(@PathVariable Long actorID) {
        Optional<List<Event>> eventByRepoId = this.eventService.getEventByActorId(actorID);
        if(!eventByRepoId.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(eventByRepoId.get(), HttpStatus.OK);

    }

    @GetMapping("/repos/{repoID}/actors/{actorID}")
    public ResponseEntity<List<Event>> getByRepoAndActorId(@PathVariable Long repoID, @PathVariable Long actorID) {
        Optional<List<Event>> eventByRepoId = this.eventService.getEventByRepoAndActorId(repoID, actorID);
        if(!eventByRepoId.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(eventByRepoId.get(), HttpStatus.OK);

    }
}
