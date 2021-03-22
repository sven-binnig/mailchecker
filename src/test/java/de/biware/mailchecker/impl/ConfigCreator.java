/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.biware.mailchecker.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.biware.mailchecker.api.MailAccount;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author svenina
 */
public class ConfigCreator {
    private static final MailAccount[] M_ACCOUNTS = {
        new MailAccount("Corina Dörnenburg (posteo)", "corina.doernenburg@posteo.de", "Kacs6670", "posteo.de", 993),
        new MailAccount("Sven Binnig (posteo)", "sven.binnig@posteo.de", "Kacs6670", "posteo.de", 993),
        new MailAccount("Corina Dörnenburg (ekiba)", "corina.doernenburg@kbz.ekiba.de", "C10d_08oe!", "outlook.office.de", 993)
    };
    
    public static void main(String... args) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(System.getProperty("user.dir") + "/mailaccounts.json")) {
            gson.toJson(M_ACCOUNTS, writer);
        }
        
    }
}
