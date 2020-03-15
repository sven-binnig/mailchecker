/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.biware.mailchecker.api;

/**
 *
 * @author svenina
 */
public class MailShortInfo {
    private final String absender;
    private final String betreff;

    public MailShortInfo(String absender, String betreff) {
        this.absender = absender;
        this.betreff = betreff;
    }

    public String getAbsender() {
        return absender;
    }

    public String getBetreff() {
        return betreff;
    }
    
    
}
