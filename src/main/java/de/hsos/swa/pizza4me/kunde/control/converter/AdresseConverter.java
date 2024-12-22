package de.hsos.swa.pizza4me.kunde.control.converter;

import de.hsos.swa.pizza4me.kunde.boundary.dto.AdresseWebDTO;
import de.hsos.swa.pizza4me.kunde.entity.Adresse;
import de.hsos.swa.pizza4me.shared.dto.AdresseDTO;
import jakarta.enterprise.context.Dependent;

@Dependent
public class AdresseConverter {

    public AdresseWebDTO toWebDTO(Adresse adresse) {
        return new AdresseWebDTO(adresse.getStrasse(), adresse.getHausnummer(), adresse.getPlz(), adresse.getOrt());
    }
    public Adresse toEntity(AdresseWebDTO adresseWebDTO) {
        return new Adresse(adresseWebDTO.getStrasse(), adresseWebDTO.getHausnummer(), adresseWebDTO.getPlz(), adresseWebDTO.getOrt());
    }
}
