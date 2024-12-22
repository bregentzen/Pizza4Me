package de.hsos.swa.pizza4me.kunde.boundary.resource;

import de.hsos.swa.pizza4me.kunde.boundary.dto.KundeWebDTO;
import de.hsos.swa.pizza4me.kunde.control.KundenController;

import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/kunden/{id}")
@Transactional(Transactional.TxType.REQUIRES_NEW)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class KundenIdResource {

    @Inject
    KundenController kundenController;

    @GET
    public Response getKundeById(@PathParam("id") int id) {
        KundeWebDTO kunde = kundenController.getKundeById(id);

        if (kunde == null) {
            JsonObject message = Json.createObjectBuilder()
                    .add("message", "(#1001) Aktuell können keine Kunden übergeben werden").build();

            JsonObject response = Json.createObjectBuilder()
                    .add("info", message).build();
            return Response.status(Response.Status.NO_CONTENT).entity(response).build();
        }

        return Response.ok(kunde).build();
    }

    @DELETE
    public Response deleteKundeById(@PathParam("id") int id) {
        boolean isDeleted = kundenController.deleteKundeById(id);

        if (isDeleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            JsonObject message = Json.createObjectBuilder()
                    .add("message", "(#1001) Kunde konnte nicht gelöscht werden").build();

            JsonObject response = Json.createObjectBuilder()
                    .add("info", message).build();
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();

        }
    }
}
