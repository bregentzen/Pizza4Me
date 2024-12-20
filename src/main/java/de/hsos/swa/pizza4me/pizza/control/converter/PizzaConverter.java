package de.hsos.swa.pizza4me.pizza.control.converter;

import de.hsos.swa.pizza4me.pizza.entity.Pizza;
import de.hsos.swa.pizza4me.pizza.gateway.dto.PizzaJpaDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PizzaConverter {

    public Pizza convertToEntity(PizzaJpaDTO pizzaJpaDTO) {
        return new Pizza(pizzaJpaDTO.getId(), pizzaJpaDTO.getName(), pizzaJpaDTO.getPreis());
    }

    public PizzaJpaDTO convertToJpaDTO(Pizza pizza) {
        return new PizzaJpaDTO(pizza.getName(), pizza.getPreis());
    }
}