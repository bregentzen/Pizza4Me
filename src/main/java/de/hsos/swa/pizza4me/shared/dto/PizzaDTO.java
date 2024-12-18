package de.hsos.swa.pizza4me.shared.dto;

public class PizzaDTO {
    private long id;
    private String name;
    private double preis;

    public PizzaDTO() {
    }

    public PizzaDTO(long id, String name, double preis) {
        this.id = id;
        this.name = name;
        this.preis = preis;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPreis() {
        return preis;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return "PizzaDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", preis=" + preis +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PizzaDTO that)) return false;

        if (id != that.id) return false;
        if (Double.compare(that.preis, preis) != 0) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + name.hashCode();
        result = 31 * result + Double.hashCode(preis);
        return result;
    }
}
