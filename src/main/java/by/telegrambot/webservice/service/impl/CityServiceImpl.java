package by.telegrambot.webservice.service.impl;

import by.telegrambot.webservice.dto.CityDTO;
import by.telegrambot.webservice.entity.City;
import by.telegrambot.webservice.repository.CityInfoRepo;
import by.telegrambot.webservice.repository.CityRepo;
import by.telegrambot.webservice.service.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@EnableJpaRepositories("by.telegrambot.webservice.repository")
public class CityServiceImpl implements CityService {
    private final CityRepo cityRepo;
    private final CityInfoRepo cityInfoRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public CityServiceImpl(CityRepo cityRepo, CityInfoRepo cityInfoRepo, ModelMapper modelMapper) {
        this.cityRepo = cityRepo;
        this.cityInfoRepo = cityInfoRepo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public City saveOrUpdate(City city) {
        return cityRepo.save(city);
    }


    @Override
    public Optional<City> getById(Long id) {
        return cityRepo.findById(id);
    }

    @CachePut(value = "citycachebyname")
    @Override
    public CityDTO findByNameDTO(String entity) {
        return convertToDto(cityRepo.findByName(entity));
    }

    @Override
    public City findByName(String entity) {
        return cityRepo.findByName(entity);
    }

    @CachePut(value = "findallcitydto")
    @Override
    public List<CityDTO> findAllCity() {
        List<CityDTO> dtoArrayList = new ArrayList<>();
        List<City> entityList = cityRepo.findAll();
        for (City cityInfo : entityList){
            dtoArrayList.add(convertToDto(cityInfo));
        }
        return dtoArrayList;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        cityRepo.deleteById(id);
    }

    @Override
    public List<City> findAll() {
        return cityRepo.findAll();
    }

    private CityDTO convertToDto(City city) {
        return modelMapper.map(city, CityDTO.class);
    }
    private City convertToEntity(CityDTO cityDTO) {
        return modelMapper.map(cityDTO, City.class);
    }
}
