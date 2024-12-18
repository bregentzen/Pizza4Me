package de.hsos.swa.pizza4me.pizza.boundary.dto;

import java.util.Objects;

public class PizzaIdWebDTO extends PizzaWebDTO {
    private Long id;

    public PizzaIdWebDTO() {
    }

    public PizzaIdWebDTO(Long id, String name, double preis) {
        super(name, preis);
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PizzaIdWebDTO{" +
                "id=" + id +
                ", name='" + getName() + '\'' +
                ", preis=" + getPreis() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PizzaIdWebDTO that)) return false;

        if (!Objects.equals(id, that.id)) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getPreis() == that.getPreis();
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + Double.hashCode(getPreis());
        return result;
    }
}
