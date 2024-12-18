package de.hsos.swa.pizza4me.shared;

import java.util.List;

public class BestellungDTO {
    private long id;
    private List<BestellpostenDTO> bestellposten;

    public BestellungDTO() {
    }

    public BestellungDTO(long id, List<BestellpostenDTO> bestellposten) {
        this.id = id;
        this.bestellposten = bestellposten;
    }

    public long getId() {
        return id;
    }

    public List<BestellpostenDTO> getBestellposten() {
        return bestellposten;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBestellposten(List<BestellpostenDTO> bestellposten) {
        this.bestellposten = bestellposten;
    }

    @Override
    public String toString() {
        return "BestellungDTO{" +
                "id=" + id +
                ", bestellposten=" + bestellposten +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BestellungDTO that)) return false;

        if (id != that.id) return false;
        return bestellposten.equals(that.bestellposten);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + bestellposten.hashCode();
        return result;
    }
}
