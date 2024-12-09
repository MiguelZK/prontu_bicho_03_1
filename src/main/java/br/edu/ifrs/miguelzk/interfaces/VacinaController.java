package br.edu.ifrs.miguelzk.interfaces;

import br.edu.ifrs.miguelzk.application.dto.VacinaRequestDTO;
import br.edu.ifrs.miguelzk.application.service.VacinaService;
import br.edu.ifrs.miguelzk.infrastructure.exception.ObjetoNaoEncontradoException;
import io.quarkus.vertx.http.runtime.devmode.ResourceNotFoundData;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/api/vacina")
public class VacinaController {

    @Inject
    ResourceNotFoundData resourceNotFoundData;
    @Inject
    private VacinaService vacinaService;

    @POST
//  @RolesAllowed("admin")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@RequestBody VacinaRequestDTO request) {
        return Response.ok().entity(vacinaService.create(request)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVacina(@PathParam("id") Long id, @RequestBody VacinaRequestDTO request) {
        return Response.ok().entity(vacinaService.updateVacina(id, request)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            return Response.ok().entity(vacinaService.findVacinaAll()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        try {
            return Response.ok().entity(vacinaService.findVacinaById(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

/*    @GET
    @Path("/carteiravacinacao")
    @Produces(MediaType.APPLICATION_JSON)
    public Response carteiraDeVacinacao(@QueryParam("idAnimal") Long idAnimal) {
        try {
            return Response.ok().entity(vacinaService.carteiraDeVacinacao(idAnimal)).build();
        } catch (ObjetoNaoEncontradoException e) {
            return Response.status(404).build();
        }
    }*/

    @DELETE
//  @RolesAllowed("admin")
    @PermitAll
    public Response deleteVacinaById(Long id) {
        try {
            vacinaService.deleteVacinaById(id);
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
