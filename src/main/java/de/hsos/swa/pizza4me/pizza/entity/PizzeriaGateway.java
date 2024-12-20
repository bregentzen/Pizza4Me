package de.hsos.swa.pizza4me.pizza.entity;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface PizzeriaGateway {

    List<Pizza> findAllPizzas(int page, int size);

    Optional<Pizza> findById(Long id);

    @Transactional
    boolean updatePrice(Long id, double newPrice);

    @Transactional
    boolean delete(Long id);

    @Transactional
    Pizza savePizza(Pizza pizza);
}