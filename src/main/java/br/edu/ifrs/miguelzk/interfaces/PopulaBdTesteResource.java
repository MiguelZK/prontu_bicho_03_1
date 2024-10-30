package br.edu.ifrs.miguelzk.interfaces;

import br.edu.ifrs.miguelzk.infrastructure.persistence.DataLoader;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/popbdteste")
public class PopulaBdTesteResource {

    @Inject
    DataLoader dataLoader;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String popBDLoader() {
        dataLoader.loadData();
        return "Teste realizado, banco de dados populado";
    }
}
