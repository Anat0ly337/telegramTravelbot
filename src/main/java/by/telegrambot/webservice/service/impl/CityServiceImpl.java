package by.telegrambot.webservice.service.impl;

import by.telegrambot.webservice.entity.City;
import by.telegrambot.webservice.repository.CityRepo;
import by.telegrambot.webservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
@EnableJpaRepositories("by.telegrambot.webservice.repository")
public class CityServiceImpl implements CityService {
    @Autowired
    private final CityRepo cityRepo;

    public CityServiceImpl(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    @Override
    public City save(City entityDto) {
        return cityRepo.save(entityDto);
    }

    @Override
    public City findByName(City entityDto) {
        return null;
    }

    @Override
    public void delete(City entityDto) {

    }

    @Override
    public List<City> findAll() {
        return cityRepo.findAll();
    }
}
