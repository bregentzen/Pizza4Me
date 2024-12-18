package de.hsos.swa.pizza4me.kunde.entity;

import java.util.Objects;

public class Kunde {
    private Long id;
    private String vorname;
    private String nachname;
    private Adresse adresse;

    public Kunde() {
    }

    public Kunde(Long id, String vorname, String nachname, Adresse adresse) {
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

    public Adresse getAdresse() {
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

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "id=" + id +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", adresse=" + adresse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kunde that)) return false;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(vorname, that.vorname)) return false;
        if (!Objects.equals(nachname, that.nachname)) return false;
        return Objects.equals(adresse, that.adresse);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (vorname != null ? vorname.hashCode() : 0);
        result = 31 * result + (nachname != null ? nachname.hashCode() : 0);
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        return result;
    }
}
