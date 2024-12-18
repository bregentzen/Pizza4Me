package de.hsos.swa.pizza4me.bestellung.entity;

import java.util.List;
import java.util.Objects;

public class Bestellung {
    private Long id;
    private List<Bestellposten> bestellposten;

    public Bestellung() {
    }

    public Bestellung(Long id, List<Bestellposten> bestellposten) {
        this.id = id;
        this.bestellposten = bestellposten;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Bestellposten> getBestellposten() {
        return bestellposten;
    }

    public void setBestellposten(List<Bestellposten> bestellposten) {
        this.bestellposten = bestellposten;
    }

    @Override
    public String toString() {
        return "Bestellung{" +
                "id=" + id +
                ", bestellposten=" + bestellposten +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bestellung that)) return false;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(bestellposten, that.bestellposten);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bestellposten != null ? bestellposten.hashCode() : 0);
        return result;
    }
}
