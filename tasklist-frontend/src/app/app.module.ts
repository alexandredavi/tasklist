import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './funcionalidade/tarefa/tarfeas.component';
import {TarefasService} from './funcionalidade/tarefa/tarefas.service';
import {ListaTarefasComponent} from 'app/components/lista-tarefas/lista-tarefas.component';

/**
 * Modulo principal da aplicação
 */

@NgModule({
  declarations: [
    AppComponent,
    ListaTarefasComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule
  ],
  providers: [
    TarefasService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
