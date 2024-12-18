package de.hsos.swa.pizza4me.bestellung.boundary.dto;

import java.util.List;
import java.util.Objects;

public class BestellungWebDTO {
    private List<BestellpostenIdWebDTO> bestellposten;

    public BestellungWebDTO() {
    }

    public BestellungWebDTO(List<BestellpostenIdWebDTO> bestellposten) {
        this.bestellposten = bestellposten;
    }

    public List<BestellpostenIdWebDTO> getBestellposten() {
        return bestellposten;
    }

    public void setBestellposten(List<BestellpostenIdWebDTO> bestellposten) {
        this.bestellposten = bestellposten;
    }

    @Override
    public String toString() {
        return "BestellungWebDTO{" +
                "bestellposten=" + bestellposten +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BestellungWebDTO that)) return false;

        return Objects.equals(bestellposten, that.bestellposten);
    }

    @Override
    public int hashCode() {
        return bestellposten != null ? bestellposten.hashCode() : 0;
    }
}
