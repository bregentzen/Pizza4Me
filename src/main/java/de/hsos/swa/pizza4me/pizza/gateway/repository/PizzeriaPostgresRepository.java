package de.hsos.swa.pizza4me.pizza.gateway.repository;

import de.hsos.swa.pizza4me.pizza.control.converter.PizzaConverter;
import de.hsos.swa.pizza4me.pizza.entity.Pizza;
import de.hsos.swa.pizza4me.pizza.entity.PizzeriaGateway;
import de.hsos.swa.pizza4me.pizza.gateway.dto.PizzaJpaDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class PizzeriaPostgresRepository implements PizzeriaGateway {

    @Inject
    EntityManager entityManager;

    @Inject
    PizzaConverter pizzaConverter;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public List<Pizza> findAllPizzas(int page, int size) {
        TypedQuery<PizzaJpaDTO> query = entityManager.createQuery("SELECT p FROM Pizza p", PizzaJpaDTO.class);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        List<PizzaJpaDTO> pizzaJpaDTOs = query.getResultList();
        return pizzaJpaDTOs.stream().map(pizzaConverter::convertToEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Optional<Pizza> findById(Long id) {
        PizzaJpaDTO pizzaJpaDTO = entityManager.find(PizzaJpaDTO.class, id);
        if (pizzaJpaDTO != null) {
            return Optional.of(pizzaConverter.convertToEntity(pizzaJpaDTO));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public boolean updatePrice(Long id, double newPrice) {
        PizzaJpaDTO pizzaJpaDTO = entityManager.find(PizzaJpaDTO.class, id);
        if (pizzaJpaDTO != null) {
            pizzaJpaDTO.setPreis(newPrice);
            entityManager.merge(pizzaJpaDTO);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public boolean delete(Long id) {
        PizzaJpaDTO pizzaJpaDTO = entityManager.find(PizzaJpaDTO.class, id);
        if (pizzaJpaDTO != null) {
            entityManager.remove(pizzaJpaDTO);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Pizza savePizza(Pizza pizza) {
        PizzaJpaDTO pizzaJpaDTO = pizzaConverter.convertToJpaDTO(pizza);
        entityManager.persist(pizzaJpaDTO);
        entityManager.flush();
        pizza.setId(pizzaJpaDTO.getId());
        return pizza;
    }
}