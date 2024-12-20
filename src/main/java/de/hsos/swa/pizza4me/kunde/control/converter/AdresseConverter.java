package de.hsos.swa.pizza4me.kunde.control.converter;

import de.hsos.swa.pizza4me.kunde.entity.Adresse;
import de.hsos.swa.pizza4me.shared.dto.AdresseDTO;
import jakarta.enterprise.context.Dependent;

@Dependent
public class AdresseConverter {

    public AdresseDTO toDTO(Adresse adresse) {
        return new AdresseDTO(adresse.getStrasse(), adresse.getHausnummer(), adresse.getPlz(), adresse.getOrt());
    }
    public Adresse toEntity(AdresseDTO adresseDTO) {
        return new Adresse(adresseDTO.getStrasse(), adresseDTO.getHausnummer(), adresseDTO.getPlz(), adresseDTO.getOrt());
    }
}
