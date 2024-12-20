package de.hsos.swa.pizza4me.kunde.control;

import de.hsos.swa.pizza4me.kunde.control.converter.KundenConverter;
import de.hsos.swa.pizza4me.kunde.entity.Kunde;
import de.hsos.swa.pizza4me.kunde.entity.KundenGateway;
import de.hsos.swa.pizza4me.shared.dto.KundeDTO;
import jakarta.inject.Inject;

import java.util.List;

public class KundenController {
    @Inject
    KundenGateway kundenGateway;

    @Inject
    KundenConverter converter;

    public List<KundeDTO> getAllKunden() {
        List<Kunde> kunden = kundenGateway.getAllKunden();
        return converter.toDTOs(kunden);
    }




}
