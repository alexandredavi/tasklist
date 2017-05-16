# tasklist

Tasklist utilizando Java/MySQL e Angular.

## Requisitos

Você deve ter instalado: Java, Maven, Wildfly, Mysql, Node e npm

## Como executar o Backend

Adicionar ao servidor de aplicação o seguinte dataSource. Não esquecendo de colocar o user-name e o password.

```
<datasource jndi-name="java:jboss/datasources/taskListDS" pool-name="taskListDS" enabled="true" use-java-context="true">
  <connection-url>jdbc:mysql://localhost/task-list?useTimezone=true&amp;serverTimezone=UTC</connection-url>
  <driver>mysql</driver>
  <pool>
   <min-pool-size>2</min-pool-size>
   <max-pool-size>5</max-pool-size>
  </pool>
  <security>
   <user-name>[username]</user-name>
   <password>[password]</password>
  </security>
 </datasource>
```

Gerar o .war com o Maven através do comando:

```
mvn package
```

Pegar o ROOT.war gerado na pasta /target e realizar o deploy no servidor de aplicação.

Caso queira executar os testes de integração com Arquillian, é necessário rodar:

```
mvn clean install
```

## Como executar o Frontend

Já possuindo NodeJS e o npm instalado, execute o seguinte comando para instalar as dependencias:

```
npm install -d
```

Depois de já possuir as dependencias instaladas, é só rodar o comando:

```
npm run server
```

Caso queira buildar com AOT (Ahead Of Time Compilation), é necessário rodar:

```
npm run build:aot && npm run server:prod
```

De também uma olhada no arquivo package.json na sessão de scripts para encontrar os comandos para testes, clean e outras build.

## License
MIT
