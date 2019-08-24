package by.telegrambot.webservice.controllers;

import by.telegrambot.webservice.entity.City;
import by.telegrambot.webservice.service.CityInfoService;
import by.telegrambot.webservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
}
