package com.hz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface EntityService<T, PK extends Serializable> {
    T get(PK var1);

    void delete(PK var1);

    void delete(Iterable<T> var1);

    void delete(T var1);

    void save(T var1);

    Iterable<T> getAll();

    Page<T> search(Map<String, Object> var1, int var2, int var3, String var4);

    List<T> findAll(Map<String, Object> var1);

    List<T> findAll(Map<String, Object> var1, Sort var2);
}