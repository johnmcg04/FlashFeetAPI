package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.example.exception.DatabaseConnectionException;
<<<<<<< HEAD
import org.example.resources.SignUpController;
=======
import org.example.resources.AuthController;
>>>>>>> Pre-Production
import org.example.resources.JobRoleController;

import java.sql.SQLException;

public class trueApplication extends Application<trueConfiguration> {

    public static void main(final String[] args) throws Exception {
        new trueApplication().run(args);
    }

    @Override
    public String getName() {
        return "true";
    }

    @Override
    public void initialize(final Bootstrap<trueConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<trueConfiguration>(){
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(trueConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(final trueConfiguration configuration,
                    final Environment environment) throws SQLException {
        environment.jersey().register(new JobRoleController());
        try {
            environment.jersey().register(new SignUpController());
            environment.jersey().register(new AuthController());
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        }

    }

}
