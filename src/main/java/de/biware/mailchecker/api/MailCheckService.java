package de.biware.mailchecker.api;

import java.util.List;

public interface MailCheckService {
    int countUnreadMails(MailAccount paramMailAccount) throws MailCheckerException;
    List<MailShortInfo> getMailShortInfo(MailAccount paramMailAccount) throws MailCheckerException;
}
