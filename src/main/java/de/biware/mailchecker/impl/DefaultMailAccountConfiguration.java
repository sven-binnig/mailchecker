/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.biware.mailchecker.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.biware.mailchecker.api.MailAccount;
import de.biware.mailchecker.api.MailAccountConfiguration;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author svenina
 */
public class DefaultMailAccountConfiguration implements MailAccountConfiguration {
    private final Gson gson;
    private final File configFile;

    public DefaultMailAccountConfiguration(File configFile) {
        this.configFile = configFile;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    

    @Override
    public Collection<MailAccount> getKnownMailAccounts() throws IOException {
        MailAccount[] accounts = gson.fromJson(new FileReader(configFile), MailAccount[].class);
        return Arrays.asList(accounts);
    }
    
}
