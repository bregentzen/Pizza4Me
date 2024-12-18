package de.hsos.swa.pizza4me.bestellung.boundary.dto;

import java.util.List;

public class BestellungIdWebDTO extends BestellungWebDTO {
    private Long id;

    public BestellungIdWebDTO() {
    }

    public BestellungIdWebDTO(Long id, List<BestellpostenIdWebDTO> bestellposten) {
        super(bestellposten);
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
        return "BestellungIdWebDTO{" +
                "id=" + id +
                ", bestellposten=" + getBestellposten() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BestellungIdWebDTO that)) return false;

        if (!id.equals(that.id)) return false;
        return getBestellposten().equals(that.getBestellposten());
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + getBestellposten().hashCode();
        return result;
    }
}
