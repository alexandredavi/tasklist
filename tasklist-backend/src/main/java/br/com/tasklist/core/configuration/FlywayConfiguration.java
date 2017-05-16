package br.com.tasklist.core.configuration;

import org.flywaydb.core.Flyway;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.sql.DataSource;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
@ConcurrencyManagement (ConcurrencyManagementType.CONTAINER)
public class FlywayConfiguration {

    @Resource(lookup = "java:jboss/datasources/taskListDS")
    private DataSource dataSource;

    @PostConstruct
    protected void onStartup() {
        if (dataSource == null) {
            throw new EJBException("no datasource found to execute the db migrations!");
        }

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
    }

}