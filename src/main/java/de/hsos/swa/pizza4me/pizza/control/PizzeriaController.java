package de.hsos.swa.pizza4me.pizza.control;

import de.hsos.swa.pizza4me.pizza.boundary.dto.PizzaWebDTO;
import de.hsos.swa.pizza4me.pizza.boundary.dto.PizzaIdWebDTO;
import de.hsos.swa.pizza4me.pizza.control.converter.PizzaConverter;
import de.hsos.swa.pizza4me.pizza.entity.Pizza;
import de.hsos.swa.pizza4me.pizza.entity.PizzeriaGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PizzeriaController {

    @Inject
    PizzeriaGateway pizzeriaGateway; // Verwendung des PizzeriaGateway Interface

    @Inject
    PizzaConverter pizzaConverter; // Verwendung des PizzaConverter für die Umwandlung

    public List<PizzaWebDTO> getAllPizzas(int page, int size) {
        List<Pizza> pizzas = pizzeriaGateway.findAllPizzas(page, size);
        return pizzas.stream().map(this::convertToWebDTO).collect(Collectors.toList());
    }

    public PizzaIdWebDTO addPizza(PizzaWebDTO pizzaWebDTO) {
        Pizza pizza = convertToEntity(pizzaWebDTO);
        Pizza savedPizza = pizzeriaGateway.savePizza(pizza);
        return convertToIdWebDTO(savedPizza);
    }

    private Pizza convertToEntity(PizzaWebDTO pizzaWebDTO) {
        return new Pizza(pizzaWebDTO.getName(), pizzaWebDTO.getPreis());
    }

    private PizzaWebDTO convertToWebDTO(Pizza pizza) {
        PizzaWebDTO pizzaWebDTO = new PizzaWebDTO();
        pizzaWebDTO.setName(pizza.getName());
        pizzaWebDTO.setPreis(pizza.getPreis());
        return pizzaWebDTO;
    }

    private PizzaIdWebDTO convertToIdWebDTO(Pizza pizza) {
        PizzaIdWebDTO pizzaIdWebDTO = new PizzaIdWebDTO();
        pizzaIdWebDTO.setId(pizza.getId());
        pizzaIdWebDTO.setName(pizza.getName());
        pizzaIdWebDTO.setPreis(pizza.getPreis());
        return pizzaIdWebDTO;
    }
}