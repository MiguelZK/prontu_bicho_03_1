package br.edu.ifrs.miguelzk.interfaces;

import br.edu.ifrs.miguelzk.infrastructure.persistence.DataLoader;
import br.edu.ifrs.miguelzk.infrastructure.persistence.DataLoaderVinculos;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/popbdteste")
public class PopulaBdTesteResource {

    @Inject
    DataLoader dataLoader;

    @Inject
    DataLoaderVinculos dataLoaderVinculos;

    @GET
    @Path("/teste1")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String popBDLoader() {
        dataLoader.loadData();
        return "Teste realizado, banco de dados populado";
    }

    @GET
    @Path("/teste2")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String popBDLoaderVinculos() {
        dataLoaderVinculos.loadData();
        return "Teste realizado, banco de dados populado com vínculos estabelecidos";
    }
}
