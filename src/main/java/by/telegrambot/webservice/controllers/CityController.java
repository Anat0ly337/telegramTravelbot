package by.telegrambot.webservice.controllers;

import by.telegrambot.webservice.dto.CityDTO;
import by.telegrambot.webservice.entity.BaseEntity;
import by.telegrambot.webservice.entity.City;
import by.telegrambot.webservice.entity.CityInfo;
import by.telegrambot.webservice.service.CityInfoService;
import by.telegrambot.webservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/city")
public class CityController {
    private final CityService cityService;
    private final CityInfoService cityInfoService;

    @Autowired
    public CityController(CityService cityService, CityInfoService cityInfoService) {
        this.cityService = cityService;
        this.cityInfoService = cityInfoService;
    }

    @GetMapping(value = "/allcities")
    public ResponseEntity<List<City>> getAll() {
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/recievecity")
    public ResponseEntity<CityDTO> getCityById(@RequestParam String name) {
        return new ResponseEntity<>(cityService.findByNameDTO(name),HttpStatus.OK);
    }

    @PutMapping("/create/{name}")
    public ResponseEntity<City> createOrUpdate(@RequestBody CityDTO cityDTO) {
        City city = new City();
        city.setName(cityDTO.getName());
        return new ResponseEntity<>(cityService.saveOrUpdate(city), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }
}


