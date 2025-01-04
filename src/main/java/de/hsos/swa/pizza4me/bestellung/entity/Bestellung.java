package de.hsos.swa.pizza4me.bestellung.entity;

import de.hsos.swa.pizza4me.kunde.entity.Kunde;

import java.util.List;

public class Bestellung {
    private long id;
    private List<Bestellposten> bestellposten;
    private Kunde kunde;

    public Bestellung(long id, List<Bestellposten> bestellposten, Kunde kunde) {
        this.id = id;
        this.bestellposten = bestellposten;
        this.kunde = kunde;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Bestellposten> getBestellposten() {
        return bestellposten;
    }

    public void setBestellposten(List<Bestellposten> bestellposten) {
        this.bestellposten = bestellposten;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    @Override
    public String toString() {
        return "Bestellung{" +
                "id=" + id +
                ", bestellposten=" + bestellposten +
                ", kunde=" + kunde +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bestellung that)) return false;

        if (id != that.id) return false;
        if (bestellposten != null ? !bestellposten.equals(that.bestellposten) : that.bestellposten != null) return false;
        return kunde != null ? kunde.equals(that.kunde) : that.kunde == null;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (bestellposten != null ? bestellposten.hashCode() : 0);
        result = 31 * result + (kunde != null ? kunde.hashCode() : 0);
        return result;
    }
}
