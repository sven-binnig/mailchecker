/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.biware.mailchecker.api;

import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author svenina
 */
public interface MailAccountConfiguration {
    Collection<MailAccount> getKnownMailAccounts() throws IOException;
}
