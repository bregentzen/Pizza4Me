package de.hsos.swa.pizza4me.web;

import de.hsos.swa.pizza4me.bestellung.boundary.dto.BestellpostenWebDTO;
import de.hsos.swa.pizza4me.bestellung.boundary.dto.BestellungWebDTO;
import de.hsos.swa.pizza4me.bestellung.boundary.resource.BestellungResource;
import de.hsos.swa.pizza4me.kunde.boundary.dto.KundeIdWebDTO;
import de.hsos.swa.pizza4me.kunde.boundary.resource.KundenIdResource;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.util.ArrayList;
import java.util.List;

@Path("/checkout")
public class CheckoutResource {

    @Inject
    Template checkout;

    @Inject
    KundenIdResource kundenIdResource;

    @Inject
    BestellungResource bestellungResource;

    @GET
    @Path("/{kundeId}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getCheckout(@PathParam("kundeId") long kundeId, @QueryParam("orderItems") String orderItemsStr) {
        List<BestellpostenWebDTO> orderItems = parseOrderItems(orderItemsStr);
        Response response = kundenIdResource.getKundeById(kundeId);
        KundeIdWebDTO kunde = response.readEntity(KundeIdWebDTO.class);
        return checkout.data("kunde", kunde).data("orderItems", orderItems).data("kundeId", kundeId);
    }

    private List<BestellpostenWebDTO> parseOrderItems(String orderItemsStr) {
        List<BestellpostenWebDTO> orderItems = new ArrayList<>();
        if (orderItemsStr != null && !orderItemsStr.isEmpty()) {
            String[] items = orderItemsStr.split(";");
            for (String item : items) {
                String[] parts = item.split(",");
                long pizzaId = Long.parseLong(parts[0]);
                int quantity = Integer.parseInt(parts[1]);
                orderItems.add(new BestellpostenWebDTO(pizzaId, quantity));
            }
        }
        return orderItems;
    }

    @POST
    @Path("/{kundeId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response placeOrder(@PathParam("kundeId") long kundeId, @FormParam("orderItems") String orderItemsStr) {
        List<BestellpostenWebDTO> orderItems = parseOrderItems(orderItemsStr);
        BestellungWebDTO bestellung = new BestellungWebDTO();
        bestellung.setBestellposten(orderItems);
        Response response = bestellungResource.createBestellung(kundeId, bestellung);
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return Response.seeOther(UriBuilder.fromPath("/orders/{kundeId}").build(kundeId)).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Order could not be placed").build();
        }
    }
}