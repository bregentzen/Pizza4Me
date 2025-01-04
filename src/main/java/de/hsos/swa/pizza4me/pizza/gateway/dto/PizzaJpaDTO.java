package de.hsos.swa.pizza4me.pizza.gateway.dto;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Objects;

@Entity(name = "Pizza")
@Schema(name = "PIZZEN")
@NamedQuery(name = "PizzaJpaDTO.findById",
        query = "SELECT pizza FROM Pizza pizza where pizza.id=:id")
public class PizzaJpaDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double preis;

    public PizzaJpaDTO() {
    }

    public PizzaJpaDTO(Long id, String name, double preis) {
        this.id = id;
        this.name = name;
        this.preis = preis;
    }

    public PizzaJpaDTO(String name, double preis) {
        this.name = name;
        this.preis = preis;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Double getPreis() {
        return this.preis;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreis(Double preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return "PizzaJpaDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", preis=" + preis +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PizzaJpaDTO that)) return false;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(preis, that.preis);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (preis != null ? preis.hashCode() : 0);
        return result;
    }
}