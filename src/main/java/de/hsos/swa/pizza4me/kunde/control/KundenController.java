package de.hsos.swa.pizza4me.kunde.control;

import de.hsos.swa.pizza4me.kunde.boundary.dto.KundeIdWebDTO;
import de.hsos.swa.pizza4me.kunde.boundary.dto.KundeWebDTO;
import de.hsos.swa.pizza4me.kunde.control.converter.KundenConverter;
import de.hsos.swa.pizza4me.kunde.entity.Kunde;
import de.hsos.swa.pizza4me.kunde.entity.KundenGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class KundenController {
    @Inject
    KundenGateway kundenGateway;

    @Inject
    KundenConverter converter;

    public List<KundeIdWebDTO> getAllKunden() {
        List<Kunde> kunden = kundenGateway.getAllKunden();
        return converter.toWebDTOs(kunden);
    }

    public KundeWebDTO getKundeById(long id) {
        Kunde kunde = kundenGateway.getKundeById(id).isPresent() ? kundenGateway.getKundeById(id).get() : null;

        if (kunde != null) {
            return converter.toWebDtoWithId(kunde);
        }
        return null;
    }

    public boolean deleteKundeById(long id) {
        return kundenGateway.deleteKunde(id);
    }

    public KundeWebDTO updateKunde(KundeIdWebDTO kundeIdWebDTO) {
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
}
