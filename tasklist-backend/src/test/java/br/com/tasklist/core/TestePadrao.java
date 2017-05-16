
package br.com.simplesnegocio.core;

import com.google.common.base.Charsets;
import org.apache.commons.io.FileUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public abstract class TestePadrao {

    protected Client client = ClientBuilder.newClient();

    @ArquillianResource
    protected URL url;

    @Deployment
    public static WebArchive getDeploymentDefaultParameters() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsManifestResource(EmptyAsset.INSTANCE, "META-INF/beans.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("tasklist-ds.xml")
                .addAsResource("db/migration")
                .addPackages(true, "br.com.tasklist")
                .addAsLibraries(getFiles());
    }

    private static File[] getFiles() {
        return Maven.configureResolver()
                .loadPomFromFile("pom.xml")
                .importRuntimeAndTestDependencies()
                .resolve()
                .withTransitivity()
                .asFile();
    }

    protected Entity<String> readEntityFromJson(String fileName) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            return Entity.json(FileUtils.readFileToString(file, Charsets.UTF_8.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Response post(String path, String json) {
        return client.target(url.toString())
                .path(path)
                .request()
                .post(readEntityFromJson(json));
    }

    protected Response get(String path) {
        return client.target(url.toString())
                .path(path)
                .request()
                .get();
    }

    protected void statusOk(Response response) {
        Assert.assertEquals(Response.Status.Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }

}

