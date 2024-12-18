package de.hsos.swa.pizza4me.pizza.boundary.dto;

import java.util.Objects;

public class PizzaWebDTO {
    private String name;
    private double preis;

    public PizzaWebDTO() {
    }

    public PizzaWebDTO(String name, double preis) {
        this.name = name;
        this.preis = preis;
    }

    public String getName() {
        return this.name;
    }


    public double getPreis() {
        return this.preis;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return "PizzaWebDTO{" +
                "name='" + name + '\'' +
                ", preis=" + preis +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PizzaWebDTO that)) return false;

        if (!Objects.equals(name, that.name)) return false;
        return preis == that.preis;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + Double.hashCode(preis);
        return result;
    }

}
