/*    */ package de.biware.mailchecker.api;
/*    */ 
/*    */ import java.util.Objects;
/*    */ 
/*    */ public class MailAccount {
/*    */   private final String name;
/*    */   
/*    */   private final String user;
/*    */   
/*    */   private final String password;
/*    */   
/*    */   private final String host;
/*    */   
/*    */   private final int port;
/*    */   
/*    */   public MailAccount(String name, String user, String password, String host, int port) {
/* 23 */     this.name = name;
/* 24 */     this.user = user;
/* 25 */     this.password = password;
/* 26 */     this.host = host;
/* 27 */     this.port = port;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 31 */     return this.name;
/*    */   }
/*    */   
/*    */   public String getUser() {
/* 35 */     return this.user;
/*    */   }
/*    */   
/*    */   public String getPassword() {
/* 39 */     return this.password;
/*    */   }
/*    */   
/*    */   public String getHost() {
/* 43 */     return this.host;
/*    */   }
/*    */   
/*    */   public int getPort() {
/* 47 */     return this.port;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 52 */     return "MailAccount{name=" + this.name + ", user=" + this.user + ", password=" + this.password + ", host=" + this.host + ", port=" + this.port + '}';
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 57 */     int hash = 7;
/* 58 */     hash = 53 * hash + Objects.hashCode(this.name);
/* 59 */     hash = 53 * hash + Objects.hashCode(this.user);
/* 60 */     hash = 53 * hash + Objects.hashCode(this.password);
/* 61 */     hash = 53 * hash + Objects.hashCode(this.host);
/* 62 */     hash = 53 * hash + this.port;
/* 63 */     return hash;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 68 */     if (this == obj)
/* 69 */       return true; 
/* 71 */     if (obj == null)
/* 72 */       return false; 
/* 74 */     if (getClass() != obj.getClass())
/* 75 */       return false; 
/* 77 */     MailAccount other = (MailAccount)obj;
/* 78 */     if (this.port != other.port)
/* 79 */       return false; 
/* 81 */     if (!Objects.equals(this.name, other.name))
/* 82 */       return false; 
/* 84 */     if (!Objects.equals(this.user, other.user))
/* 85 */       return false; 
/* 87 */     if (!Objects.equals(this.password, other.password))
/* 88 */       return false; 
/* 90 */     if (!Objects.equals(this.host, other.host))
/* 91 */       return false; 
/* 93 */     return true;
/*    */   }
/*    */ }


/* Location:              E:\software\sbg-ide\workspace\mailchecker\target\mailchecker-1.0-SNAPSHOT.jar!\de\biware\mailchecker\api\MailAccount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */