package br.edu.ifrs.miguelzk.interfaces;

import br.edu.ifrs.miguelzk.application.dto.VinculoRequestDTO;
import br.edu.ifrs.miguelzk.infrastructure.exception.ObjetoNaoEncontradoException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import br.edu.ifrs.miguelzk.application.dto.AtendimentoRequestDTO;
import br.edu.ifrs.miguelzk.application.service.AtendimentoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/admin/atendimento")
public class AtendimentoController {

    @Inject
    private AtendimentoService atendimentoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@RequestBody AtendimentoRequestDTO request) {
        try {
            return Response.ok().entity(atendimentoService.createAtendimento(request)).build();
        } catch (ObjetoNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError().entity("CAUSA: " + e.getCause() + " MENSAGEM: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAtendimentoById(@PathParam("id") Long id) {
        try {
            return Response.ok().entity(atendimentoService.findAtendimentoById(id)).build();
        } catch (ObjetoNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAtendimentoAll() {
        try {
            return Response.ok().entity(atendimentoService.findAtendimentoAll()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAtendimento(@PathParam("id") Long id, @RequestBody AtendimentoRequestDTO request) {
        try {
            return Response.ok().entity(atendimentoService.updateAtendimento(id, request)).build();
        } catch (ObjetoNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
//  @RolesAllowed("admin")
    @Path("/{id}")
    public void deleteAtendimentoById(@PathParam("id") Long id) {
      try {
        atendimentoService.deleteAtendimentoById(id);
      } catch (ObjetoNaoEncontradoException e) {

      }
    }
    }
