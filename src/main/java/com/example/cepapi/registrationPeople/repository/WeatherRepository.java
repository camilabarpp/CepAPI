package com.example.cepapi.registrationPeople.repository;

import com.example.cepapi.registrationPeople.model.weather.WeatherEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends MongoRepository<WeatherEntity, String> {
}
