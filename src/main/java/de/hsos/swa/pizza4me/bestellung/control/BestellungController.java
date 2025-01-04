package de.hsos.swa.pizza4me.bestellung.control;

import de.hsos.swa.pizza4me.bestellung.boundary.dto.BestellpostenWebDTO;
import de.hsos.swa.pizza4me.bestellung.boundary.dto.BestellungIdWebDTO;
import de.hsos.swa.pizza4me.bestellung.boundary.dto.BestellungWebDTO;
import de.hsos.swa.pizza4me.bestellung.control.converter.BestellpostenConverter;
import de.hsos.swa.pizza4me.bestellung.control.converter.BestellungConverter;
import de.hsos.swa.pizza4me.bestellung.entity.Bestellung;
import de.hsos.swa.pizza4me.bestellung.entity.BestellungGateway;
import de.hsos.swa.pizza4me.kunde.entity.Kunde;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BestellungController {

    @Inject
    BestellungGateway bestellungGateway;

    @Inject
    BestellungConverter bestellungConverter;

    @Inject
    BestellpostenConverter bestellpostenConverter;

    public List<BestellungWebDTO> getAllBestellungen(long kundeId) {
        List<Bestellung> bestellungen = bestellungGateway.getAllBestellungen().stream()
                .filter(b -> b.getKunde().getId() == kundeId)
                .toList();
        return bestellungen.stream()
                .map(bestellungConverter::toWebDTO)
                .collect(Collectors.toList());
    }

    public BestellungWebDTO createBestellung(long kundeId, BestellungWebDTO bestellungWebDTO) {
        Kunde kunde = bestellungGateway.getKundeById(kundeId);
        Bestellung bestellung = bestellungConverter.toEntity(bestellungWebDTO, kunde);
        Bestellung createdBestellung = bestellungGateway.createBestellung(bestellung);
        return createdBestellung != null ? bestellungConverter.toWebDTO(createdBestellung) : null;
    }

    public BestellungIdWebDTO getBestellungById(long kundeId, long bestellungId) {
        Bestellung bestellung = bestellungGateway.getBestellungById(bestellungId);
        if (bestellung == null || bestellung.getKunde().getId() != kundeId) {
            return null;
        }
        return bestellungConverter.toWebDTO(bestellung);
    }

    public BestellungIdWebDTO addBestellposten(long kundeId, long bestellungId, BestellpostenWebDTO bestellpostenWebDTO) {
        Bestellung bestellung = bestellungGateway.getBestellungById(bestellungId);
        if (bestellung == null || bestellung.getKunde().getId() != kundeId) {
            return null;
        }

        bestellung.getBestellposten().add(bestellpostenConverter.toEntity(bestellpostenWebDTO));
        Bestellung updatedBestellung = bestellungGateway.updateBestellung(bestellung.getKunde(), bestellungId, bestellpostenConverter.toEntity(bestellpostenWebDTO));
        return bestellungConverter.toWebDTO(updatedBestellung);
    }

    public boolean deleteBestellung(long kundeId, long bestellungId) {
        Bestellung bestellung = bestellungGateway.getBestellungById(bestellungId);
        if (bestellung == null || bestellung.getKunde().getId() != kundeId) {
            return false;
        }
        bestellungGateway.deleteBestellung(bestellungId);
        return true;
    }
}