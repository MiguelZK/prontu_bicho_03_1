package br.edu.ifrs.miguelzk.interfaces;

import br.edu.ifrs.miguelzk.application.dto.ImunizanteRequestDTO;
import br.edu.ifrs.miguelzk.application.service.ImunizanteService;
import br.edu.ifrs.miguelzk.infrastructure.exception.ObjetoNaoEncontradoException;
import io.quarkus.vertx.http.runtime.devmode.ResourceNotFoundData;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/api/imunizante")
public class ImunizanteController {

    @Inject
    ResourceNotFoundData resourceNotFoundData;
    @Inject
    private ImunizanteService imunizanteService;

    @POST
//  @RolesAllowed("admin")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@RequestBody ImunizanteRequestDTO request) {
        try {
            return Response.ok().entity(imunizanteService.create(request)).build();
        } catch (ObjetoNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateImunizante(@PathParam("id") Long id, @RequestBody ImunizanteRequestDTO request) {
        return Response.ok().entity(imunizanteService.updateImunizante(id, request)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            return Response.ok().entity(imunizanteService.findImunizanteAll()).build();
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
            return Response.ok().entity(imunizanteService.findImunizanteById(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
//  @RolesAllowed("admin")
    @PermitAll
    public Response deleteImunizanteById(Long id) {
        try {
            imunizanteService.deleteImunizanteById(id);
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
