package de.hsos.swa.pizza4me.pizza.boundary.resource;

import de.hsos.swa.pizza4me.pizza.boundary.dto.PizzaWebDTO;
import de.hsos.swa.pizza4me.pizza.boundary.dto.PizzaIdWebDTO;
import de.hsos.swa.pizza4me.pizza.control.PizzeriaController;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/pizzen")
@Tag(name = "Pizzas", description = "Verwaltung von Pizzas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PizzaIdResource {

    @Inject
    PizzeriaController pizzeriaController;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Pizza by ID", description = "Ruft eine Pizza anhand der ID ab")
    @APIResponse(responseCode = "200", description = "Pizza gefunden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PizzaIdWebDTO.class)))
    @APIResponse(responseCode = "204", description = "Keine Pizza gefunden")
    public Response getPizza(@Parameter(description = "ID der Pizza", required = true) @PathParam("id") Long id) {
        PizzaIdWebDTO pizza = pizzeriaController.findPizzaById(id);
        if (pizza != null) {
            return Response.ok(pizza).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("{\"info\": {\"message\": \"(#1016) Aktuell kann keine Pizza übergeben werden\"}}")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update Pizza Price", description = "Aktualisiert den Preis einer Pizza")
    @APIResponse(responseCode = "200", description = "Preis erfolgreich aktualisiert", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PizzaIdWebDTO.class)))
    @APIResponse(responseCode = "204", description = "Pizza konnte nicht bearbeitet werden")
    public Response updatePizzaPrice(@Parameter(description = "ID der Pizza", required = true) @PathParam("id") Long id, PizzaWebDTO pizza) {
        boolean updated = pizzeriaController.updatePizzaPrice(id, pizza.getPreis());
        if (updated) {
            PizzaIdWebDTO updatedPizza = pizzeriaController.findPizzaById(id);
            return Response.ok(updatedPizza).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("{\"info\": {\"message\": \"(#1017) Aktuell kann keine Pizza bearbeitet werden\"}}")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete Pizza", description = "Löscht eine Pizza anhand der ID")
    @APIResponse(responseCode = "204", description = "Pizza erfolgreich gelöscht")
    @APIResponse(responseCode = "404", description = "Pizza konnte nicht gefunden werden")
    public Response deletePizza(@Parameter(description = "ID der Pizza", required = true) @PathParam("id") Long id) {
        boolean deleted = pizzeriaController.deletePizza(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"info\": {\"message\": \"(#1018) Pizza konnte nicht gelöscht werden\"}}")
                    .build();
        }
    }
}