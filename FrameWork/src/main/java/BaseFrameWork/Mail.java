package BaseFrameWork;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {

	static String timestamp = new SimpleDateFormat("MMdd_HHmm").format(new Date());

	public static Boolean sendmailAttachment(String FileName, String to) throws Exception {

		String fileAttachment = "D:\\EMP 878 AJ\\Reports\\FOTA\\" + FileName;
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("cauto@contus.in", "contusauto123");
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("arputhajerrin.e@contus.in"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("SVEP4158_MAHINDRA_FOTA_WEB_ATMTN_REPORT_" + timestamp);
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Hi Team,"
					+ "\n\n The Automation report for SVEP4154 JCB Web. Please find the Report attached with this mail."
					+ "\n\n Total testcases automated = 285"
					+ "\n\n The following are the Automated Modules"
					+ "\n\n 1. Login"
					+ "\n\n Download the .HTML file attached with this mail to view the automation report");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(fileAttachment);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(FileName);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Message sent");
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
