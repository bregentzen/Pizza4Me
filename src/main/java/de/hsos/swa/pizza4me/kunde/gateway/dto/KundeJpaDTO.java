package de.hsos.swa.pizza4me.kunde.gateway.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Objects;

@Entity(name = "Kunde")
@Schema(name = "KUNDEN")
@NamedQuery(name = "KundeJpaDTO.findById",
            query = "SELECT kunde FROM Kunde kunde where kunde.id=:id")
public class KundeJpaDTO {
    private Long id;
    private String vorname;
    private String nachname;
    private AdresseJpaDTO adresse;

    public KundeJpaDTO() {
    }

    public KundeJpaDTO(Long id, String vorname, String nachname, AdresseJpaDTO adresse) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
    }

    public Long getId() {
        return this.id;
    }

    public String getVorname() {
        return this.vorname;
    }

    public String getNachname() {
        return this.nachname;
    }

    public AdresseJpaDTO getAdresse() {
        return this.adresse;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setAdresse(AdresseJpaDTO adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "KundeJpaDTO{" +
                "id=" + id +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", adresse=" + adresse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KundeJpaDTO that)) return false;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(vorname, that.vorname)) return false;
        if (!Objects.equals(nachname, that.nachname)) return false;
        return Objects.equals(adresse, that.adresse);
    }

    @Override
    public int hashCode() {
        int result = vorname != null ? vorname.hashCode() : 0;
        result = 31 * result + (nachname != null ? nachname.hashCode() : 0);
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        return result;
    }
}
