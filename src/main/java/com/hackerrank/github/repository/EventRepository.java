package com.hackerrank.github.repository;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.model.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    Optional<List<Event>> findByRepo(Repo aLong);

    Optional<List<Event>> findByActor(Actor aLong);

    Optional<List<Event>> findByRepoAndActor(Repo repo, Actor actor);
}
