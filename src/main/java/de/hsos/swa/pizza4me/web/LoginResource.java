package de.hsos.swa.pizza4me.web;

import de.hsos.swa.pizza4me.kunde.boundary.dto.AdresseWebDTO;
import de.hsos.swa.pizza4me.kunde.boundary.dto.KundeIdWebDTO;
import de.hsos.swa.pizza4me.kunde.boundary.dto.KundeWebDTO;
import de.hsos.swa.pizza4me.kunde.boundary.resource.KundenIdResource;
import de.hsos.swa.pizza4me.kunde.boundary.resource.KundenResource;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

@Path("/login")
public class LoginResource {

    @Inject
    Template login;

    @Inject
    KundenIdResource kundenIdResource;

    @Inject
    KundenResource kundenResource;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getLogin() {
        return login.instance();
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    public Response login(KundeWebDTO tmpKunde) {
        String vorname = tmpKunde.getVorname();
        String nachname = tmpKunde.getNachname();
        String strasse = tmpKunde.getAdresse().getStrasse();
        String hausnummer = tmpKunde.getAdresse().getHausnummer();
        String plz = tmpKunde.getAdresse().getPlz();
        String ort = tmpKunde.getAdresse().getOrt();

        // Check if the customer already exists
        Response response = kundenIdResource.getKundeByDetails( vorname, nachname, strasse, hausnummer, plz, ort);
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            KundeIdWebDTO existingKunde = response.readEntity(KundeIdWebDTO.class);
            return Response.seeOther(UriBuilder.fromPath("/orders/{id}").build(existingKunde.getId())).build();
        } else {
            // Create a new customer
            AdresseWebDTO adresseWebDTO = new AdresseWebDTO(strasse, hausnummer, plz, ort);
            KundeWebDTO kundeWebDTO = new KundeWebDTO(vorname, nachname, adresseWebDTO);
            Response createResponse = kundenResource.createKunde(kundeWebDTO);
            KundeIdWebDTO newKunde = createResponse.readEntity(KundeIdWebDTO.class);
            return Response.seeOther(UriBuilder.fromPath("/orders/{id}").build(newKunde.getId())).build();
        }
    }
}