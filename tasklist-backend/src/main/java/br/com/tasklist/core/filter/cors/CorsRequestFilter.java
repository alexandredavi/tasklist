package br.com.tasklist.core.filter.cors;

import javax.inject.Inject;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
public class CorsRequestFilter implements ContainerRequestFilter {

    @Context
    private UriInfo uriInfo;

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    private CorsCheckOrigin corsCheckOrigin;
    
    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        final String origin = request.getHeaderString(CorsHeaders.ORIGIN.toString());
        if (origin == null) {
            return;
        }

        corsCheckOrigin.checkOrigin(uriInfo, resourceInfo, request, origin);
        
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            configureCors(origin, request);
        }
    }
    
    private void configureCors(String origin, ContainerRequestContext requestContext) throws IOException {
        final Response.ResponseBuilder builder = Response.ok();
        builder.header(CorsHeaders.ACCESS_CONTROL_ALLOW_ORIGIN.toString(), origin);

        String requestMethods = requestContext.getHeaderString(CorsHeaders.ACCESS_CONTROL_REQUEST_METHOD.toString());
        if (requestMethods != null) {
            builder.header(CorsHeaders.ACCESS_CONTROL_ALLOW_METHODS.toString(), requestMethods);
        }

        String allowHeaders = requestContext.getHeaderString(CorsHeaders.ACCESS_CONTROL_REQUEST_HEADERS.toString());
        if (allowHeaders != null) {
            builder.header(CorsHeaders.ACCESS_CONTROL_ALLOW_HEADERS.toString(), allowHeaders);
        }
        
        requestContext.abortWith(builder.build());
    }
}