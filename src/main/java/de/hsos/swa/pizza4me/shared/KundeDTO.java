package de.hsos.swa.pizza4me.shared;

public class KundeDTO {
    private long id;
    private String vorname;
    private String nachname;
    private AdresseDTO adresse;

    public KundeDTO() {
    }

    public KundeDTO(long id, String vorname, String nachname, AdresseDTO adresse) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
    }

    public long getId() {
        return id;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public AdresseDTO getAdresse() {
        return adresse;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setAdresse(AdresseDTO adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "KundeDTO{" +
                "id=" + id +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", adresse=" + adresse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KundeDTO that)) return false;

        if (id != that.id) return false;
        if (!vorname.equals(that.vorname)) return false;
        if (!nachname.equals(that.nachname)) return false;
        return adresse.equals(that.adresse);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + vorname.hashCode();
        result = 31 * result + nachname.hashCode();
        result = 31 * result + adresse.hashCode();
        return result;
    }
}
