import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {TarefaDto} from './tarefa.dto';

/**
 * Service para requisições http e regras relacionado as tarefas
 */

@Injectable()
export class TarefasService {

  endpoint: string = 'http://localhost:8080/api/tarefas';

  constructor(private http: Http) {

  }

  public inserir(dto: TarefaDto): Observable<any> {
    return this.http.post(this.endpoint, dto);
  }

  public atualizar(dto: TarefaDto): Observable<any> {
    return this.http.put(this.endpoint, dto);
  }

  public buscarTodos(): Observable<any> {
    return this.http.get(this.endpoint);
  }

  public remover(id: number): Observable<any> {
    return this.http.delete(this.endpoint + '/' + id);
  }

  public marcarTarefaComoConcluida(id: number): Observable<any> {
    return this.http.get(this.endpoint + '/concluida/' + id);
  }

  public marcarTarefaComoNaoConcluida(id: number): Observable<any> {
    return this.http.get(this.endpoint + '/nao-concluida/' + id);
  }

  public atualizaPosicao(id: number, posicao: number): Observable<any> {
    return this.http.get(this.endpoint + '/atualiza-posicao/' + id + '/posicao/' + posicao);
  }

  public ordenarTarfasPorPosicao(tarefas: TarefaDto[]): TarefaDto[] {
    return tarefas.sort((tarefa1, tarefa2) => tarefa2.posicao - tarefa1.posicao);
  }

}
