package de.hsos.swa.pizza4me.bestellung.boundary.dto;

import de.hsos.swa.pizza4me.pizza.boundary.dto.PizzaIdWebDTO;

public class BestellpostenWebDTO {
    private long pizza;
    private int anzahl;

    public BestellpostenWebDTO() {}

    public BestellpostenWebDTO(long pizza, int anzahl) {
        this.pizza = pizza;
        this.anzahl = anzahl;
    }

    public long getPizza() {
        return pizza;
    }

    public void setPizza(long pizza) {
        this.pizza = pizza;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
}