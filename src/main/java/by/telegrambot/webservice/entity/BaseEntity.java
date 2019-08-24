package by.telegrambot.webservice.entity;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
public abstract class BaseEntity <T> implements Serializable {
    private static final long serialVersionUID = 883847341013705469L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;
}
