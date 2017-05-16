import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TarefaDto} from './tarefa.dto';
import {TarefasService} from './tarefas.service';

import 'rxjs/add/operator/map';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public form: FormGroup;
  public tarefa: TarefaDto;
  public tarefas: TarefaDto[] = [];
  public buscando: boolean;

  constructor(formBuilder: FormBuilder,
              private tarefasService: TarefasService) {
    this.tarefa = new TarefaDto();
    this.form = formBuilder.group({
      titulo: [null, [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.buscando = true;
    this.tarefasService.buscarTodos()
      .map((res) => res.json())
      .subscribe(
        (data) => {
          this.tarefas = data;
          this.buscando = false;
        },
        () => this.buscando = false
      );
  }

  public salvar(): void {
    if (!this.tarefa) {
      this.tarefasService.inserir(this.tarefa)
        .map((res) => res.json())
        .subscribe(
          (data) => {
            this.tarefa.id = data;
            this.tarefas.push({...this.tarefa});
            this.tarefa = new TarefaDto();
          }
        );
    } else {
      this.tarefasService.atualizar(this.tarefa).subscribe(
        () => this.tarefa = new TarefaDto()
      );
    }
  }

  public editarTarefa(dto: TarefaDto): void {
    this.tarefa = dto;
  }

  public excluirTarefa(id: number): void {
    this.tarefasService.remover(id)
      .subscribe(
        () => {
          const tarefaRemovida = this.tarefas.find((tarefa) => tarefa.id === id);
          const idx = this.tarefas.indexOf(tarefaRemovida);
          this.tarefas.splice(idx, 1);
        }
      );
  }
}
