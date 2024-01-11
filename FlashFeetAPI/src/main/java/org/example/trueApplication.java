package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
<<<<<<< HEAD
import org.example.resources.BandLevelController;
import org.example.resources.CapabilityController;
import org.example.resources.JobEntryController;
=======
import org.example.exception.DatabaseConnectionException;
import org.example.resources.SignUpController;
import org.example.resources.AuthController;
import org.example.resources.JobRoleController;
>>>>>>> Pre-Production

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
                    final Environment environment) {
            environment.jersey().register(new JobEntryController());
            environment.jersey().register(new CapabilityController());
            environment.jersey().register(new BandLevelController());

    }

}
