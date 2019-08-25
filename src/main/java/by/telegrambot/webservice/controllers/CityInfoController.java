package by.telegrambot.webservice.controllers;

import by.telegrambot.webservice.dto.CityDTO;
import by.telegrambot.webservice.dto.CityInfoDTO;
import by.telegrambot.webservice.entity.City;
import by.telegrambot.webservice.entity.CityInfo;
import by.telegrambot.webservice.service.CityInfoService;
import by.telegrambot.webservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/cityinfo")
public class CityInfoController {
    private final CityService cityService;
    private final CityInfoService cityInfoService;

    @Autowired
    public CityInfoController(CityService cityService, CityInfoService cityInfoService) {
        this.cityService = cityService;
        this.cityInfoService = cityInfoService;
    }

    @GetMapping(value = "/allplaces")
    public ResponseEntity<List<CityInfoDTO>> getAll() {
        return new ResponseEntity<>(cityInfoService.findAllCityInfo(), HttpStatus.OK);
    }
    @GetMapping("/place/{name}")
    public ResponseEntity<String> getDescription(@PathVariable String name){
        CityInfo cityInfo = cityInfoService.findByName(name);
        String description = cityInfo.getDescription();
        return new ResponseEntity<>(description,HttpStatus.OK);
    }

    @GetMapping("/placesbycity/{name}")
    public ResponseEntity<List<CityInfoDTO>> getCityByName(@PathVariable String name) {
        City city = cityService.findByName(name);
        Set<CityInfo> cityInfo = city.getCityInfo();
        return new ResponseEntity<>(cityInfoService.cityConvertList(cityInfo), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CityInfo> createOrUpdate(@RequestBody CityInfoDTO cityInfoDTO) {
        CityInfo cityInfo = new CityInfo();
        cityInfo.setName(cityInfoDTO.getName());
        return new ResponseEntity<>(cityInfoService.saveOrUpdate(cityInfo), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        cityInfoService.delete(id);
    }
}
