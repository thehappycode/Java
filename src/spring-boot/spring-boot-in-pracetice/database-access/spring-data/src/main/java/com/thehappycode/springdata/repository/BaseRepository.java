package com.thehappycode.springdata.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;


@NoRepositoryBean
public interface BaseRepository<T, ID> extends Repository<T, ID>{

    <S extends T> S save(S entity);
    Iterable<T> findAll();
}

