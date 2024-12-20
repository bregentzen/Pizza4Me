package de.hsos.swa.pizza4me.kunde.boundary.resource;

import de.hsos.swa.pizza4me.kunde.control.KundenController;
import de.hsos.swa.pizza4me.shared.dto.KundeDTO;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jose4j.json.internal.json_simple.JSONObject;

import java.util.List;

@Path("/kunden")
@Transactional(Transactional.TxType.REQUIRES_NEW)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class KundenResource {

    @Inject
    KundenController kundenController;

    @GET
    @Operation(summary = "get all customers", description = "get all customers as a list")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200", description = "Successfully get all customers",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = KundeDTO.class))
                    ),
                    @APIResponse(
                            responseCode = "204", description = "No customers exist",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON)
                    )
            }
    )
    public Response getAllKunden() {
        List<KundeDTO> kunden = kundenController.getAllKunden();

        if (kunden.isEmpty()) {
            JsonObject message = Json.createObjectBuilder()
                    .add("message", "(#1001) Aktuell können keine Kunden übergeben werden").build();

            JsonObject response = Json.createObjectBuilder()
                    .add("info", message).build();
            return Response.status(Response.Status.NO_CONTENT).entity(response).build();
        }

        return Response.ok(kunden).build();
    }
}
