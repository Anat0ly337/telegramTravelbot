package by.telegrambot.webservice.service;


import by.telegrambot.webservice.dto.CityInfoDTO;
import by.telegrambot.webservice.entity.CityInfo;

import java.util.List;
import java.util.Set;

public interface CityInfoService extends CrudService<CityInfo, Long> {
    CityInfoDTO findByNameDTO(String entity);

    CityInfo findByName(String entity);

    List<CityInfoDTO> findAllCityInfo();

    List<CityInfoDTO> cityConvertList(Set<CityInfo> cityInfoSet);

}
