package de.hsos.swa.pizza4me.kunde.control.converter;

import de.hsos.swa.pizza4me.kunde.entity.Adresse;
import de.hsos.swa.pizza4me.kunde.entity.Kunde;
import de.hsos.swa.pizza4me.shared.dto.KundeDTO;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.util.List;

@Dependent
public class KundenConverter {

    @Inject
    AdresseConverter adresseConverter;

    public KundeDTO toDTO(Kunde kunde) {
        long id = kunde.getId();
        String vorname = kunde.getVorname();
        String nachname = kunde.getNachname();
        Adresse adresse = kunde.getAdresse();
        return new KundeDTO(id, vorname, nachname, adresseConverter.toDTO(adresse));
    }

    public Kunde toEntity(KundeDTO kundeDTO) {
        return new Kunde(kundeDTO.getId(), kundeDTO.getVorname(), kundeDTO.getNachname(), adresseConverter.toEntity(kundeDTO.getAdresse()));
    }

    public List<KundeDTO> toDTOs(List<Kunde> kunden) {
        return kunden.stream().map(this::toDTO).toList();
    }
}
