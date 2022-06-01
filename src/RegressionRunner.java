import org.simplejavamail.api.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RegressionRunner {

    private final String smtpEmail;
    private final String smtpPassword;
    private final String smtpHost;
    private final int smtpPort;
    private final List<String> scriptCommands;
    private final String recipientEmail;
    private final StringBuilder emailContentBuilder;
    private boolean buildSuccess;

    public RegressionRunner(
            String[] scriptCommands,
            String smtpHost,
            int smtpPort,
            String smtpEmail,
            String smtpPassword,
            String recipientEmail
    ) {
        this.scriptCommands = Arrays.asList(scriptCommands);
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.smtpEmail = smtpEmail;
        this.smtpPassword = smtpPassword;
        this.recipientEmail = recipientEmail;
        this.emailContentBuilder = new StringBuilder();
        this.buildSuccess = false;
    }

    public boolean startProcess() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(this.scriptCommands);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        appendTestResults(process);
        process.waitFor();

        this.buildSuccess = process.exitValue() == 0;
        this.emailContentBuilder.insert(0, "Build success: " + this.buildSuccess + '\n');
        return this.buildSuccess;
    }

    public void emailTestResults() {
        Email email = EmailBuilder.startingBlank()
                .from(smtpEmail)
                .to(recipientEmail)
                .withSubject("Regression Test Results: JavaCheckers/tst/")
                .withPlainText(getTestResults())
                .buildEmail();

        MailerBuilder.withSMTPServer(this.smtpHost, this.smtpPort, this.smtpEmail, this.smtpPassword)
                .buildMailer()
                .sendMail(email);
    }

    private String getTestResults() {
        return this.emailContentBuilder.toString();
    }

    private void appendTestResults(Process process) throws IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = inputReader.readLine()) != null) {
            System.out.println(line);

            if (line.startsWith("Test run finished after") || !this.emailContentBuilder.isEmpty()) {
                this.emailContentBuilder.append(line).append('\n');
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Usage: java RegressionRunner <SMTP_Host> <SMTP_Port> <SMTP_Email> <SMTP_Password> <recipientEmail>");
            return;
        }

        String smtpHost = args[0];
        int smtpPort = Integer.parseInt(args[1]);
        String smtpEmail = args[2];
        String smtpPassword = args[3];
        String recipientEmail = args[4];

        System.out.println("Running regression build");

        try {

            RegressionRunner runner = new RegressionRunner(
                    new String[]{
                            "java",
                            "-jar",
                            "junit-platform-console-standalone-1.8.2.jar",
                            "--classpath",
                            "../target/test-classes:../target/classes:mockito-core-4.4.0.jar:byte-buddy-1.12.8.jar:objenesis-3.2.jar",
                            "--scan-classpath"
                    },
                    smtpHost,
                    smtpPort,
                    smtpEmail,
                    smtpPassword,
                    recipientEmail
            );

            runner.startProcess();
            runner.emailTestResults();

            System.out.println(runner.getTestResults());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
