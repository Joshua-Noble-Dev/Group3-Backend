package org.example;


import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.jsonwebtoken.Jwts;
import org.example.controllers.AuthController;
import org.example.controllers.JobRoleController;

import org.example.daos.AuthDao;
import org.example.daos.DatabaseConnector;
import org.example.daos.JobRoleDao;
import org.example.services.AuthService;
import org.example.services.JobRoleService;
import org.example.utils.S3Uploader;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

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

        String s3BucketName = System.getenv("S3_BUCKET");
        String s3Region = System.getenv("S3_REGION");
        environment.jersey()
                .register(new JobRoleController(
                        new JobRoleService(
                                new JobRoleDao(), databaseConnector,
                                new S3Uploader(
                                        AmazonS3ClientBuilder.standard().
                                                withRegion(s3Region)
                                                .build(), s3BucketName))));
        environment.jersey()
                .register(new AuthController(
                        new AuthService(
                                new AuthDao(), jwtKey)));
    }
}
