/*    */ package de.biware.mailchecker.ui;
/*    */ 
/*    */ import de.biware.mailchecker.api.MailAccount;
/*    */ import de.biware.mailchecker.api.MailCheckService;
/*    */ import de.biware.mailchecker.api.MailCheckerException;
/*    */ 
/*    */ public class DefaultMailCheckPresenter implements MailCheckPresenter {
/*    */   private MailCheckView view;
/*    */   
/*    */   private final MailCheckService mailCheckService;
/*    */   
/*    */   public DefaultMailCheckPresenter(MailCheckService mailCheckService) {
/* 23 */     this.mailCheckService = mailCheckService;
/*    */   }
/*    */   
/*    */   public void registerMailCheckView(MailCheckView view) {
/* 28 */     this.view = view;
/*    */   }
/*    */   
/*    */   public void performMailCheck(MailAccount account) {
/*    */     try {
/* 34 */       int unread = this.mailCheckService.countUnreadMails(account);
/* 35 */       this.view.onMailCheckPerformed(account, unread);
/* 36 */     } catch (MailCheckerException ex) {
/* 37 */       this.view.onMailCheckerException(ex);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\software\sbg-ide\workspace\mailchecker\target\mailchecker-1.0-SNAPSHOT.jar!\de\biware\mailchecke\\ui\DefaultMailCheckPresenter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */