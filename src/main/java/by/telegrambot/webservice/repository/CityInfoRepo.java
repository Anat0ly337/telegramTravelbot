package by.telegrambot.webservice.repository;

import by.telegrambot.webservice.entity.CityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityInfoRepo extends JpaRepository<CityInfo,Long> {
    CityInfo findByName(String name);
    CityInfo deleteCityInfoByName(String name);
}
