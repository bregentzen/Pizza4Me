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

    public List<PizzaIdWebDTO> getAllPizzas(int page, int size) {
        List<Pizza> pizzas = pizzeriaGateway.findAllPizzas(page, size);
        return pizzas.stream().map(pizzaConverter::convertToIdWebDTO).collect(Collectors.toList());
    }

    public PizzaIdWebDTO addPizza(PizzaWebDTO pizzaWebDTO) {
        Pizza pizza = pizzaConverter.convertToEntity(pizzaWebDTO);
        System.out.println("Pizza: " + pizza);
        Pizza savedPizza = pizzeriaGateway.savePizza(pizza);
        System.out.println("Saved Pizza: " + savedPizza);
        return pizzaConverter.convertToIdWebDTO(savedPizza);
    }

    public PizzaIdWebDTO findPizzaById(Long id) {
        return pizzeriaGateway.findById(id)
                .map(pizzaConverter::convertToIdWebDTO)
                .orElse(null);
    }

    public boolean updatePizzaPrice(Long id, double newPrice) {
        return pizzeriaGateway.updatePrice(id, newPrice);
    }

    public boolean deletePizza(Long id) {
        return pizzeriaGateway.delete(id);
    }
}