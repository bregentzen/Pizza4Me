package de.hsos.swa.pizza4me.pizza.entity;

import java.util.List;

public interface PizzeriaGateway {

    List<Pizza> findAllPizzas(int page, int size);

    Pizza savePizza(Pizza pizza);
}