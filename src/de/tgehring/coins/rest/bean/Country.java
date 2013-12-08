package de.tgehring.coins.rest.bean;

import de.tgehring.coins.general.Entity;

import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "country")
@NamedQuery(name=Country.FIND_ALL, query="SELECT c FROM Country c")
public class Country implements Entity {

    public final static String FIND_ALL = "Country.findAll";

    private long id;
    private String name;

    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String findAllQuery() {
        return Country.FIND_ALL;
    }
}
