package de.biware.mailchecker.impl;

import de.biware.mailchecker.api.MailAccount;
import de.biware.mailchecker.api.MailCheckService;
import de.biware.mailchecker.api.MailCheckerException;
import de.biware.mailchecker.api.MailShortInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

public class SimpleMailCheckService implements MailCheckService {

    private Folder openInbox(final MailAccount forAccount) throws MailCheckerException {
        Properties props = new Properties();
        props.put("mail.imap.host", forAccount.getHost());
        props.put("mail.imap.user", forAccount.getUser());
        //props.put("mail.imap.socketFactory.port", Integer.valueOf(993));
        props.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.imap.port", Integer.valueOf(forAccount.getPort()));
        props.setProperty("mail.imap.socketFactory.fallback", "false");
        props.setProperty("mail.imap.socketFactory.port", "993");
        props.put("mail.imap.ssl.protocols", "TLSv1.2");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                SimpleMailCheckService.this.log("authenticate as " + forAccount.getName());
                return new PasswordAuthentication(forAccount.getUser(), forAccount.getPassword());
            }
        });
        try {
            log("locate imap store");
            Store store = session.getStore("imap");
            log("connect to imap store");
            store.connect();
            log("locate inbox folder");
            Folder fldr = store.getFolder("Inbox");
            log("open inbox folder");
            fldr.open(1);
            return fldr;
        } catch (MessagingException exc) {
            throw new MailCheckerException(exc);
        }
    }

    @Override
    public int countUnreadMails(final MailAccount forAccount) throws MailCheckerException {
        try {
            Folder fldr = this.openInbox(forAccount);
            int count = fldr.getUnreadMessageCount();
            log(forAccount.getUser() + " has " + count + " unread messages");
            //store.close();
            fldr.getStore().close();
            return count;
        } catch (MessagingException exc) {
            throw new MailCheckerException(exc);
        }
    }

    private void log(String message) {
        System.out.println("[" + getClass().getSimpleName() + "] " + message);
    }

    @Override
    public List<MailShortInfo> getMailShortInfo(MailAccount paramMailAccount) throws MailCheckerException {
        List<MailShortInfo> result = new ArrayList<>();
        try {
            Folder fldr = this.openInbox(paramMailAccount);
            // search for all "unseen" messages
            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, true);
            List<Message> messages = Arrays.asList(fldr.search(unseenFlagTerm));
            messages.forEach(message -> {
                try {
                    result.add(new MailShortInfo(message.getFrom()[0].toString(), message.getSubject()));
                } catch (MessagingException ex) {
                    //
                }
            });

            fldr.getStore().close();
            return result;
        } catch (MessagingException exc) {
            throw new MailCheckerException(exc);
        }
    }
}
