package by.telegrambot.webservice.dto;

import lombok.Data;

@Data
public class CityDTO {
   private String name;

    @Override
    public String toString() {
        return "CityDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
