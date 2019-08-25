package by.telegrambot.webservice.dto;

import lombok.Data;

@Data
public class CityInfoDTO {
    private String name;
    private String description;

    @Override
    public String toString() {
        return "CityInfoDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
