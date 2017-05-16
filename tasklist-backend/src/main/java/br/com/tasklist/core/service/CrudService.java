package br.com.tasklist.core.service;

import br.com.tasklist.core.dao.CrudDao;
import br.com.tasklist.core.model.entity.BaseEntity;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.validation.Validator;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service com metodos genericos para CRUD.
 */
public abstract class CrudService<E extends BaseEntity<ID>, ID extends Serializable, D> {

    @Inject
    protected Validator validator;

    @Inject
    protected ModelMapper mapper;

    private Class<E> classE;
    private Class<D> classDto;

    public E inserir(E entity) {
        getDao().inserir(entity);
        return entity;
    }

    public D inserirDto(D dto) {
        E entity = toEntity(dto);
        return toDto(inserir(entity));
    }

    public E atualizar(E entity) {
        E entityMerged = getDao().atualizar(entity);
        return entityMerged;
    }

    public D atualizarDto(D dto) {
        E entity = toEntity(dto);
        return toDto(atualizar(entity));
    }

    public void remover(ID id) {
        getDao().remover(id);
    }

    public E buscarPorId(ID id) {
        return getDao().buscarPorId(id);
    }

    public D buscarDtoPorId(ID id) {
        return toDto(buscarPorId(id));
    }

    public List<E> buscarTodos() {
        return getDao().buscarTodos();
    }

    public List<D> buscarTodosDtos() {
        return toDto(buscarTodos());
    }

    public E toEntity(D dto) {
        return mapper.map(dto, getClassE());
    }

    public D toDto(E entidade) {
        return mapper.map(entidade, getClassDto());
    }

    public List<D> toDto(List<E> entidades) {
        return entidades.stream()
                .map(e -> mapper.map(e, getClassDto()))
                .collect(Collectors.toList());
    }

    protected abstract CrudDao<E, ID> getDao();

    @SuppressWarnings("unchecked")
    protected Class<E> getClassE() {
        if (classE == null) {
            this.classE = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return classE;
    }

    @SuppressWarnings("unchecked")
    protected Class<D> getClassDto() {
        if (classDto == null) {
            this.classDto = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        }
        return classDto;
    }

}
