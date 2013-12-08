package de.tgehring.coins.rest.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import de.tgehring.coins.general.Entity;
import de.tgehring.coins.rest.bean.Coin;
import de.tgehring.coins.rest.dao.GenericDao;

import java.util.LinkedList;
import java.util.List;

@Path("/crud/coin")
public class CoinCrudService {
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Coin getCoin(@PathParam("id") long id) {
        GenericDao<Coin> dao = GenericDao.getInstance();

        return (Coin) dao.read(id);
    }

    @GET
    @Produces("application/json")
    public List<Coin> getCoins() {
        GenericDao<Coin> dao = GenericDao.getInstance();
        String query = new Coin().findAllQuery();
        List<Coin> coins = new LinkedList<Coin>();
        List<Entity> fetchedCoins = dao.read(query);
        for(Entity entity: fetchedCoins) {
            coins.add((Coin) entity);
        }

        return coins;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCoin(JAXBElement<Coin> coin) {
        GenericDao<Coin> dao = GenericDao.getInstance();
        dao.create(coin.getValue());
        String result = "Coin saved : " + coin.getValue();

        return Response.status(201).entity(result).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editAbteilung(JAXBElement<Coin> coin) {
        GenericDao<Coin> dao = GenericDao.getInstance();
        dao.update(coin.getValue());
        String result = "Coin changed : " + coin.getValue();

        return Response.status(201).entity(result).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBenutzer(@PathParam("id") long id) {
        GenericDao<Coin> dao = GenericDao.getInstance();
        dao.delete(id);
        String result = "Coin[ID=" + id + "] deleted";

        return Response.status(201).entity(result).build();
    }
}
