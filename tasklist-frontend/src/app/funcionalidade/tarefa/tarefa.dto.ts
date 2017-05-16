export class TarefaDto {
  id: number;
  titulo: string;
  descricao: string;
  concluida: boolean = false;
  posicao: number = 0;
  dataCriacao: string;
  dataUltimaEdicao: string;
  dataRemocao: string;
  dataConclusao: string;
}
