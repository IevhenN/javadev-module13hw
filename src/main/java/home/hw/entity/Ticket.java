package home.hw.entity;

import home.hw.exception.InvalidDataEntityException;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

@Entity
@Table(name = "ticket")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "created_at",
            nullable = false,
            updatable = false,
            insertable = false,
            columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    public LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    public Client client;
    @ManyToOne
    @JoinColumn(name = "from_planet_id", nullable = false)
    public Planet fromPlanet;
    @ManyToOne
    @JoinColumn(name = "to_planet_id", nullable = false)
    public Planet toPlanet;

    @Override
    public String toString() {
        return "Ticket id: " + id +
                "; " + client.getName() +
                " from " + fromPlanet.getName() +
                " to " + toPlanet.getName() +
                "; created: " + createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                ".";
    }
}
