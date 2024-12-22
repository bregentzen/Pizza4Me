package de.hsos.swa.pizza4me.kunde.control.converter;

import de.hsos.swa.pizza4me.kunde.boundary.dto.KundeIdWebDTO;
import de.hsos.swa.pizza4me.kunde.boundary.dto.KundeWebDTO;
import de.hsos.swa.pizza4me.kunde.entity.Adresse;
import de.hsos.swa.pizza4me.kunde.entity.Kunde;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.util.List;

@Dependent
public class KundenConverter {

    @Inject
    AdresseConverter adresseConverter;

    public KundeIdWebDTO toWebDtoWithId(Kunde kunde) {
        long id = kunde.getId();
        String vorname = kunde.getVorname();
        String nachname = kunde.getNachname();
        Adresse adresse = kunde.getAdresse();
        return new KundeIdWebDTO(id, vorname, nachname, adresseConverter.toWebDTO(adresse));
    }

    public Kunde toEntity(KundeWebDTO kundeWebDTO) {
        return new Kunde( kundeWebDTO.getVorname(), kundeWebDTO.getNachname(), adresseConverter.toEntity(kundeWebDTO.getAdresse()));
    }

    public List<KundeIdWebDTO> toWebDTOs(List<Kunde> kunden) {
        return kunden.stream().map(this::toWebDtoWithId).toList();
    }
}
