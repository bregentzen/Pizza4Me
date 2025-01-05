package de.hsos.swa.pizza4me.kunde.boundary.resource;

import de.hsos.swa.pizza4me.kunde.boundary.dto.KundeIdWebDTO;
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
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Response getKundeById(@PathParam("id") long id) {
        KundeIdWebDTO kunde = kundenController.getKundeById(id);

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
    @Transactional(Transactional.TxType.REQUIRES_NEW)
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

    @GET
    @Path("/details")
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Response getKundeByDetails(@QueryParam("vorname") String vorname,
                                      @QueryParam("nachname") String nachname,
                                      @QueryParam("strasse") String strasse,
                                      @QueryParam("hausnummer") String hausnummer,
                                      @QueryParam("plz") String plz,
                                      @QueryParam("ort") String ort) {
        KundeWebDTO kunde = kundenController.getKundeByDetails(vorname, nachname, strasse, hausnummer, plz, ort);

        if (kunde == null) {
            JsonObject message = Json.createObjectBuilder()
                    .add("message", "Kunde nicht gefunden").build();

            JsonObject response = Json.createObjectBuilder()
                    .add("info", message).build();
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }

        return Response.ok(kunde).build();
    }
}
