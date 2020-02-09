/*    */ package de.biware.mailchecker.impl;
/*    */ 
/*    */ import de.biware.mailchecker.api.MailAccount;
/*    */ import de.biware.mailchecker.api.MailCheckService;
/*    */ import de.biware.mailchecker.api.MailCheckerException;
/*    */ import java.util.Properties;
/*    */ import javax.mail.Authenticator;
/*    */ import javax.mail.Folder;
/*    */ import javax.mail.MessagingException;
/*    */ import javax.mail.PasswordAuthentication;
/*    */ import javax.mail.Session;
/*    */ import javax.mail.Store;
/*    */ 
/*    */ public class SimpleMailCheckService implements MailCheckService {
/*    */   public int countUnreadMails(final MailAccount forAccount) throws MailCheckerException {
/* 28 */     Properties props = new Properties();
/* 29 */     props.put("mail.imap.host", forAccount.getHost());
/* 30 */     props.put("mail.imap.user", forAccount.getUser());
/* 31 */     props.put("mail.imap.socketFactory", Integer.valueOf(993));
/* 32 */     props.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
/* 33 */     props.put("mail.imap.port", Integer.valueOf(forAccount.getPort()));
/* 34 */     Session session = Session.getInstance(props, new Authenticator() {
/*    */           protected PasswordAuthentication getPasswordAuthentication() {
/* 37 */             SimpleMailCheckService.this.log("authenticate as " + forAccount.getName());
/* 38 */             return new PasswordAuthentication(forAccount.getUser(), forAccount.getPassword());
/*    */           }
/*    */         });
/*    */     try {
/* 42 */       Store store = session.getStore("imap");
/* 43 */       store.connect();
/* 44 */       Folder fldr = store.getFolder("Inbox");
/* 45 */       fldr.open(1);
/* 47 */       int count = fldr.getUnreadMessageCount();
/* 48 */       log(forAccount.getUser() + " has " + count + " unread messages");
/* 49 */       store.close();
/* 50 */       return count;
/* 51 */     } catch (MessagingException exc) {
/* 52 */       throw new MailCheckerException(exc);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void log(String message) {
/* 58 */     System.out.println("[" + getClass().getSimpleName() + "] " + message);
/*    */   }
/*    */ }


/* Location:              E:\software\sbg-ide\workspace\mailchecker\target\mailchecker-1.0-SNAPSHOT.jar!\de\biware\mailchecker\impl\SimpleMailCheckService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */