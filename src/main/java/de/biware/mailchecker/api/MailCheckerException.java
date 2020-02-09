/*    */ package de.biware.mailchecker.api;
/*    */ 
/*    */ public class MailCheckerException extends Exception {
/*    */   public MailCheckerException() {}
/*    */   
/*    */   public MailCheckerException(String string) {
/* 18 */     super(string);
/*    */   }
/*    */   
/*    */   public MailCheckerException(String string, Throwable thrwbl) {
/* 22 */     super(string, thrwbl);
/*    */   }
/*    */   
/*    */   public MailCheckerException(Throwable thrwbl) {
/* 26 */     super(thrwbl);
/*    */   }
/*    */   
/*    */   public MailCheckerException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
/* 30 */     super(string, thrwbl, bln, bln1);
/*    */   }
/*    */ }


/* Location:              E:\software\sbg-ide\workspace\mailchecker\target\mailchecker-1.0-SNAPSHOT.jar!\de\biware\mailchecker\api\MailCheckerException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */