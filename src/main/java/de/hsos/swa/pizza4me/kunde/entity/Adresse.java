package de.hsos.swa.pizza4me.kunde.entity;

public class Adresse {
    private Long id;
    private String strasse;
    private String hausnummer;
    private String plz;
    private String ort;

    public Adresse() {
    }

    public Adresse(String strasse, String hausnummer, String plz, String ort) {
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                ", strasse='" + strasse + '\'' +
                ", hausnummer='" + hausnummer + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adresse)) return false;

        Adresse that = (Adresse) o;

        if (strasse != null ? !strasse.equals(that.strasse) : that.strasse != null) return false;
        if (hausnummer != null ? !hausnummer.equals(that.hausnummer) : that.hausnummer != null) return false;
        if (plz != null ? !plz.equals(that.plz) : that.plz != null) return false;
        return ort != null ? ort.equals(that.ort) : that.ort == null;
    }

    @Override
    public int hashCode() {
        int result = strasse != null ? strasse.hashCode() : 0;
        result = 31 * result + (hausnummer != null ? hausnummer.hashCode() : 0);
        result = 31 * result + (plz != null ? plz.hashCode() : 0);
        result = 31 * result + (ort != null ? ort.hashCode() : 0);
        return result;
    }
}
