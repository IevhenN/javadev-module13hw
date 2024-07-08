package home.hw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "planet")
@Data
public class Planet {
    @Id
    public String id;
    @Column(length = 500, nullable = false)
    public String name;
}
