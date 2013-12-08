package de.tgehring.coins.rest.bean;

import de.tgehring.coins.general.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "coin")
@NamedQuery(name=Coin.FIND_ALL, query="SELECT c FROM Coin c")
public class Coin implements Entity {

    public final static String FIND_ALL = "Coin.findAll";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Country country;

    private double value;

    private int year;

    private char letter;

    private boolean specialCoin;

    private String comment;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public char getLetter() {
        return this.letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public boolean isSpecialCoin() {
        return this.specialCoin;
    }

    public void setSpecialCoin(boolean specialCoin) {
        this.specialCoin = specialCoin;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String findAllQuery() {
        return Coin.FIND_ALL;
    }
}
