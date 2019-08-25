package by.telegrambot.webservice.service.impl;

import by.telegrambot.webservice.dto.CityDTO;
import by.telegrambot.webservice.dto.CityInfoDTO;
import by.telegrambot.webservice.entity.City;
import by.telegrambot.webservice.entity.CityInfo;
import by.telegrambot.webservice.repository.CityInfoRepo;
import by.telegrambot.webservice.service.CityInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@EnableJpaRepositories("by.telegrambot.webservice.repository")
public class CityInfoServiceImpl implements CityInfoService {
    private final CityInfoRepo cityInfoRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public CityInfoServiceImpl(CityInfoRepo cityInfoRepo, ModelMapper modelMapper) {
        this.cityInfoRepo = cityInfoRepo;
        this.modelMapper = modelMapper;
    }
    @Override
    public CityInfoDTO findByNameDTO(String entity) {
        return convertToDto(cityInfoRepo.findByName(entity));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        cityInfoRepo.deleteById(id);
    }

    @Transactional
    @Override
    public CityInfo saveOrUpdate(CityInfo city) {
        return cityInfoRepo.save(city);
    }

    @Override
    public Optional<CityInfo> getById(Long id) {
        return cityInfoRepo.findById(id);
    }

    @Override
    public List<CityInfo> findAll() {
        return cityInfoRepo.findAll();
    }

    @Override
    public CityInfo findByName(String entity) {
        return null;
    }

    @Override
    public List<CityInfoDTO> cityConvertList(Set<CityInfo> cityInfoSet) {
        List<CityInfoDTO> cityInfoDTOArrayList = new ArrayList<>();
        for (CityInfo cityInfo : cityInfoSet){
            cityInfoDTOArrayList.add(convertToDto(cityInfo));
        }
        return cityInfoDTOArrayList;
    }

    @Override
    public List<CityInfoDTO> findAllCityInfo() {
        List<CityInfoDTO> dtoArrayList = new ArrayList<>();
        List<CityInfo> entityList = findAll();
        for (CityInfo cityInfo : entityList){
            dtoArrayList.add(convertToDto(cityInfo));
        }
        return dtoArrayList;
    }

    private CityInfoDTO convertToDto(CityInfo city) {
        return modelMapper.map(city, CityInfoDTO.class);
    }
}
