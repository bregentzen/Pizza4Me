package de.hsos.swa.pizza4me.bestellung.boundary.dto;

import de.hsos.swa.pizza4me.kunde.boundary.dto.KundeIdWebDTO;

import java.util.List;

public class BestellungIdWebDTO extends BestellungWebDTO {
    private long id;

    public BestellungIdWebDTO() {}

    public BestellungIdWebDTO(long id, List<BestellpostenWebDTO> bestellposten) {
        super(bestellposten);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}