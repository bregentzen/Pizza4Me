package de.hsos.swa.pizza4me.pizza.control.converter;

import de.hsos.swa.pizza4me.pizza.boundary.dto.PizzaIdWebDTO;
import de.hsos.swa.pizza4me.pizza.boundary.dto.PizzaWebDTO;
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

    public Pizza convertToEntity(PizzaWebDTO pizzaWebDTO) {
        return new Pizza(pizzaWebDTO.getName(), pizzaWebDTO.getPreis());
    }

    public PizzaWebDTO convertToWebDTO(Pizza pizza) {
        PizzaWebDTO pizzaWebDTO = new PizzaWebDTO();
        pizzaWebDTO.setName(pizza.getName());
        pizzaWebDTO.setPreis(pizza.getPreis());
        return pizzaWebDTO;
    }

    public PizzaIdWebDTO convertToIdWebDTO(Pizza pizza) {
        PizzaIdWebDTO pizzaIdWebDTO = new PizzaIdWebDTO();
        pizzaIdWebDTO.setId(pizza.getId());
        pizzaIdWebDTO.setName(pizza.getName());
        pizzaIdWebDTO.setPreis(pizza.getPreis());
        return pizzaIdWebDTO;
    }
}