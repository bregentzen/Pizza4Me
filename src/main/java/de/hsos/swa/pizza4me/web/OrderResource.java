package de.hsos.swa.pizza4me.web;

import de.hsos.swa.pizza4me.bestellung.boundary.dto.BestellungIdWebDTO;
import de.hsos.swa.pizza4me.bestellung.boundary.dto.BestellungWebDTO;
import de.hsos.swa.pizza4me.bestellung.boundary.resource.BestellungResource;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.util.List;

@Path("/orders")
public class OrderResource {

    @Inject
    Template orders;

    @Inject
    BestellungResource bestellungResource;

    @GET
    @Path("/{kundeId}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getOrders(@PathParam("kundeId") long kundeId) {
        Response response = bestellungResource.getAllBestellungen(kundeId);
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            List<BestellungWebDTO> bestellungen = response.readEntity(new GenericType<>() {});
            return orders.data("orders", bestellungen).data("kundeId", kundeId);
        } else {
            return orders.data("orders", List.of()).data("kundeId", kundeId);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createNewOrder(@FormParam("kundeId") long kundeId) {
        return Response.seeOther(UriBuilder.fromPath("/newOrder/{kundeId}").build(kundeId)).build();
    }
}