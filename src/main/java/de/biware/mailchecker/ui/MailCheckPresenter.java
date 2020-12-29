package de.biware.mailchecker.ui;

import de.biware.mailchecker.api.MailAccount;

public interface MailCheckPresenter {

    void registerMailCheckView(MailCheckView paramMailCheckView);

    void performMailCheck(MailAccount paramMailAccount);
}
