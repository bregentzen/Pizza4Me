package de.hsos.swa.pizza4me.kunde.gateway.repository;

import de.hsos.swa.pizza4me.kunde.entity.Adresse;
import de.hsos.swa.pizza4me.kunde.entity.Kunde;
import de.hsos.swa.pizza4me.kunde.entity.KundenGateway;
import de.hsos.swa.pizza4me.kunde.gateway.dto.AdresseJpaDTO;
import de.hsos.swa.pizza4me.kunde.gateway.dto.KundeJpaDTO;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class KundenPostgresRepository implements KundenGateway {

    @Inject
    transient EntityManager entityManager;


    @Override
    public List<Kunde> getAllKunden() {
        String queryString = "select k from Kunde k";
        TypedQuery<KundeJpaDTO> query = this.entityManager
                .createQuery(queryString, KundeJpaDTO.class);
        List<KundeJpaDTO> resultList = query.getResultList();
        return resultList.stream()
                .map(this::fromDbDTOKunde)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<Kunde> getKundeById(long id) {

        TypedQuery<KundeJpaDTO> typedQuery =
                this.entityManager.createNamedQuery("KundeJpaDTO.findById", KundeJpaDTO.class);
        typedQuery.setParameter("id", id);
        KundeJpaDTO foundKunde = typedQuery.getSingleResult();

        if(foundKunde==null) {
            return Optional.empty();
        }
        Kunde kunde = fromDbDTOKunde(foundKunde);

        return Optional.of(kunde);
    }

    @Override
    public Optional<Kunde> createKunde(Kunde kunde) {
        KundeJpaDTO kundeJpaDTO = new KundeJpaDTO();
        kundeJpaDTO.setId(kunde.getId());
        kundeJpaDTO.setVorname(kunde.getVorname());
        kundeJpaDTO.setNachname(kunde.getNachname());
        kundeJpaDTO.setAdresse(kundeJpaDTO.getAdresse());

        try {
            this.entityManager.persist(kundeJpaDTO);
            return Optional.of(fromDbDTOKunde(kundeJpaDTO));
        } catch (PersistenceException e) {
            System.err.println("Kunde konnte nicht erstellt werden: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Kunde> updateKunde(Kunde kunde) {

        KundeJpaDTO foundKunde = this.entityManager.find(KundeJpaDTO.class, kunde.getId());

        if (foundKunde != null) {
            foundKunde.setVorname(kunde.getVorname());
            foundKunde.setNachname(kunde.getNachname());
            foundKunde.setAdresse(toDbDTOAdresse(kunde.getAdresse()));

            try {
                this.entityManager.merge(foundKunde);
                return Optional.of(kunde);
            } catch (PersistenceException e) {
                System.err.println("Kunde konnte nicht aktualisiert werden: " + e.getMessage());
            }

        }
        return Optional.empty();
    }

    @Override
    public boolean deleteKunde(long id) {
        KundeJpaDTO foundKunde = this.entityManager.find(KundeJpaDTO.class, id);

        if (foundKunde != null) {
            try {
                this.entityManager.remove(foundKunde);
                return true;
            } catch (PersistenceException | IllegalArgumentException e) {
                System.err.println("Kunde konnte nicht geloescht werden: " + e.getMessage());
            }
        }

        return false;
    }

    private Kunde fromDbDTOKunde(KundeJpaDTO kundeJpaDTO) {
        return new Kunde(kundeJpaDTO.getId(), kundeJpaDTO.getVorname(), kundeJpaDTO.getNachname(), fromDbDTOAdresse(kundeJpaDTO.getAdresse()));
    }

    private Adresse fromDbDTOAdresse(AdresseJpaDTO adresseJpaDTO) {
        return new Adresse(adresseJpaDTO.getStrasse(), adresseJpaDTO.getHausnummer(), adresseJpaDTO.getPlz(), adresseJpaDTO.getOrt());
    }

    //TODO: hier so gewollt? vielleicht aendern
    private AdresseJpaDTO toDbDTOAdresse(Adresse adresse) {
        return new AdresseJpaDTO(adresse.getStrasse(), adresse.getHausnummer(), adresse.getPlz(), adresse.getOrt());
    }

}
