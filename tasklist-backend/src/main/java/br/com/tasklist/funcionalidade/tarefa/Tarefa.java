package br.com.tasklist.funcionalidade.tarefa;

import br.com.tasklist.core.model.entity.BaseEntity;
import br.com.tasklist.core.model.entity.ExclusaoLogica;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tarefa")
public class Tarefa extends BaseEntity<Long> implements ExclusaoLogica {

    @Column(name = "nm_titulo")
    private String titulo;

    @Column(name = "ds_tarefa")
    private String descricao;

    @Column(name = "fg_concluida")
    private boolean concluida;

    @Column(name = "nu_posicao")
    private Long posicao;

    @Column(name = "dt_criacao")
    private LocalDate dataCriacao;

    @Column(name = "dt_ultima_edicao")
    private LocalDate dataUltimaEdicao;

    @Column(name = "dt_remocao")
    private LocalDate dataRemocao;

    @Column(name = "dt_conclusao")
    private LocalDate dataConclusao;

    @Column(name = "fg_excluido")
    protected boolean excluido;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataUltimaEdicao() {
        return dataUltimaEdicao;
    }

    public void setDataUltimaEdicao(LocalDate dataUltimaEdicao) {
        this.dataUltimaEdicao = dataUltimaEdicao;
    }

    public LocalDate getDataRemocao() {
        return dataRemocao;
    }

    public void setDataRemocao(LocalDate dataRemocao) {
        this.dataRemocao = dataRemocao;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Long getPosicao() {
        return posicao;
    }

    public void setPosicao(Long posicao) {
        this.posicao = posicao;
    }

    @Override
    public void setExcluido(boolean exclusao) {
        this.dataRemocao = LocalDate.now();
        this.excluido = exclusao;
    }

    @Override
    public boolean isExcluido() {
        return this.excluido;
    }

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDate.now();
        this.posicao = 0L;
    }

    @PreUpdate
    public void preUpdate() {
        this.dataUltimaEdicao = LocalDate.now();
    }
}
