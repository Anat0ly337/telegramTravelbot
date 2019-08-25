package by.telegrambot.webservice.service;

import by.telegrambot.webservice.dto.CityDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudService<E,PK extends Serializable> {


    E saveOrUpdate(E city);

    Optional<E> getById(Long id);

    void delete(Long id);

    List<E> findAll();
}
