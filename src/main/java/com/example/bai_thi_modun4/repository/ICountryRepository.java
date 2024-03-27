package com.example.bai_thi_modun4.repository;

import com.example.bai_thi_modun4.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface ICountryRepository extends CrudRepository<Country,Long> {
}
