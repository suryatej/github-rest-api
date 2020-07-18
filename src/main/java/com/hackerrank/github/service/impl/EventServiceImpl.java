package com.hackerrank.github.service.impl;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.model.Repo;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import com.hackerrank.github.repository.RepoRepository;
import com.hackerrank.github.service.EventService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RepoRepository repoRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public void deleteAll() {
      this.eventRepository.deleteAll();
    }

    @Override
    public void create(Event event) throws BadHttpRequest {
        if(event.getId() != null){
            Optional<Event> optionalTrade = this.eventRepository.findById(event.getId());

            if (optionalTrade.isPresent()) {
                throw new BadHttpRequest();
            }
        }

        this.actorRepository.save(event.getActor());
        this.repoRepository.save(event.getRepo());
        this.eventRepository.save(event);
    }

    @Override
    public Event getEventById(Long id) throws BadHttpRequest {
        return this.eventRepository.findById(id).orElseThrow(BadHttpRequest::new);
    }

    @Override
    public List<Event> getAllEvents() {
        return this.eventRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

    @Override
    public Optional<List<Event>>  getEventByRepoId(Long repoId) {
        Optional<Repo> repoRepositoryById = this.repoRepository.findById(repoId);
        if(!repoRepositoryById.isPresent()){
            return Optional.empty();
        }
        return this.eventRepository.findByRepo(repoRepositoryById.get());
    }

    @Override
    public  Optional<List<Event>>  getEventByActorId(Long actorId)  {
        Optional<Actor> actorRepositoryById = this.actorRepository.findById(actorId);
        if(!actorRepositoryById.isPresent()){
            return Optional.empty();
        }
        return this.eventRepository.findByActor(actorRepositoryById.get());
    }

    @Override
    public Optional<List<Event>> getEventByRepoAndActorId(Long repoId, Long actorId) {
        Optional<Repo> repoRepositoryById = this.repoRepository.findById(repoId);
        if(!repoRepositoryById.isPresent()){
            return Optional.empty();
        }
        Optional<Actor> actorRepositoryById = this.actorRepository.findById(actorId);
        if(!actorRepositoryById.isPresent()){
            return Optional.empty();
        }
        return this.eventRepository.findByRepoAndActor(repoRepositoryById.get(),actorRepositoryById.get());
    }
}
