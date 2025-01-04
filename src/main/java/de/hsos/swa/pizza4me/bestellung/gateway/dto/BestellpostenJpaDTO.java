package de.hsos.swa.pizza4me.bestellung.gateway.dto;

import de.hsos.swa.pizza4me.pizza.gateway.dto.PizzaJpaDTO;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class BestellpostenJpaDTO {
    @ManyToOne
    private PizzaJpaDTO pizza;
    private int anzahl;

    public BestellpostenJpaDTO() {}

    public BestellpostenJpaDTO(PizzaJpaDTO pizza, int anzahl) {
        this.pizza = pizza;
        this.anzahl = anzahl;
    }

    public PizzaJpaDTO getPizza() {
        return pizza;
    }

    public void setPizza(PizzaJpaDTO pizza) {
        this.pizza = pizza;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
}