package com.databrigade;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Path("/corpus")
@Api("/corpus")
@Produces(MediaType.APPLICATION_JSON)
public class CorpusResource {

	private final File configFile;

	public static Set<String> createBlurb(String blurb) {
		String[] blurbWords = blurb.split("\\W+");
		Set<String> blurbSet = new HashSet<String>(blurbWords.length);
		for (String blurbWord : blurbWords) {
			blurbSet.add(blurbWord);
		}
		return blurbSet;
	}

	public static String printScores(Map<String, Double> scores) {
		StringBuilder sb = new StringBuilder("");

		for (String key : scores.keySet()) {
			sb.append(key + ": " + -scores.get(key));
		}
		return sb.toString();
	}

	public CorpusResource(File dbConfigFile) {
		this.configFile = dbConfigFile;
	}

	@GET
	@ApiOperation("Predict an author given a query string.")
	@Timed
	public Response predict(@QueryParam("predict") String query) {
		// CorpusManager cm = new CorpusManager(this.configFile);
		// Set<String> blurbSet = createBlurb(query);
		// Map<String, Double> scoresNorm = cm.predictCorpus(blurbSet, true,
		// true);
		// printScores(scoresNorm)
		return new Response("ok", query);
	}
}