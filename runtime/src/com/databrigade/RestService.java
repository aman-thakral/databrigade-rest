package com.databrigade;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import com.databrigade.RestResource;
import com.databrigade.TemplateHealthCheck;

public class RestService extends Application<RestConfiguration> {
	public static void main(String[] args) throws Exception {
		new RestService().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	public void initialize(Bootstrap<RestConfiguration> bootstrap) {
	    bootstrap.addBundle(new SwaggerBundle<RestConfiguration>() {
	        @Override
	        protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(RestConfiguration configuration) {
	            return configuration.swaggerBundleConfiguration;
	        }
	    });
	}

	@Override
	public void run(RestConfiguration configuration, Environment environment) {
		final RestResource resource = new RestResource(
				configuration.getTemplate(), 
				configuration.getDefaultName()
				);
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(
				configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
	}

}