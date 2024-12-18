package de.hsos.swa.pizza4me.pizza.gateway.dto;

public class PizzaJpaDTO {
    private Long id;
    private String name;
    private double preis;

    public PizzaJpaDTO() {
    }

    public PizzaJpaDTO(Long id, String name, double preis) {
        this.id = id;
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
        return "PizzaJpaDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", preis=" + preis +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PizzaJpaDTO that)) return false;

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
