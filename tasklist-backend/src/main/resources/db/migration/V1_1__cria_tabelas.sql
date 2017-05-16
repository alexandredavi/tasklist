CREATE TABLE tarefa
(
  id INT NOT NULL AUTO_INCREMENT,
  fg_excluido BOOLEAN NOT NULL,
  fg_concluida BOOLEAN NOT NULL,
  nm_titulo CHARACTER VARYING(255) NOT NULL,
  ds_tarefa CHARACTER VARYING(255),
  dt_criacao DATE,
  dt_ultima_edicao DATE,
  dt_remocao DATE,
  dt_conclusao DATE,
  PRIMARY KEY (id)
);