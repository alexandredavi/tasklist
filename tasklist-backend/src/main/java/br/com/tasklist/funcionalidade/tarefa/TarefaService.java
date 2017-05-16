package br.com.tasklist.funcionalidade.tarefa;

import br.com.tasklist.core.dao.CrudDao;
import br.com.tasklist.core.service.CrudService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TarefaService extends CrudService<Tarefa, Long, TarefaDto, TarefaDto> {

    @Inject
    private TarefaDao dao;

    @Override
    protected CrudDao<Tarefa, Long> getDao() {
        return dao;
    }

    public void marcarTarefaComoConcluida(Long idTarefa) {
        Tarefa tarefa = buscarPorId(idTarefa);
        tarefa.setConcluida(true);
    }

    public void marcarTarefaComoNaoConcluida(Long idTarefa) {
        Tarefa tarefa = buscarPorId(idTarefa);
        tarefa.setConcluida(false);
    }
}
