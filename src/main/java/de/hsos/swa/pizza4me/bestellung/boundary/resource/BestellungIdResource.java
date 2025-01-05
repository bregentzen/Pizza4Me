package de.hsos.swa.pizza4me.bestellung.boundary.resource;

import de.hsos.swa.pizza4me.bestellung.boundary.dto.BestellpostenWebDTO;
import de.hsos.swa.pizza4me.bestellung.boundary.dto.BestellungIdWebDTO;
import de.hsos.swa.pizza4me.bestellung.control.BestellungController;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/kunden/{kundeId}/bestellungen/{bestellungId}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BestellungIdResource {

    @Inject
    BestellungController bestellungController;

    @GET
    @Operation(summary = "Get order by ID", description = "Fetches the order details by order ID")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Order found"),
            @APIResponse(responseCode = "204", description = "No Content"),
            @APIResponse(responseCode = "404", description = "Order not found")
    })
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Response getBestellungById(@PathParam("kundeId") long kundeId, @PathParam("bestellungId") long bestellungId) {
        BestellungIdWebDTO bestellung = bestellungController.getBestellungById(kundeId, bestellungId);
        if (bestellung == null) {
            return Response.status(Response.Status.NO_CONTENT).entity(
                    "{\"info\": {\"message\": \"(#1008) Aktuell kann keine Bestellung übergeben werden\"}}"
            ).build();
        }
        return Response.ok(bestellung).build();
    }

    @PUT
    @Operation(summary = "Add item to order", description = "Adds an item to the specified order")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Item added to order"),
            @APIResponse(responseCode = "204", description = "No Content"),
            @APIResponse(responseCode = "404", description = "Order not found")
    })
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Response addBestellposten(@PathParam("kundeId") long kundeId, @PathParam("bestellungId") long bestellungId, BestellpostenWebDTO bestellpostenWebDTO) {
        BestellungIdWebDTO updatedBestellung = bestellungController.addBestellposten(kundeId, bestellungId, bestellpostenWebDTO);
        if (updatedBestellung == null) {
            return Response.status(Response.Status.NO_CONTENT).entity(
                    "{\"info\": {\"message\": \"(#1009) Aktuell kann kein Bestellposten hinzugefügt werden\"}}"
            ).build();
        }
        return Response.ok(updatedBestellung).build();
    }

    @DELETE
    @Operation(summary = "Delete order", description = "Deletes the specified order")
    @APIResponses({
            @APIResponse(responseCode = "204", description = "Order deleted"),
            @APIResponse(responseCode = "404", description = "Order not found")
    })
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Response deleteBestellung(@PathParam("kundeId") long kundeId, @PathParam("bestellungId") long bestellungId) {
        boolean deleted = bestellungController.deleteBestellung(kundeId, bestellungId);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).entity(
                    "{\"info\": {\"message\": \"(#1010) Bestellung konnte nicht gelöscht werden\"}}"
            ).build();
        }
        return Response.noContent().build();
    }
}