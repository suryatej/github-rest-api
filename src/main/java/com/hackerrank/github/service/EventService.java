package com.hackerrank.github.service;

import com.hackerrank.github.model.Event;
import javassist.tools.web.BadHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface EventService {

   void deleteAll();

   void create(Event event) throws BadHttpRequest;

   Event getEventById(Long id) throws BadHttpRequest;

   List<Event> getAllEvents();

   Optional<List<Event>>  getEventByRepoId(Long repoId) ;

   Optional<List<Event>>  getEventByActorId(Long actorId) ;

   Optional<List<Event>>  getEventByRepoAndActorId(Long repoId, Long actorId) ;
}
