package by.telegrambot.webservice.entity;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(of = {"name"})
@Entity
@Table(name = "CITYINFO")
public class CityInfo extends BaseEntity<Long> {
    private static final long serialVersionUID = 3212313771661405693L;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(targetEntity = City.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_ID")
    private City city;

}