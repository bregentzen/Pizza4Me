package de.hsos.swa.pizza4me.kunde.control;

import de.hsos.swa.pizza4me.kunde.boundary.dto.KundeIdWebDTO;
import de.hsos.swa.pizza4me.kunde.boundary.dto.KundeWebDTO;
import de.hsos.swa.pizza4me.kunde.control.converter.KundenConverter;
import de.hsos.swa.pizza4me.kunde.entity.Kunde;
import de.hsos.swa.pizza4me.kunde.entity.KundenGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class KundenController {
    @Inject
    KundenGateway kundenGateway;

    @Inject
    KundenConverter converter;
    @Inject
    KundenConverter kundenConverter;

    public List<KundeIdWebDTO> getAllKunden() {
        List<Kunde> kunden = kundenGateway.getAllKunden();
        return converter.toWebDTOs(kunden);
    }

    public KundeIdWebDTO getKundeById(long id) {
        Kunde kunde = kundenGateway.getKundeById(id).isPresent() ? kundenGateway.getKundeById(id).get() : null;

        if (kunde != null) {
            return converter.toWebDtoWithId(kunde);
        }
        return null;
    }

    public boolean deleteKundeById(long id) {
        return kundenGateway.deleteKunde(id);
    }

    public KundeIdWebDTO updateKunde(KundeIdWebDTO kundeIdWebDTO) {
        Kunde kunde = converter.toEntity(kundeIdWebDTO);
        Kunde updatedKunde = kundenGateway.updateKunde(kunde).isPresent() ? kundenGateway.updateKunde(kunde).get() : null;

        if (updatedKunde != null) {
            return converter.toWebDtoWithId(updatedKunde);
        }
        return null;
    }

    public KundeIdWebDTO createKunde(KundeWebDTO kundeWebDTO) {
        Kunde kunde = converter.toEntity(kundeWebDTO);

        Kunde createdKunde = kundenGateway.createKunde(kunde).get();

        if (createdKunde != null) {
            return converter.toWebDtoWithId(createdKunde);
        }
        return null;
    }

    public KundeIdWebDTO getKundeByDetails(String vorname, String nachname, String strasse, String hausnummer, String plz, String ort) {
        Optional<Kunde> kundeOptional = kundenGateway.getKundeByDetails(vorname, nachname, strasse, hausnummer, plz, ort);
        return kundeOptional.map(kunde -> kundenConverter.toWebDtoWithId(kunde)).orElse(null);
    }
}
