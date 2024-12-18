package de.hsos.swa.pizza4me.bestellung.gateway.dto;

public class BestellpostenJpaDTO {
    private Long id;
    private Long pizzaId;
    private Integer anzahl;

    public BestellpostenJpaDTO() {
    }

    public BestellpostenJpaDTO(Long id, Long pizzaId, Integer anzahl) {
        this.id = id;
        this.pizzaId = pizzaId;
        this.anzahl = anzahl;
    }

    public Long getId() {
        return id;
    }

    public Long getPizzaId() {
        return pizzaId;
    }

    public Integer getAnzahl() {
        return anzahl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPizzaId(Long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    @Override
    public String toString() {
        return "BestellpostenJpaDTO{" +
                "id=" + id +
                ", pizzaId=" + pizzaId +
                ", anzahl=" + anzahl +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BestellpostenJpaDTO)) return false;

        BestellpostenJpaDTO that = (BestellpostenJpaDTO) o;

        if (id != that.id) return false;
        if (pizzaId != that.pizzaId) return false;
        return anzahl == that.anzahl;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pizzaId != null ? pizzaId.hashCode() : 0);
        result = 31 * result + (anzahl != null ? anzahl.hashCode() : 0);
        return result;
    }
}
