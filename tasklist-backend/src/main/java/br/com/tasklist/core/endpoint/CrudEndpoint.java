package br.com.tasklist.core.endpoint;

import br.com.tasklist.core.model.entity.BaseEntity;
import br.com.tasklist.core.service.CrudService;
import org.slf4j.Logger;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.Serializable;

public abstract class CrudEndpoint<E extends BaseEntity<ID>, ID extends Serializable, S extends CrudService<E, ID, D, DL>, D, DL> extends Endpoint {

    @Inject
    protected Instance<S> service;

    @Inject
    private Logger logger;

    @GET
    @Path("/{id}")
    public Response buscaPorId(@PathParam("id") final ID id) {
        D dto = service.get().buscarDtoPorId(id);
        if (dto != null) {
            return ok(dto);
        }
        return notFound();
    }

    @GET
    public Response buscarTodos() {
        return ok(service.get().buscarTodosDtos());
    }

    @POST
    public Response inserir(@Valid D dto) {
        return ok(service.get().inserirDto(dto));
    }

    @PUT
    public Response atualizar(@Valid D dto) {
        service.get().atualizarDto(dto);
        return ok();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") final ID id) {
        service.get().remover(id);
        return ok();
    }

}
