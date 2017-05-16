package br.com.tasklist.funcionalidade.tarefa;

import br.com.tasklist.core.TestePadrao;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;

/**
 * Teste de integração para a funcionalidade de tarefas.
 */

@RunAsClient
@RunWith(Arquillian.class)
public class TarefaEndpointTest extends TestePadrao {

    @Test
    @InSequence(1)
    public void inserirTarefa() {
        Response response = post("/api/tarefa", "json/tarefa/nova-tarefa.json");
        statusOk(response);
    }

    @Test
    @InSequence(2)
    public void buscarPorId() {
        Response response = get("/api/tarefa/1");
        statusOk(response);
    }


    @Test
    @InSequence(3)
    public void editarTarefa() {
        Response responseGet = get("/api/tarefa/1");
        TarefaDto tarefa = responseGet.readEntity(TarefaDto.class);
        tarefa.setTitulo("Tarefa editada");
        Response responsePut = put("/api/tarefa", tarefa);
        statusOk(responsePut);
    }

    @Test
    @InSequence(4)
    public void marcarTarefaComoConcluida() {
        Response response = get("/api/tarefa/concluida/1");
        statusOk(response);
    }

    @Test
    @InSequence(5)
    public void marcarTarefaComoNaoConcluida() {
        Response response = get("/api/tarefa/nao-concluida/1");
        statusOk(response);
    }

    @Test
    @InSequence(6)
    public void removerTarefa() {
        Response response = delete("/api/tarefa/1");
        statusOk(response);
    }
}
