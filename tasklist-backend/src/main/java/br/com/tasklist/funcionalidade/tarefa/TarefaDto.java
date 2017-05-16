package br.com.tasklist.funcionalidade.tarefa;

import br.com.tasklist.core.converter.json.LocalDateDeserializer;
import br.com.tasklist.core.converter.json.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TarefaDto {

    private Long id;

    @NotNull
    private String titulo;

    private String descricao;

    @NotNull
    private boolean concluida;

    private Long posicao;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataCriacao;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataUltimaEdicao;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataRemocao;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataConclusao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getPosicao() {
        return posicao;
    }

    public void setPosicao(Long posicao) {
        this.posicao = posicao;
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
}
