package de.hsos.swa.pizza4me.shared.dto;

public class BestellpostenDTO {
    private long id;
    private long pizzaId;
    private int anzahl;

    public BestellpostenDTO() {
    }

    public BestellpostenDTO(long id, long pizzaId, int anzahl) {
        this.id = id;
        this.pizzaId = pizzaId;
        this.anzahl = anzahl;
    }

    public long getId() {
        return id;
    }

    public long getPizzaId() {
        return pizzaId;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPizzaId(long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    @Override
    public String toString() {
        return "BestellpostenDTO{" +
                "id=" + id +
                ", pizzaId=" + pizzaId +
                ", anzahl=" + anzahl +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BestellpostenDTO that)) return false;

        if (id != that.id) return false;
        if (pizzaId != that.pizzaId) return false;
        return anzahl == that.anzahl;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + Long.hashCode(pizzaId);
        result = 31 * result + Integer.hashCode(anzahl);
        return result;
    }
}
