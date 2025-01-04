package de.hsos.swa.pizza4me.bestellung.boundary.resource;

import de.hsos.swa.pizza4me.bestellung.boundary.dto.BestellungWebDTO;
import de.hsos.swa.pizza4me.bestellung.control.BestellungController;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/kunden/{id}/bestellungen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BestellungResource {

    @Inject
    BestellungController bestellungController;

    @GET
    @Operation(summary = "Get all orders", description = "Fetches all orders for a specific customer")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Orders found"),
            @APIResponse(responseCode = "204", description = "No Content")
    })
    public Response getAllBestellungen(@PathParam("id") long kundeId) {
        List<BestellungWebDTO> bestellungen = bestellungController.getAllBestellungen(kundeId);
        if (bestellungen.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(bestellungen).build();
    }

    @POST
    @Transactional
    @Operation(summary = "Create order", description = "Creates a new order for a specific customer")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Order created"),
            @APIResponse(responseCode = "204", description = "No Content")
    })
    public Response createBestellung(@PathParam("id") long kundeId, BestellungWebDTO bestellungWebDTO) {
        BestellungWebDTO createdBestellung = bestellungController.createBestellung(kundeId, bestellungWebDTO);
        if (createdBestellung == null) {
            return Response.status(Response.Status.NO_CONTENT).entity(
                    "{\"info\": {\"message\": \"(#1007) Aktuell können keine Bestellungen angelegt werden\"}}"
            ).build();
        }
        return Response.status(Response.Status.OK).entity(createdBestellung).build();
    }
}