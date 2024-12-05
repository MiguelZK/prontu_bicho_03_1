package br.edu.ifrs.miguelzk.interfaces;

import br.edu.ifrs.miguelzk.application.dto.VinculoRequestDTO;
import br.edu.ifrs.miguelzk.application.service.VinculoService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/api/vinculo")
public class VinculoController {

  @Inject
  private VinculoService vinculoService;

  @POST
//  @RolesAllowed("admin")
  @PermitAll
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response create(@RequestBody VinculoRequestDTO request) {
    return Response.ok().entity(vinculoService.create(request)).build();
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateVinculo(@PathParam("id") Long id, @RequestBody VinculoRequestDTO request) {
    return Response.ok().entity(vinculoService.updateVinculo(id, request)).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAll() {
    try {
      return Response.ok().entity(vinculoService.findVinculoAll()).build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findById(@PathParam("id") Long id) {
    try {
      return Response.ok().entity(vinculoService.findVinculoById(id)).build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }

  @GET
  @Path("/{nomeVinculo}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findVinculoByName(@PathParam("nomeVinculo") String nomeVinculo) {
    return Response.ok().entity(vinculoService.findVinculoByName(nomeVinculo)).build();
  }

  @DELETE
//  @RolesAllowed("admin")
  @PermitAll
  public Response deleteVinculoById(Long id) {
    try {
      vinculoService.deleteVinculoById(id);
      return Response.ok().build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }
}
