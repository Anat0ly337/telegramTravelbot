package by.telegrambot.webservice.service;

import by.telegrambot.webservice.dto.CityDTO;
import by.telegrambot.webservice.dto.CityInfoDTO;
import by.telegrambot.webservice.entity.City;
import by.telegrambot.webservice.entity.CityInfo;

import java.util.List;

public interface CityService extends CrudService<City,Long> {
    CityDTO findByNameDTO(String entity);

    City findByName(String entity);

    List<CityDTO> findAllCity();
}
