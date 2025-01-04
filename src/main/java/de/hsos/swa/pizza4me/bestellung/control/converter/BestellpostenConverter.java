package de.hsos.swa.pizza4me.bestellung.control.converter;

import de.hsos.swa.pizza4me.bestellung.boundary.dto.BestellpostenWebDTO;
import de.hsos.swa.pizza4me.bestellung.entity.Bestellposten;
import de.hsos.swa.pizza4me.bestellung.entity.BestellungGateway;
import de.hsos.swa.pizza4me.pizza.entity.Pizza;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class BestellpostenConverter {

    @Inject
    BestellungGateway bestellungGateway;

    public Bestellposten toEntity(BestellpostenWebDTO dto) {
        if (dto == null) return null;
        Pizza pizza = bestellungGateway.getPizzaById(dto.getPizza());
        return new Bestellposten(pizza, dto.getAnzahl());
    }

    public BestellpostenWebDTO toWebDTO(Bestellposten entity) {
        if (entity == null) return null;
        long pizzaId = entity.getPizza().getId();
        return new BestellpostenWebDTO(pizzaId, entity.getAnzahl());
    }
}