package de.biware.mailchecker.ui;

import de.biware.mailchecker.api.MailAccount;
import de.biware.mailchecker.api.MailCheckerException;

public interface MailCheckView {

    void onMailCheckPerformed(MailAccount paramMailAccount, int paramInt);

    void onMailCheckerException(MailCheckerException paramMailCheckerException);

    void startupPeriodicMailChecking();
}
