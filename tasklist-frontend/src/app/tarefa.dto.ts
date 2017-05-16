export class TarefaDto {
  id: number;
  titulo: string;
  descricao: string;
  concluida: boolean = false;
  dataCriacao: string;
  dataUltimaEdicao: string;
  dataRemocao: string;
  dataConclusao: string;
}
