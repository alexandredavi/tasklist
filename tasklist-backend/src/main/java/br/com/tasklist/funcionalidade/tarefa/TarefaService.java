package br.com.tasklist.funcionalidade.tarefa;

import br.com.tasklist.core.dao.CrudDao;
import br.com.tasklist.core.service.CrudService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;

@Stateless
public class TarefaService extends CrudService<Tarefa, Long, TarefaDto> {

    @Inject
    private TarefaDao dao;

    @Override
    protected CrudDao<Tarefa, Long> getDao() {
        return dao;
    }

    public void marcarTarefaComoConcluida(Long idTarefa) {
        Tarefa tarefa = buscarPorId(idTarefa);
        tarefa.setConcluida(true);
        tarefa.setDataConclusao(LocalDate.now());
    }

    public void marcarTarefaComoNaoConcluida(Long idTarefa) {
        Tarefa tarefa = buscarPorId(idTarefa);
        tarefa.setConcluida(false);
        tarefa.setDataConclusao(null);
    }

    public void atualizaPosicao(Long id, Long posicao) {
        Tarefa tarefa = buscarPorId(id);
        tarefa.setPosicao(posicao);
    }
}
