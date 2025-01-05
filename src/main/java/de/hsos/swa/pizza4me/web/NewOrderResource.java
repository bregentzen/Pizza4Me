package de.hsos.swa.pizza4me.web;

import de.hsos.swa.pizza4me.bestellung.boundary.dto.BestellpostenWebDTO;
import de.hsos.swa.pizza4me.pizza.boundary.dto.PizzaIdWebDTO;
import de.hsos.swa.pizza4me.pizza.boundary.resource.PizzaResource;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.util.ArrayList;
import java.util.List;

@Path("/newOrder")
public class NewOrderResource {

    @Inject
    Template newOrder;

    @Inject
    PizzaResource pizzaResource;

    @GET
    @Path("/{kundeId}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getNewOrder(@PathParam("kundeId") long kundeId) {
        Response response = pizzaResource.getAllPizzas(0, 10);
        List<PizzaIdWebDTO> pizzas = response.readEntity(List.class);
        return newOrder.data("pizzas", pizzas).data("kundeId", kundeId);
    }

    @POST
    @Path("/{kundeId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addItem(@FormParam("kundeId") long kundeId, @FormParam("pizzaId") List<Long> pizzaIds, @FormParam("quantity") List<Integer> quantities) {
        List<BestellpostenWebDTO> orderItems = new ArrayList<>();
        if (pizzaIds != null && quantities != null && pizzaIds.size() == quantities.size()) {
            for (int i = 0; i < pizzaIds.size(); i++) {
                if (quantities.get(i) > 0) {
                    orderItems.add(new BestellpostenWebDTO(pizzaIds.get(i), quantities.get(i)));
                }
            }
        }
        return Response.seeOther(UriBuilder.fromPath("/checkout/{kundeId}").build(kundeId)).build();
    }
}