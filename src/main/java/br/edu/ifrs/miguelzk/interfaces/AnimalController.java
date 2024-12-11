package br.edu.ifrs.miguelzk.interfaces;

import br.edu.ifrs.miguelzk.infrastructure.exception.ObjetoNaoEncontradoException;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import br.edu.ifrs.miguelzk.application.dto.AnimalRequestDTO;
import br.edu.ifrs.miguelzk.application.service.AnimalService;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@Path("/api/animal")
public class AnimalController {

    @Inject
    private AnimalService animalService;

    @POST
//  @RolesAllowed("admin, user")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAnimal(@RequestBody AnimalRequestDTO request) {
        return Response.ok().entity(animalService.create(request)).build();
    }

    @PUT
    @Path("/{id}")
//  @RolesAllowed("admin")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAnimal(@PathParam("id") Long id, @RequestBody AnimalRequestDTO request) {
        return Response.ok().entity(animalService.updateAnimal(id, request)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAnimalAll() {
        return Response.ok().entity(animalService.findAnimalAll()).build();
    }

    @GET
    @Path("/buscanome")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAnimalByName(@QueryParam("nomeAnimal") String nomeAnimal) {
        return Response.ok().entity(animalService.findAnimalByName(nomeAnimal)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAnimalById(@PathParam("id") Long id) {
        return Response.ok().entity(animalService.findAnimalById(id)).build();
    }

    @GET
    @Path("/prontuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAnimalComColecoesById(@PathParam("id") Long id) {
        return Response.ok().entity(animalService.findAnimalComColecoesById(id)).build();
    }

    @GET
    @Path("/carteiravacinacao")
    @Produces(MediaType.APPLICATION_JSON)
    public Response carteiraDeVacinacao(@QueryParam("idAnimal") Long idAnimal) {
        try {
            return Response.ok().entity(animalService.carteiraDeVacinacao(idAnimal)).build();
        } catch (ObjetoNaoEncontradoException e) {
            return Response.status(404).build();
        }
    }

    @DELETE
//  @RolesAllowed("admin")
    @PermitAll
    @Path("/{id}")
    public Response deleteAnimalById(@PathParam("id") Long id) {
        try {
            animalService.deleteAnimalById(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
