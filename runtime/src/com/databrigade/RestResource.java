package com.databrigade;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Api("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class RestResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public RestResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @ApiOperation("Query Param example")
    @Timed
    public Saying sayHello(@QueryParam("name") String name) {
        final String value = String.format(template, name);
        return new Saying(counter.incrementAndGet(), value);
    }
    
    @GET
    @Timed
    @ApiOperation("Path Param example")
    @Path("/{name}")
    public Saying sayHelloAgain(@PathParam("name") String name) {
        final String value = String.format(template, name);
        return new Saying(counter.incrementAndGet(), value);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @ApiOperation(
        value = "Create Saying Object",
        notes = "Form Example",
        response = Saying.class
    )    
    @Path("/form")
    public Saying sayHelloForm( 
    		@FormParam("id") @ApiParam(defaultValue = "0") int id,
            @FormParam("content") @ApiParam(defaultValue = "Stranger") String content) {
        return new Saying(id, content);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Increment Saying object",
            notes = "POST Example.",
            response = Saying.class
    )
    @Path("/post")

    public Saying sayHelloPost(Saying saying) {
    	long id = saying.getId() + 1;
        return new Saying(id, saying.getContent());
    }
}

//10000 rows, 2000 cols
//Run cosine clustering multiple times in parallel
