package com.lucas.pingwise.infrastructure.security;

import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum PublicPaths {

    // API public endpoints (Not counted as a plan usage)
    LOGIN("/api/v1/auth/**", Optional.empty()),
    REGISTER("/api/v1/users", Optional.of(HttpMethod.POST)),
    HEALTH("/actuator/health", Optional.empty()),
    PLANS("/api/v1/plans", Optional.of(HttpMethod.GET)),

    // Swagger / OpenAPI routes
    API_DOCS("/v3/api-docs/**", Optional.empty()),
    SWAGGER_HTML("/swagger-ui.html", Optional.empty()),
    SWAGGER("/swagger-ui/**", Optional.empty());

    @Getter
    private final String path;

    @Getter
    private final HttpMethod httpMethod;

    PublicPaths(String path, Optional<HttpMethod> httpMethod) {
        this.path = path;
        this.httpMethod = httpMethod.orElse(null);
    }

    public static List<PublicPaths> asList() {
        return Arrays.stream(values())
                .toList();
    }

    public static boolean isPublicPath(String requestPath, HttpMethod method) {
        AntPathMatcher matcher = new AntPathMatcher();

        return Arrays.stream(values())
                .anyMatch(publicPath -> {
                    boolean pathMatches = matcher.match(publicPath.getPath(), requestPath);
                    boolean methodMatches = publicPath.getHttpMethod() == null
                            || publicPath.getHttpMethod().equals(method);

                    return pathMatches && methodMatches;
                });
    }
}
