package br.com.tasklist.core.filter.cors;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.UriInfo;
import java.lang.reflect.Method;

@ApplicationScoped
public class CorsCheckOrigin {

    @Resource(name="accessControlAllowOriginDefault")
    private String accessControlAllowOriginDefault;

    public void checkOrigin(UriInfo uriInfo, ResourceInfo resourceInfo, ContainerRequestContext request, String origin) {
        String accessControlAllowOrigin = accessControlAllowOriginDefault;

        Method method = resourceInfo.getResourceMethod();
        if (method != null && method.isAnnotationPresent(CorsAccessControlAllowOrigin.class)) {
            accessControlAllowOrigin = method.getAnnotation(CorsAccessControlAllowOrigin.class).origin();
        }

        if (!accessControlAllowOrigin.contains("*") && !accessControlAllowOrigin.contains(origin)) {
            request.setProperty("cors.failure", true);

            throw new ForbiddenException("Origin not allowed: " + origin);
        }
    }
}