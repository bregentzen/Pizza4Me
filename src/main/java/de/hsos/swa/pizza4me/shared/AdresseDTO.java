package de.hsos.swa.pizza4me.shared;

public class AdresseDTO {
    private String strasse;
    private String hausnummer;
    private String plz;
    private String ort;

    public AdresseDTO() {
    }

    public AdresseDTO(String strasse, String hausnummer, String plz, String ort) {
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
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
        return "AdresseDTO{" +
                "strasse='" + strasse + '\'' +
                ", hausnummer='" + hausnummer + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdresseDTO that)) return false;

        if (!strasse.equals(that.strasse)) return false;
        if (!hausnummer.equals(that.hausnummer)) return false;
        if (!plz.equals(that.plz)) return false;
        return ort.equals(that.ort);
    }

    @Override
    public int hashCode() {
        int result = strasse.hashCode();
        result = 31 * result + hausnummer.hashCode();
        result = 31 * result + plz.hashCode();
        result = 31 * result + ort.hashCode();
        return result;
    }
}
