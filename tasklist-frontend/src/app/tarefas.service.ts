import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {TarefaDto} from './tarefa.dto';

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

}
