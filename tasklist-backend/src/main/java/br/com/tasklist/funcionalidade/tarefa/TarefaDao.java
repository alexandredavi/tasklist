package br.com.tasklist.funcionalidade.tarefa;

import br.com.tasklist.core.dao.ExclusaoLogicaCrudDao;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class TarefaDao extends ExclusaoLogicaCrudDao<Tarefa, Long> {

}
