package com.example.cepapi.repository;

import com.example.cepapi.model.weather.WeatherEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends MongoRepository<WeatherEntity, String> {
}
