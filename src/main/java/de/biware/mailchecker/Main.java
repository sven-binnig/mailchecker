/*    */ package de.biware.mailchecker;
/*    */ 
/*    */ import de.biware.mailchecker.api.MailAccount;
/*    */ import de.biware.mailchecker.api.MailCheckerException;
/*    */ import de.biware.mailchecker.impl.SimpleMailCheckService;
/*    */ 
/*    */ public class Main {
/*    */   public static void main(String[] args) throws MailCheckerException {
/* 19 */     MailAccount sbg_account = new MailAccount("sven binnig", "sven.binnig@posteo.de", "Kacs6670", "posteo.de", 993);
/* 21 */     SimpleMailCheckService simpleMailCheckService = new SimpleMailCheckService();
/* 22 */     System.out.println("unread mails count in " + sbg_account.getName() + ": " + simpleMailCheckService.countUnreadMails(sbg_account));
/*    */   }
/*    */ }


/* Location:              E:\software\sbg-ide\workspace\mailchecker\target\mailchecker-1.0-SNAPSHOT.jar!\de\biware\mailchecker\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */