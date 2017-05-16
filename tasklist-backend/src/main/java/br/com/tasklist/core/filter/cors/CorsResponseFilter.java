package br.com.tasklist.core.filter.cors;

import javax.inject.Inject;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsResponseFilter implements ContainerResponseFilter {

    @Context
    private UriInfo uriInfo;

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    private CorsCheckOrigin corsCheckOrigin;

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) {
        final String origin = request.getHeaderString(CorsHeaders.ORIGIN.toString());
        if (origin == null || HttpMethod.OPTIONS.toString().equals(request.getMethod()) || request.getProperty("cors.failure") != null) {
            return;
        }

        corsCheckOrigin.checkOrigin(uriInfo, resourceInfo, request, origin);

        response.getHeaders().putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_ORIGIN.toString(), origin);
    }

}