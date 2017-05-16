import {Component, EventEmitter, Input, Output} from '@angular/core';
import {TarefaDto} from '../../tarefa.dto';
import {TarefasService} from '../../tarefas.service';
@Component({
  selector: 'lista-tarefas',
  templateUrl: 'lista-tarefas.component.html'
})
export class ListaTarefasComponent {

  @Input() public titulo: string;
  @Input() public tarefas: TarefaDto[] = [];
  @Input() public concluidas: boolean;

  @Output() public editar: EventEmitter<any> = new EventEmitter();

  constructor(private tarefasService: TarefasService) {

  }

  public editarTarefa(dto: TarefaDto): void {
    this.editar.next(dto);
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

  public alterarStatusTarefa(dto: TarefaDto): void {
    if (dto.concluida) {
      this.tarefasService.marcarTarefaComoConcluida(dto.id).subscribe();
    } else {
      this.tarefasService.marcarTarefaComoNaoConcluida(dto.id).subscribe();
    }
  }

  public upPosicao(dto: TarefaDto): void {
    dto.posicao++;
    this.tarefasService.atualizaPosicao(dto.id, dto.posicao).subscribe(
      () => this.tarefasService.ordenarTarfasPorPosicao(this.tarefas),
      () => {
        dto.posicao--;
        this.tarefasService.ordenarTarfasPorPosicao(this.tarefas);
      }
    );
  }

  public downPosicao(dto: TarefaDto): void {
    dto.posicao--;
    if (dto.posicao < 0) {
      dto.posicao++;
      return;
    }
    this.tarefasService.atualizaPosicao(dto.id, dto.posicao).subscribe(
      () => this.tarefasService.ordenarTarfasPorPosicao(this.tarefas),
      () => {
        dto.posicao++;
        this.tarefasService.ordenarTarfasPorPosicao(this.tarefas);
      }
    );
  }

}
