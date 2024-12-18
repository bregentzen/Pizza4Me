package de.hsos.swa.pizza4me.kunde.gateway.dto;

import java.util.Objects;

public class AdresseJpaDTO {
    private Long id;
    private String strasse;
    private String hausnummer;
    private String plz;
    private String ort;

    public AdresseJpaDTO() {
    }

    public AdresseJpaDTO(Long id, String strasse, String hausnummer, String plz, String ort) {
        this.id = id;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
    }

    public Long getId() {
        return this.id;
    }

    public String getStrasse() {
        return this.strasse;
    }

    public String getHausnummer() {
        return this.hausnummer;
    }

    public String getPlz() {
        return this.plz;
    }

    public String getOrt() {
        return this.ort;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Override
    public String toString() {
        return "AdresseJpaDTO{" +
                "id=" + id +
                ", strasse='" + strasse + '\'' +
                ", hausnummer='" + hausnummer + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdresseJpaDTO that)) return false;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(strasse, that.strasse)) return false;
        if (!Objects.equals(hausnummer, that.hausnummer)) return false;
        if (!Objects.equals(plz, that.plz)) return false;
        return Objects.equals(ort, that.ort);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (strasse != null ? strasse.hashCode() : 0);
        result = 31 * result + (hausnummer != null ? hausnummer.hashCode() : 0);
        result = 31 * result + (plz != null ? plz.hashCode() : 0);
        result = 31 * result + (ort != null ? ort.hashCode() : 0);
        return result;
    }
}
