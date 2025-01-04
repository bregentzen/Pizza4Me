package de.hsos.swa.pizza4me.bestellung.control.converter;

import de.hsos.swa.pizza4me.bestellung.boundary.dto.BestellungIdWebDTO;
import de.hsos.swa.pizza4me.bestellung.boundary.dto.BestellungWebDTO;
import de.hsos.swa.pizza4me.bestellung.entity.Bestellung;
import de.hsos.swa.pizza4me.kunde.boundary.dto.KundeIdWebDTO;
import de.hsos.swa.pizza4me.kunde.entity.Kunde;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.util.stream.Collectors;

@Dependent
public class BestellungConverter {

    @Inject
    BestellpostenConverter bestellpostenConverter;

    public Bestellung toEntity(BestellungWebDTO dto, Kunde kunde) {
        if (dto == null) return null;
        return new Bestellung(
                0,
                dto.getBestellposten().stream().map(bestellpostenConverter::toEntity).collect(Collectors.toList()),
                kunde
        );
    }

    public BestellungIdWebDTO toWebDTO(Bestellung entity) {
        if (entity == null) return null;
        return new BestellungIdWebDTO(
                entity.getId(),
                entity.getBestellposten().stream().map(bestellpostenConverter::toWebDTO).collect(Collectors.toList())
        );
    }
}