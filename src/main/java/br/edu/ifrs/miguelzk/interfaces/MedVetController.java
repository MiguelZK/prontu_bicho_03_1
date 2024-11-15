package br.edu.ifrs.miguelzk.interfaces;

import br.edu.ifrs.miguelzk.application.dto.MedVetRequestDTO;
import br.edu.ifrs.miguelzk.application.service.MedVetService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/api/medVet")
public class MedVetController {

  @Inject
  MedVetService medVetService;

  @POST
//  @RolesAllowed("admin")
  @PermitAll
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response create(@RequestBody MedVetRequestDTO request) {
    return Response.ok().entity(medVetService.create(request)).build();
  }

  @PUT
  @Path("/{crmv}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateMedVet(@PathParam("crmv") Long crmv, @RequestBody MedVetRequestDTO request) {
    return Response.ok().entity(medVetService.updateMedVet(crmv, request)).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAll() {
      return Response.ok().entity(medVetService.findMedVetAll()).build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findById(@PathParam("id") Long id) {
    try {
      return Response.ok().entity(medVetService.findMedVetById(id)).build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }

  @GET
  @Path("/crmv/{crmv}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findByCrmv(@PathParam("crmv") Long crmv) {
    try {
      return Response.ok().entity(medVetService.findMedVetByCrmv(crmv)).build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }

  @GET
  @Path("/buscanome")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findMedVetByName(@QueryParam("nomeMedVet") String nomeMedVet) {
    try {
      return Response.ok().entity(medVetService.findMedVetByNome(nomeMedVet)).build();
    } catch (NotFoundException e) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }

  @GET
  @Path("/buscausermedvet")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findMedVetByUserName(@QueryParam("nomeMedVet") String userName) {
    try {
      return Response.ok().entity(medVetService.findMedVetByUserName(userName)).build();
    } catch (NotFoundException e) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }

  @DELETE
  @Path("/{crmv}")
//  @RolesAllowed("admin")
  @PermitAll
  public Response deleteMedVetById(@PathParam("crmv") Long crmv) {
    try {
      medVetService.deleteMedVetByCrmv(crmv);
      return Response.ok().build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }
}
