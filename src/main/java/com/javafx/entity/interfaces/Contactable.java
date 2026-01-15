package com.javafx.entity.interfaces;
/**
 * Definira osnovne kontaktne podatke osobe
 */
public interface Contactable {
    /**
     * Vraća e-mail adresu kontakta.
     *
     * @return e-mail adresa
     */
    String getEmail();
    /**
     * Vraća telefonski broj kontakta.
     *
     * @return telefonski broj
     */
    String getPhoneNumber();
}