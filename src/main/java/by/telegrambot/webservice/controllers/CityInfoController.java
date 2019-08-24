package by.telegrambot.webservice.controllers;

import by.telegrambot.webservice.entity.CityInfo;
import by.telegrambot.webservice.service.CityInfoService;
import by.telegrambot.webservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cityinfo")
public class CityInfoController {
    private final CityInfoService cityInfoService;

    @Autowired
    public CityInfoController(CityInfoService cityInfoService) {
        this.cityInfoService = cityInfoService;
    }

    @GetMapping(value = "/places")
    public ResponseEntity<List<CityInfo>> getAll() {
        return new ResponseEntity<>(cityInfoService.findAll(), HttpStatus.OK);
    }
}
