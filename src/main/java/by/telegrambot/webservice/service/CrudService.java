package by.telegrambot.webservice.service;

import java.io.Serializable;
import java.util.List;

public interface CrudService<E,PK extends Serializable> {
    E save(E entityDto);

    E findByName(E entityDto);

    void delete(E entityDto);

    List<E> findAll();
}
