package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.example.daos.DatabaseConnector;
import org.example.daos.RoleDetailDao;
import org.example.services.RoleDetailService;
import org.example.controllers.RoleDetailController;

public class JobApplication extends Application<JobConfiguration> {
    public static void main(final String[] args) throws Exception {
        new JobApplication().run(args);
    }
    @Override
    public String getName() {
        return "Job";
    }

    @Override
    public void initialize(
            final Bootstrap<JobConfiguration> bootstrap) {
                bootstrap.addBundle(
                new SwaggerBundle<JobConfiguration>() {
                    @Override
                    protected SwaggerBundleConfiguration
                    getSwaggerBundleConfiguration(
                            final JobConfiguration configuration) {
                        return configuration.getSwagger();
                }
            });
    }

    @Override
    public void run(final JobConfiguration configuration,
                    final Environment environment) {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        environment.jersey()
                .register(new RoleDetailController(
                        new RoleDetailService(new RoleDetailDao(), da)));
    }
}
