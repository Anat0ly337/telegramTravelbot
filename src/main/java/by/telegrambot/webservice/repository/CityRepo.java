package by.telegrambot.webservice.repository;

import by.telegrambot.webservice.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends JpaRepository<City,Long> {
    City findByName(String name);
    City deleteCityByName(String name);
}
