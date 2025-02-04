package de.hsos.swa.pizza4me.kunde.gateway.repository;

import de.hsos.swa.pizza4me.kunde.entity.Adresse;
import de.hsos.swa.pizza4me.kunde.entity.Kunde;
import de.hsos.swa.pizza4me.kunde.entity.KundenGateway;
import de.hsos.swa.pizza4me.kunde.gateway.dto.AdresseJpaDTO;
import de.hsos.swa.pizza4me.kunde.gateway.dto.KundeJpaDTO;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class KundenPostgresRepository implements KundenGateway {

    @Inject
    transient EntityManager entityManager;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public List<Kunde> getAllKunden() {
        String queryString = "select k from Kunde k";
        List<KundeJpaDTO> resultList = this.entityManager.createQuery(queryString, KundeJpaDTO.class).getResultList();
        return resultList.stream().map(this::fromDbDTOKunde).collect(Collectors.toList());
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Optional<Kunde> getKundeById(long id) {
        KundeJpaDTO foundKunde = this.entityManager.find(KundeJpaDTO.class, id);
        return foundKunde != null ? Optional.of(fromDbDTOKunde(foundKunde)) : Optional.empty();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Optional<Kunde> createKunde(Kunde kunde) {
        KundeJpaDTO kundeJpaDTO = new KundeJpaDTO();
        kundeJpaDTO.setId(kunde.getId());
        kundeJpaDTO.setVorname(kunde.getVorname());
        kundeJpaDTO.setNachname(kunde.getNachname());
        kundeJpaDTO.setAdresse(toDbDTOAdresse(kunde.getAdresse()));

        try {
            this.entityManager.persist(kundeJpaDTO);
            return Optional.of(fromDbDTOKunde(kundeJpaDTO));
        } catch (PersistenceException e) {
            System.err.println("Kunde konnte nicht erstellt werden: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
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
    @Transactional(Transactional.TxType.REQUIRED)
    public boolean deleteKunde(long id) {
        KundeJpaDTO foundKunde = this.entityManager.find(KundeJpaDTO.class, id);

        if (foundKunde != null) {
            this.entityManager.remove(foundKunde);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Kunde> getKundeByDetails(String vorname, String nachname, String strasse, String hausnummer, String plz, String ort) {
        try {
            KundeJpaDTO kundeJpaDTO = entityManager.createQuery(
                            "SELECT k FROM Kunde k WHERE k.vorname = :vorname AND k.nachname = :nachname AND k.adresse.strasse = :strasse AND k.adresse.hausnummer = :hausnummer AND k.adresse.plz = :plz AND k.adresse.ort = :ort",
                            KundeJpaDTO.class)
                    .setParameter("vorname", vorname)
                    .setParameter("nachname", nachname)
                    .setParameter("strasse", strasse)
                    .setParameter("hausnummer", hausnummer)
                    .setParameter("plz", plz)
                    .setParameter("ort", ort)
                    .getSingleResult();
            return Optional.of(fromDbDTOKunde(kundeJpaDTO));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    private Kunde fromDbDTOKunde(KundeJpaDTO kundeJpaDTO) {
        return new Kunde(kundeJpaDTO.getId(), kundeJpaDTO.getVorname(), kundeJpaDTO.getNachname(), fromDbDTOAdresse(kundeJpaDTO.getAdresse()));
    }

    private Adresse fromDbDTOAdresse(AdresseJpaDTO adresseJpaDTO) {
        return new Adresse(adresseJpaDTO.getStrasse(), adresseJpaDTO.getHausnummer(), adresseJpaDTO.getPlz(), adresseJpaDTO.getOrt());
    }

    private AdresseJpaDTO toDbDTOAdresse(Adresse adresse) {
        return new AdresseJpaDTO(adresse.getStrasse(), adresse.getHausnummer(), adresse.getPlz(), adresse.getOrt());
    }
}