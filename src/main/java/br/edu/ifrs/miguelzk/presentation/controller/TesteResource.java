package br.edu.ifrs.miguelzk.presentation.controller;

import br.edu.ifrs.miguelzk.application.DataLoader;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/teste")
public class TesteResource {

    @Inject
    DataLoader dataLoader;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String testarLoader() {
        dataLoader.loadData();
        return "Teste realizado";
    }
}
