package br.com.tasklist.core.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public abstract class Endpoint {

    public Response ok() {
        return Response.ok().build();
    }

    public Response ok(Object o) {
        return Response.ok(o).build();
    }

    public Response notFound() {
        return Response.status(Status.NOT_FOUND).build();
    }

    public Response created(Object o) {
        return Response.status(Status.CREATED).build();
    }

}
