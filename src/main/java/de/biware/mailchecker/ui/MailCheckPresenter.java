package de.biware.mailchecker.ui;

import de.biware.mailchecker.api.MailAccount;

public interface MailCheckPresenter {
  void registerMailCheckView(MailCheckView paramMailCheckView);
  
  void performMailCheck(MailAccount paramMailAccount);
}


/* Location:              E:\software\sbg-ide\workspace\mailchecker\target\mailchecker-1.0-SNAPSHOT.jar!\de\biware\mailchecke\\ui\MailCheckPresenter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */