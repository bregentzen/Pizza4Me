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
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/pizzen")
@Tag(name = "Pizzas", description = "Verwaltung von Pizzas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PizzaResource {

    @Inject
    PizzeriaController pizzeriaController; // Verwendung des PizzeriaController als Service-Klasse

    @GET
    @Operation(summary = "Get all pizzas", description = "Returns a list of all available pizzas with paging")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PizzaIdWebDTO.class))),
            @APIResponse(responseCode = "204", description = "No Content")
    })
    public Response getAllPizzas(@QueryParam("page") @DefaultValue("0") int page,
                                 @QueryParam("size") @DefaultValue("10") int size) {
        List<PizzaIdWebDTO> pizzas = pizzeriaController.getAllPizzas(page, size);
        if (pizzas == null || pizzas.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("{\"info\": {\"message\": \"(#1014) Aktuell können keine Pizzen übergeben werden\"}}")
                    .build();
        }
        return Response.ok(pizzas).build();
    }

    @POST
    @Operation(summary = "Add a new pizza", description = "Adds a new pizza to the list")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Pizza created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PizzaIdWebDTO.class))),
            @APIResponse(responseCode = "204", description = "No Content")
    })
    public Response addPizza(PizzaWebDTO pizzaWebDTO) {
        PizzaIdWebDTO newPizza = pizzeriaController.addPizza(pizzaWebDTO);
        if (newPizza == null) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("{\"info\": {\"message\": \"(#1015) Aktuell können keine Pizzen angelegt werden\"}}")
                    .build();
        }
        System.out.println("New Pizza: " + newPizza);
        return Response.ok(newPizza).build();
    }
}