package br.edu.ifrs.miguelzk.infrastructure.exception;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.hibernate.exception.ConstraintViolationException;

@Provider
@RegisterForReflection
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        System.out.println("ConstraintViolationExceptionMapper CARREGADOOOOOO!");
        String mensagemErro = "Erro de UNICIDADEEEEEE (elemento único já foi encontrado no BD): " + e.getConstraintName();
        return Response.status(Response.Status.BAD_REQUEST) // HTTP 400
                .entity(mensagemErro)
                .build();
    }
}