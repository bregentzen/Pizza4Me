package de.hsos.swa.pizza4me.bestellung.entity;

import de.hsos.swa.pizza4me.pizza.entity.Pizza;

public class Bestellposten {
    private Pizza pizza;
    private int anzahl;

    public Bestellposten(Pizza pizza, int anzahl) {
        this.pizza = pizza;
        this.anzahl = anzahl;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String toString() {
        return "Bestellposten{" + "pizza=" + pizza + ", anzahl=" + anzahl + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bestellposten that)) return false;

        if (anzahl != that.anzahl) return false;
        return pizza.equals(that.pizza);
    }

    @Override
    public int hashCode() {
        int result = pizza.hashCode();
        result = 31 * result + anzahl;
        return result;
    }
}
