package de.hsos.swa.pizza4me.bestellung.boundary.dto;

import de.hsos.swa.pizza4me.kunde.boundary.dto.KundeIdWebDTO;
import java.util.List;

public class BestellungWebDTO {
    private List<BestellpostenWebDTO> bestellposten;

    public BestellungWebDTO() {}

    public BestellungWebDTO(List<BestellpostenWebDTO> bestellposten) {
        this.bestellposten = bestellposten;
    }

    public List<BestellpostenWebDTO> getBestellposten() {
        return bestellposten;
    }

    public void setBestellposten(List<BestellpostenWebDTO> bestellposten) {
        this.bestellposten = bestellposten;
    }
}