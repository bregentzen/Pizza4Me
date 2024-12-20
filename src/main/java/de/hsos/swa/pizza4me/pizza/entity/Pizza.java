package de.hsos.swa.pizza4me.pizza.entity;

public class Pizza {
    private Long id;
    private String name;
    private double preis;

    public Pizza() {
    }

    public Pizza(Long id, String name, double preis) {
        this.id = id;
        this.name = name;
        this.preis = preis;
    }

    public Pizza(String name, double preis) {
        this.name = name;
        this.preis = preis;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getPreis() {
        return this.preis;
    }

    public void setId(Long id) {
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
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", preis=" + preis +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza that)) return false;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return preis == that.preis;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + Double.hashCode(preis);
        return result;
    }
}
