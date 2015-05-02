package com.databrigade;

import java.io.File;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

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
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
					RestConfiguration configuration) {
				return configuration.swaggerBundleConfiguration;
			}
		});
	}

	
	@Override
	public void run(RestConfiguration configuration, Environment environment) {
		final RestResource resource = new RestResource(
				configuration.getTemplate(), configuration.getDefaultName());
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(
				configuration.getTemplate());
		
		final String dbConfig = configuration.getDbConfigFile();
		File dbConfigFile = new File(dbConfig);
		if (!dbConfigFile.exists())
		{
			System.out.println("Could not file db config file!");
			System.exit(-1);
		}
		final CorpusResource corpusResource = new CorpusResource(dbConfigFile);
		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
		environment.jersey().register(corpusResource);
		

	}

}