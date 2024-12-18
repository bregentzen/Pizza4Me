package de.hsos.swa.pizza4me.bestellung.gateway.dto;

import java.util.List;
import java.util.Objects;

public class BestellungJpaDTO {
    private long id;
    private List<BestellpostenJpaDTO> bestellposten;

    public BestellungJpaDTO() {
    }

    public BestellungJpaDTO(long id, List<BestellpostenJpaDTO> bestellposten) {
        this.id = id;
        this.bestellposten = bestellposten;
    }

    public long getId() {
        return id;
    }

    public List<BestellpostenJpaDTO> getBestellposten() {
        return bestellposten;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBestellposten(List<BestellpostenJpaDTO> bestellposten) {
        this.bestellposten = bestellposten;
    }

    @Override
    public String toString() {
        return "BestellungJpaDTO{" +
                "id=" + id +
                ", bestellposten=" + bestellposten +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BestellungJpaDTO that)) return false;

        if (id != that.id) return false;
        return Objects.equals(bestellposten, that.bestellposten);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (bestellposten != null ? bestellposten.hashCode() : 0);
        return result;
    }
}
