package com.example.bai_thi_modun4.service;

import com.example.bai_thi_modun4.model.City;

import java.util.Optional;

public interface IGenerateService <C>{
    Iterable<C>findAll();
    C save (C c);
    Optional<C> findById(Long id);
    void remove(Long id);
}
