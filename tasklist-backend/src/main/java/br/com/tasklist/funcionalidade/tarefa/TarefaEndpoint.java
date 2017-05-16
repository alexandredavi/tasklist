package br.com.tasklist.funcionalidade.tarefa;

import br.com.tasklist.core.endpoint.CrudEndpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/tarefa")
public class TarefaEndpoint extends CrudEndpoint<Tarefa, Long, TarefaService, TarefaDto, TarefaDto> {

    @GET
    @Path("/concluida/{id}")
    public Response marcarTarefaComoConcluida(@PathParam("id") Long idTarefa) {
        service.get().marcarTarefaComoConcluida(idTarefa);
        return ok();
    }

    @GET
    @Path("/nao-concluida/{id}")
    public Response marcarTarefaComoNaoConcluida(@PathParam("id") Long idTarefa) {
        service.get().marcarTarefaComoNaoConcluida(idTarefa);
        return ok();
    }
}
