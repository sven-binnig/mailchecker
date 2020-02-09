package de.biware.mailchecker.ui;

import de.biware.mailchecker.api.MailAccount;
import de.biware.mailchecker.api.MailCheckerException;

public interface MailCheckView {
  void onMailCheckPerformed(MailAccount paramMailAccount, int paramInt);
  
  void onMailCheckerException(MailCheckerException paramMailCheckerException);
}


/* Location:              E:\software\sbg-ide\workspace\mailchecker\target\mailchecker-1.0-SNAPSHOT.jar!\de\biware\mailchecke\\ui\MailCheckView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */