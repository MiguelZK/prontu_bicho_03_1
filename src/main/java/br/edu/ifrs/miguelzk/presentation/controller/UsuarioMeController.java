/*
package br.edu.ifrs.miguelzk.presentation.controller;

import br.edu.ifrs.miguelzk.application.dto.UsuarioRequestDTO;
import br.edu.ifrs.miguelzk.application.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/api/usuariome")
public class UsuarioMeController {

  @GET
  @RolesAllowed(("user"))
  @Path("/me")
  public String me(@Context SecurityContext securityContext) {
    return securityContext.getUserPrincipal().getName();
  }
}
*/
