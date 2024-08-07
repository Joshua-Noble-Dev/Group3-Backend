package org.example;


import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.jsonwebtoken.Jwts;
import org.example.auth.JwtAuthenticator;
import org.example.auth.RoleAuthorisor;
import org.example.controllers.AuthController;
import org.example.controllers.JobRoleController;

import org.example.daos.AuthDao;
import org.example.daos.DatabaseConnector;
import org.example.daos.JobRoleDao;
import org.example.models.JwtToken;
import org.example.services.AuthService;
import org.example.services.JobRoleService;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import java.security.Key;



public class TestApplication extends Application<TestConfiguration> {
    public static void main(final String[] args) throws Exception {
        new TestApplication().run(args);
    }
    @Override
    public String getName() {
        return "Test";
    }
    @Override
    public void initialize(final Bootstrap<TestConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                    final TestConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }
    @Override
    public void run(final TestConfiguration configuration,
                    final Environment environment) {
        Key jwtKey = Jwts.SIG.HS256.key().build();
        DatabaseConnector databaseConnector = new DatabaseConnector();
        environment.jersey()
                .register(new JobRoleController(
                             new JobRoleService(
                                new JobRoleDao(), databaseConnector)));
        environment.jersey()
                .register(new AuthController(
                  new AuthService(
                    new AuthDao(), jwtKey)));
        environment.jersey()
                .register(new AuthDynamicFeature(
                        new OAuthCredentialAuthFilter.Builder<JwtToken>()
                                .setAuthenticator(new JwtAuthenticator(jwtKey))
                                .setAuthorizer(new RoleAuthorisor())
                                .setPrefix("Bearer")
                                .buildAuthFilter()
                ));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(
                new AuthValueFactoryProvider.Binder<>(JwtToken.class));
    }

}
