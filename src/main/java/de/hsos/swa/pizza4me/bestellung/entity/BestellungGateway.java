package de.hsos.swa.pizza4me.bestellung.entity;

import de.hsos.swa.pizza4me.kunde.entity.Kunde;
import de.hsos.swa.pizza4me.pizza.entity.Pizza;

import java.util.List;

public interface BestellungGateway {
     Bestellung getBestellungById(long id);
     Bestellung createBestellung(Bestellung bestellung);
     Bestellung updateBestellung(Kunde kunde, long BestellungId, Bestellposten posten);
     void deleteBestellung(long id);
     List<Bestellung> getAllBestellungen();

     Kunde getKundeById(long kundeId);

     Pizza getPizzaById(long pizza);
}
