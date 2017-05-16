package br.com.tasklist.core.dao;

import br.com.tasklist.core.model.entity.BaseEntity;
import br.com.tasklist.core.model.entity.ExclusaoLogica;

import java.io.Serializable;

public abstract class ExclusaoLogicaCrudDao<E extends BaseEntity<ID> & ExclusaoLogica, ID extends Serializable> extends CrudDao<E, ID> {

    @Override
    public void remover(final ID id) {
        E entity = buscarPorId(id);
        if (entity != null) {
            entity.setExcluido(true);
        }
    }

}
