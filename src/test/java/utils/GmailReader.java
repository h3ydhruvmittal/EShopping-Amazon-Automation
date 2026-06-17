package utils;

import constants.Credentials;
import jakarta.mail.*;
import jakarta.mail.search.SubjectTerm;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailReader {

    private Store connect() throws Exception {

        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");

        Session session = Session.getDefaultInstance(props);
        Store store = session.getStore("imaps");

        store.connect(
                "imap.gmail.com",
                Credentials.USERNAME.getValue(),
                ConfigReader.getProperty("gmail.password")
        );
        return store;
    }

    private Message getLatestEmailBySubject(Folder inbox, String expectedSubject) throws Exception {
    Message[] messages = inbox.search(new SubjectTerm(expectedSubject));
    long tenMinutesAgo = System.currentTimeMillis() - (10 * 60 * 1000L);
    for (int i = messages.length - 1; i >= 0; i--) {
        Message message = messages[i];
        if (message.getReceivedDate() != null
                && message.getReceivedDate().getTime() >= tenMinutesAgo) {
            return message;
        }
    }
    throw new RuntimeException("No email found with subject " + expectedSubject + " in the last 10 minutes");

}

    private String extractTextFromMessage(Message message) throws Exception {
        Object content = message.getContent();

        if (content instanceof String) {
            return (String) content;
        }

        if (content instanceof Multipart multipart) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);

                if (bodyPart.isMimeType("text/plain")) {
                    result.append(bodyPart.getContent());
                }

                if (bodyPart.isMimeType("text/html")) {
                    result.append(bodyPart.getContent());
                }
            }
            return result.toString();
        }
        return "";
    }

    public String getLatestEmailSubject(String expectedSubject) throws Exception {
        Store store = connect();
        Folder inbox = store.getFolder("INBOX");

        try {
            inbox.open(Folder.READ_ONLY);
            Message email = getLatestEmailBySubject(inbox, expectedSubject);
            return email.getSubject();
        } finally {
            inbox.close(false);
            store.close();
        }
    }

    public String getLatestEmailContent(String expectedSubject) throws Exception {
        Store store = connect();
        Folder inbox = store.getFolder("INBOX");

        try {
            inbox.open(Folder.READ_ONLY);
            Message email = getLatestEmailBySubject(inbox, expectedSubject);
            return extractTextFromMessage(email);
        } finally {
            inbox.close(false);
            store.close();
        }
    }

    public String getLatestOtp(String expectedSubject) throws Exception {
        String content = getLatestEmailContent(expectedSubject);
        Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new RuntimeException("OTP not found in email");
    }

    public String waitForOtp(String expectedSubject, int timeoutSeconds) throws Exception {
        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000L);
        while (System.currentTimeMillis() < endTime) {
            try {
                String otp = getLatestOtp(expectedSubject);
                if (!otp.isBlank()) {
                    return otp;
                }
            } catch (Exception ignored) {
            }
            Thread.sleep(5000);
        }

        throw new RuntimeException("OTP email not received within " + timeoutSeconds + " seconds");
    }
}