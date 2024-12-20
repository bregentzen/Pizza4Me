package de.hsos.swa.pizza4me.kunde.entity;


import java.util.List;
import java.util.Optional;

public interface KundenGateway {
    List<Kunde> getAllKunden();
    Optional<Kunde> getKundeById(long id);
    Optional<Kunde> createKunde(Kunde kunde);
    Optional<Kunde> updateKunde(Kunde kunde);
    boolean deleteKunde(long id);
}
