package de.hsos.swa.pizza4me.bestellung.gateway.dto;

import de.hsos.swa.pizza4me.kunde.gateway.dto.KundeJpaDTO;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Entity(name = "Bestellung")
@Schema(name = "BESTELLUNGEN")
@NamedQuery(name = "BestellungJpaDTO.findById",
        query = "SELECT bestellung FROM Bestellung bestellung where bestellung.id=:id")
public class BestellungJpaDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ElementCollection
    private List<BestellpostenJpaDTO> bestellposten;

    @ManyToOne
    private KundeJpaDTO kunde;

    public BestellungJpaDTO() {}

    public BestellungJpaDTO(List<BestellpostenJpaDTO> bestellposten, KundeJpaDTO kunde) {
        this.bestellposten = bestellposten;
        this.kunde = kunde;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<BestellpostenJpaDTO> getBestellposten() {
        return bestellposten;
    }

    public void setBestellposten(List<BestellpostenJpaDTO> bestellposten) {
        this.bestellposten = bestellposten;
    }

    public KundeJpaDTO getKunde() {
        return kunde;
    }

    public void setKunde(KundeJpaDTO kunde) {
        this.kunde = kunde;
    }
}