package by.telegrambot.webservice.service.impl;

import by.telegrambot.webservice.entity.CityInfo;
import by.telegrambot.webservice.repository.CityInfoRepo;
import by.telegrambot.webservice.service.CityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true)
@EnableJpaRepositories("by.telegrambot.webservice.repository")
public class CityInfoServiceImpl implements CityInfoService {
    @Autowired
    private final CityInfoRepo cityInfoRepo;

    public CityInfoServiceImpl(CityInfoRepo cityInfoRepo) {
        this.cityInfoRepo = cityInfoRepo;
    }

    @Override
    public CityInfo save(CityInfo entityDto) {
       return cityInfoRepo.save(entityDto);
    }

    @Override
    public CityInfo findByName(CityInfo entityDto) {
        return cityInfoRepo.findByName("s");
    }

    @Override
    public void delete(CityInfo entityDto) {
        cityInfoRepo.delete(entityDto);

    }

    @Override
    public List<CityInfo> findAll() {
        return (List<CityInfo>) cityInfoRepo.findAll();
    }
}
