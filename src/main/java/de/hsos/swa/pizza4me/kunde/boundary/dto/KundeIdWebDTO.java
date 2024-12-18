package de.hsos.swa.pizza4me.kunde.boundary.dto;

import java.util.Objects;

public class KundeIdWebDTO extends KundeWebDTO {
    private Long id;

    public KundeIdWebDTO() {
    }

    public KundeIdWebDTO(Long id, String vorname, String nachname, AdresseWebDTO adresse) {
        super(vorname, nachname, adresse);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "KundenIdWebDTO{" +
                "id=" + id +
                ", vorname='" + getVorname() + '\'' +
                ", nachname='" + getNachname() + '\'' +
                ", adresse=" + getAdresse() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KundeIdWebDTO that)) return false;

        if (!Objects.equals(id, that.id)) return false;
        if (getVorname() != null ? !getVorname().equals(that.getVorname()) : that.getVorname() != null) return false;
        if (getNachname() != null ? !getNachname().equals(that.getNachname()) : that.getNachname() != null) return false;
        return getAdresse() != null ? getAdresse().equals(that.getAdresse()) : that.getAdresse() == null;
    }

    @Override
    public int hashCode() {
        int result = getVorname() != null ? getVorname().hashCode() : 0;
        result = 31 * result + (getNachname() != null ? getNachname().hashCode() : 0);
        result = 31 * result + (getAdresse() != null ? getAdresse().hashCode() : 0);
        return result;
    }
}
