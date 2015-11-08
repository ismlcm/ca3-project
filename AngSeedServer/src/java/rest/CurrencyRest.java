/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import entity.Currency;
import static entity.Currency_.code;
import facades.CurrencyFacade;
import java.text.DecimalFormat;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Ismail Cam
 */
@Path( "currency" )
@RolesAllowed( "User" )
public class CurrencyRest
{

    Gson gson;
    static CurrencyFacade cf = new CurrencyFacade();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CurrencyRest
     */
    public CurrencyRest()
    {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy( FieldNamingPolicy.IDENTITY ).create();
    }

    /**
     * Retrieves representation of an instance of rest.CurrencyRest
     *
     * @param code
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces( "application/json" )
    @Path( "{code}" )
    public String getCurrency( @PathParam( "code" ) String code )
    {
        JsonObject obj = new JsonObject();
        obj.addProperty( "code", cf.getCurrency( code ).getCode() );
        obj.addProperty( "descr", cf.getCurrency( code ).getDescr() );
        obj.addProperty( "price", cf.getCurrency( code ).getAmount() );

        return gson.toJson( obj );
    }

    @GET
    @Produces( "application/json" )
    @Path( "all" )
    public String getCurrencies()
    {
        List<Currency> list = cf.getAll("6-10-2015");
      
        return gson.toJson( list );
    }
    
    @GET
    @Produces("application/json")
    @Path("cal/{amount}/{curFrom}/{curTo}")
    public String calculate(@PathParam("amount") String amount, @PathParam("curFrom") String curFrom, @PathParam("curTo") String curTo)
    {
        return cf.calCur( amount, curFrom, curTo );
    }

    /**
     * PUT method for updating or creating an instance of CurrencyRest
     *
     * @param content representation for the resource
     *
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes( "application/json" )
    public void putJson( String content )
    {
    }
}
