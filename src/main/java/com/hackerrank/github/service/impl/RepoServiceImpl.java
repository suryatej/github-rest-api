package com.hackerrank.github.service.impl;

import com.hackerrank.github.repository.RepoRepository;
import com.hackerrank.github.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;

public class RepoServiceImpl implements RepoService {

    @Autowired
    private RepoRepository repoRepository;

    @Override
    public void deleteAll() {
         this.repoRepository.deleteAll();
    }
}
