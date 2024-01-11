package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.example.resources.BandLevelController;
import org.example.resources.CapabilityController;
import org.example.resources.JobEntryController;
import java.sql.SQLException;
import org.example.exception.DatabaseConnectionException;
import org.example.resources.AuthController;


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
                environment.jersey().register(new JobEntryController());
                environment.jersey().register(new CapabilityController());
                environment.jersey().register(new BandLevelController());
        try {
            environment.jersey().register(new AuthController());
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        }

    }

}
