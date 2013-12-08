package de.tgehring.coins.rest.services;

import de.tgehring.coins.general.Entity;
import de.tgehring.coins.rest.bean.Country;
import de.tgehring.coins.rest.dao.GenericDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import java.util.LinkedList;
import java.util.List;

@Path("/crud/country")
public class CountryCrudService {
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Country getCountry(@PathParam("id") long id) {
        GenericDao<Country> dao = GenericDao.getInstance();

        return (Country) dao.read(id);
    }

    @GET
    @Produces("application/json")
    public List<Country> getCountries() {
        GenericDao<Country> dao = GenericDao.getInstance();
        String query = new Country().findAllQuery();
        List<Country> Countrys = new LinkedList<Country>();
        List<Entity> fetchedCountrys = dao.read(query);
        for(Entity entity: fetchedCountrys) {
            Countrys.add((Country) entity);
        }

        return Countrys;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCountry(JAXBElement<Country> Country) {
        GenericDao<Country> dao = GenericDao.getInstance();
        dao.create(Country.getValue());
        String result = "Country saved : " + Country.getValue();

        return Response.status(201).entity(result).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editAbteilung(JAXBElement<Country> Country) {
        GenericDao<Country> dao = GenericDao.getInstance();
        dao.update(Country.getValue());
        String result = "Country changed : " + Country.getValue();

        return Response.status(201).entity(result).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBenutzer(@PathParam("id") long id) {
        GenericDao<Country> dao = GenericDao.getInstance();
        dao.delete(id);
        String result = "Country[ID=" + id + "] deleted";

        return Response.status(201).entity(result).build();
    }
}
