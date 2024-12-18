package de.hsos.swa.pizza4me.kunde.boundary.dto;

import java.util.Objects;

public class KundeWebDTO {
    private String vorname;
    private String nachname;
    private AdresseWebDTO adresse;

    public KundeWebDTO() {
    }

    public KundeWebDTO(String vorname, String nachname, AdresseWebDTO adresse) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public AdresseWebDTO getAdresse() {
        return adresse;
    }

    public void setAdresse(AdresseWebDTO adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "KundenWebDTO{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", adresse=" + adresse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KundeWebDTO that)) return false;

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
