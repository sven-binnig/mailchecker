/*    */ package de.biware.mailchecker.ui;
/*    */ 
/*    */ import javafx.scene.control.Label;
/*    */ 
/*    */ public class StyledLabel extends Label {
/*    */   public StyledLabel(String text, String... styles) {
/* 17 */     super(text);
/* 18 */     getStyleClass().addAll(styles);
/*    */   }
/*    */ }


/* Location:              E:\software\sbg-ide\workspace\mailchecker\target\mailchecker-1.0-SNAPSHOT.jar!\de\biware\mailchecke\\ui\StyledLabel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */