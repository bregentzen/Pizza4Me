package de.hsos.swa.pizza4me.bestellung.boundary.dto;

public class BestellpostenIdWebDTO extends BestellpostenWebDTO {
    private Long id;

    public BestellpostenIdWebDTO() {
    }

    public BestellpostenIdWebDTO(Long id, Long pizzaId, Integer anzahl) {
        super(pizzaId, anzahl);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BestellpostenIdWebDTO{" +
                "id=" + id +
                ", pizzaId=" + getPizzaId() +
                ", anzahl=" + getAnzahl() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BestellpostenIdWebDTO that)) return false;

        if (!id.equals(that.id)) return false;
        if (!getPizzaId().equals(that.getPizzaId())) return false;
        return getAnzahl().equals(that.getAnzahl());
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + getPizzaId().hashCode();
        result = 31 * result + getAnzahl().hashCode();
        return result;
    }
}
