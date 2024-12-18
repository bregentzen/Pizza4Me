package de.hsos.swa.pizza4me.bestellung.boundary.dto;

import java.util.Objects;

public class BestellpostenWebDTO {
    private Long pizzaId;
    private Integer anzahl;

    public BestellpostenWebDTO() {
    }

    public BestellpostenWebDTO(Long pizzaId, int anzahl) {
        this.pizzaId = pizzaId;
        this.anzahl = anzahl;
    }

    public Long getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public Integer getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    @Override
    public String toString() {
        return "BestellpostenWebDTO{" +
                ", pizzaId=" + pizzaId +
                ", anzahl=" + anzahl +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BestellpostenWebDTO that)) return false;

        if (anzahl != that.anzahl) return false;
        return Objects.equals(pizzaId, that.pizzaId);
    }

    @Override
    public int hashCode() {
        int result = pizzaId != null ? pizzaId.hashCode() : 0;
        result = 31 * result + anzahl;
        return result;
    }
}
