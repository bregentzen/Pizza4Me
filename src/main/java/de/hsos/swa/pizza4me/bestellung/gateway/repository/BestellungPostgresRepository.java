package de.hsos.swa.pizza4me.bestellung.gateway.repository;

import de.hsos.swa.pizza4me.bestellung.entity.Bestellposten;
import de.hsos.swa.pizza4me.bestellung.entity.Bestellung;
import de.hsos.swa.pizza4me.bestellung.entity.BestellungGateway;
import de.hsos.swa.pizza4me.bestellung.gateway.dto.BestellpostenJpaDTO;
import de.hsos.swa.pizza4me.bestellung.gateway.dto.BestellungJpaDTO;
import de.hsos.swa.pizza4me.kunde.entity.Adresse;
import de.hsos.swa.pizza4me.kunde.entity.Kunde;
import de.hsos.swa.pizza4me.kunde.gateway.dto.AdresseJpaDTO;
import de.hsos.swa.pizza4me.kunde.gateway.dto.KundeJpaDTO;
import de.hsos.swa.pizza4me.pizza.entity.Pizza;
import de.hsos.swa.pizza4me.pizza.gateway.dto.PizzaJpaDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BestellungPostgresRepository implements BestellungGateway {

    @Inject
    EntityManager entityManager;

    @Override
    public Bestellung getBestellungById(long id) {
        BestellungJpaDTO bestellungJpaDTO = entityManager.find(BestellungJpaDTO.class, id);
        return convertToEntity(bestellungJpaDTO);
    }

    @Override
    public Bestellung createBestellung(Bestellung bestellung) {
        BestellungJpaDTO bestellungJpaDTO = convertToDTO(bestellung);
        entityManager.persist(bestellungJpaDTO);
        return convertToEntity(bestellungJpaDTO);
    }

    @Override
    public Bestellung updateBestellung(Kunde kunde, long bestellungId, Bestellposten posten) {
        BestellungJpaDTO bestellungJpaDTO = entityManager.find(BestellungJpaDTO.class, bestellungId);
        if (bestellungJpaDTO != null) {
            bestellungJpaDTO.getBestellposten().add(new BestellpostenJpaDTO(convertToPizzaDTO(posten.getPizza()), posten.getAnzahl()));
            entityManager.merge(bestellungJpaDTO);
        }
        return convertToEntity(bestellungJpaDTO);
    }

    @Override
    public void deleteBestellung(long id) {
        BestellungJpaDTO bestellungJpaDTO = entityManager.find(BestellungJpaDTO.class, id);
        if (bestellungJpaDTO != null) {
            entityManager.remove(bestellungJpaDTO);
        }
    }

    @Override
    public List<Bestellung> getAllBestellungen() {
        TypedQuery<BestellungJpaDTO> query = entityManager.createQuery("SELECT b FROM Bestellung b", BestellungJpaDTO.class);
        return query.getResultList().stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Kunde getKundeById(long kundeId) {
        KundeJpaDTO kundeJpaDTO = entityManager.find(KundeJpaDTO.class, kundeId);
        return convertToKundeEntity(kundeJpaDTO);
    }

    @Override
    public Pizza getPizzaById(long pizza) {
        PizzaJpaDTO pizzaJpaDTO = entityManager.find(PizzaJpaDTO.class, pizza);
        return convertToPizzaEntity(pizzaJpaDTO);
    }

    private Bestellung convertToEntity(BestellungJpaDTO dto) {
        if (dto == null) return null;
        List<Bestellposten> bestellposten = dto.getBestellposten().stream()
                .map(p -> new Bestellposten(convertToPizzaEntity(p.getPizza()), p.getAnzahl()))
                .collect(Collectors.toList());
        return new Bestellung(dto.getId(), bestellposten, convertToKundeEntity(dto.getKunde()));
    }

    private BestellungJpaDTO convertToDTO(Bestellung entity) {
        if (entity == null) return null;
        List<BestellpostenJpaDTO> bestellposten = entity.getBestellposten().stream()
                .map(p -> new BestellpostenJpaDTO(convertToPizzaDTO(p.getPizza()), p.getAnzahl()))
                .collect(Collectors.toList());
        return new BestellungJpaDTO(bestellposten, convertToKundeDTO(entity.getKunde()));
    }

    private Kunde convertToKundeEntity(KundeJpaDTO dto) {
        if (dto == null) return null;
        return new Kunde(dto.getId(), dto.getVorname(), dto.getNachname(), convertToAdresseEntity(dto.getAdresse()));    }

    private KundeJpaDTO convertToKundeDTO(Kunde entity) {
        if (entity == null) return null;
        return new KundeJpaDTO(entity.getId(), entity.getVorname(), entity.getNachname(), convertToAdresseDTO(entity.getAdresse()));    }

    private PizzaJpaDTO convertToPizzaDTO(Pizza entity) {
        if (entity == null) return null;
        return new PizzaJpaDTO(entity.getId(), entity.getName(), entity.getPreis());
    }

    private Pizza convertToPizzaEntity(PizzaJpaDTO dto) {
        if (dto == null) return null;
        return new Pizza(dto.getId(), dto.getName(), dto.getPreis());
    }

    private Adresse convertToAdresseEntity(AdresseJpaDTO dto) {
        if (dto == null) return null;
        return new Adresse(dto.getStrasse(), dto.getHausnummer(), dto.getPlz(), dto.getOrt());
    }

    private AdresseJpaDTO convertToAdresseDTO(Adresse entity) {
        if (entity == null) return null;
        return new AdresseJpaDTO(entity.getStrasse(), entity.getHausnummer(), entity.getPlz(), entity.getOrt());
    }
}