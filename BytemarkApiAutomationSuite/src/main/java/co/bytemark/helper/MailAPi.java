package co.bytemark.helper;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.ContentType;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

public class MailAPi {

//	public static void fetch(String pop3Host, String storeType, String user, String password) {
//		try {
//			// create properties field
//			Properties properties = new Properties();
//			properties.put("mail.store.protocol", "pop3");
//			properties.put("mail.pop3.host", pop3Host);
//			properties.put("mail.pop3.port", "995");
//			properties.put("mail.pop3.starttls.enable", "true");
//			Session emailSession = Session.getDefaultInstance(properties);
//			// emailSession.setDebug(true);
//
//			// create the POP3 store object and connect with the pop server
//			Store store = emailSession.getStore("pop3s");
//
//			store.connect(pop3Host, user, password);
//
//			// create the folder object and open it
//			Folder emailFolder = store.getFolder("INBOX");
//			emailFolder.open(Folder.READ_ONLY);
//
//			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//			// retrieve the messages from the folder in an array and print it
//			Message[] messages = emailFolder.getMessages();
//			System.out.println("messages.length---" + messages.length);
//
//			for (int i = 0; i < messages.length; i++) {
//				Message message = messages[i];
//				System.out.println("---------------------------------");
//				writePart(message);
//				String line = reader.readLine();
//				if ("YES".equals(line)) {
//					message.writeTo(System.out);
//				} else if ("QUIT".equals(line)) {
//					break;
//				}
//			}
//
//			// close the store and folder objects
//			emailFolder.close(false);
//			store.close();
//
//		} catch (NoSuchProviderException e) {
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void main(String[] args) {
//
//		String host = "pop.gmail.com";// change accordingly
//		String mailStoreType = "pop3";
//		String username = "velann21@gmail.com";// change accordingly
//		String password = "VELANN21";// change accordingly
//
//		// Call method fetch
//		fetch(host, mailStoreType, username, password);
//
//	}
//
//	/*
//	 * This method checks for content-type based on which, it processes and fetches
//	 * the content of the message
//	 */
//	public static void writePart(Part p) throws Exception {
//	      if (p instanceof Message)
//	         //Call methos writeEnvelope
//	         writeEnvelope((Message) p);
//
//	      System.out.println("----------------------------");
//	      System.out.println("CONTENT-TYPE: " + p.getContentType());
//
//	      //check if the content is plain text
//	      if (p.isMimeType("text/plain")) {
//	         System.out.println("This is plain text");
//	         System.out.println("---------------------------");
//	         System.out.println((String) p.getContent());
//	      } 
//	      //check if the content has attachment
//	      else if (p.isMimeType("multipart/*")) {
//	         System.out.println("This is a Multipart");
//	         System.out.println("---------------------------");
//	         Multipart mp = (Multipart) p.getContent();
//	         int count = mp.getCount();
//	         for (int i = 0; i < count; i++)
//	            writePart(mp.getBodyPart(i));
//	      } 
//	      //check if the content is a nested message
//	      else if (p.isMimeType("message/rfc822")) {
//	         System.out.println("This is a Nested Message");
//	         System.out.println("---------------------------");
//	         writePart((Part) p.getContent());
//	      } 
//	      //check if the content is an inline image
//	      else if (p.isMimeType("image/jpeg")) {
//	         System.out.println("--------> image/jpeg");
//	         Object o = p.getContent();
//
//	         InputStream x = (InputStream) o;
//	         // Construct the required byte array
//	         System.out.println("x.length = " + x.available());
//	         int i = 0;
//	         byte[] bArray = new byte[x.available()];
//
//	         while ((i = (int) ((InputStream) x).available()) > 0) {
//	            int result = (int) (((InputStream) x).read(bArray));
//	            if (result == -1)
//	         
//	            break;
//	         }
//	         FileOutputStream f2 = new FileOutputStream("/tmp/image.jpg");
//	         f2.write(bArray);
//	      } 
//	      else if (p.getContentType().contains("image/")) {
//	         System.out.println("content type" + p.getContentType());
//	         File f = new File("image" + new Date().getTime() + ".jpg");
//	         DataOutputStream output = new DataOutputStream(
//	            new BufferedOutputStream(new FileOutputStream(f)));
//	            com.sun.mail.util.BASE64DecoderStream test = 
//	                 (com.sun.mail.util.BASE64DecoderStream) p
//	                  .getContent();
//	         byte[] buffer = new byte[1024];
//	         int bytesRead;
//	         while ((bytesRead = test.read(buffer)) != -1) {
//	            output.write(buffer, 0, bytesRead);
//	         }
//	      } 
//	      else {
//	         Object o = p.getContent();
//	         if (o instanceof String) {
//	            System.out.println("This is a string");
//	            System.out.println("---------------------------");
//	            System.out.println((String) o);
//	         } 
//	         else if (o instanceof InputStream) {
//	            System.out.println("This is just an input stream");
//	            System.out.println("---------------------------");
//	            InputStream is = (InputStream) o;
//	            is = (InputStream) o;
//	            int c;
//	            while ((c = is.read()) != -1)
//	               System.out.write(c);
//	         } 
//	         else {
//	            System.out.println("This is an unknown type");
//	            System.out.println("---------------------------");
//	            System.out.println(o.toString());
//	         }
//	      }
//
//	   }
//
//	/*
//	 * This method would print FROM,TO and SUBJECT of the message
//	 */
//	public static void writeEnvelope(Message m) throws Exception {
//		System.out.println("This is the message envelope");
//		System.out.println("---------------------------");
//		Address[] a;
//
//		// FROM
//		if ((a = m.getFrom()) != null) {
//			for (int j = 0; j < a.length; j++)
//				System.out.println("FROM: " + a[j].toString());
//		}
//
//		// TO
//		if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
//			for (int j = 0; j < a.length; j++)
//				System.out.println("TO: " + a[j].toString());
//		}
//
//		// SUBJECT
//		if (m.getSubject() != null)
//			System.out.println("SUBJECT: " + m.getSubject());
//
//	}
//
//}
 public static String getMail( String subject, String accountName ,String passWord) throws Exception{
 System.out.println("Searching for Email with subject:"+subject.trim());
 Properties props = System.getProperties();
 props.setProperty("mail.store.protocol", "imaps");
 Session session = Session.getDefaultInstance(props, null);
 Store store = session.getStore("imaps");
 try {
 store.connect("imap.gmail.com",accountName, passWord);
 //System.out.println(store);
 /*
 * If the mail is found then the loop below terminates with the return
 statement
 * If the mail is not in the in box then it pauses for few seconds and check
 again
 * It repeats for 20 times before it confirms that there is not mail in the
 inbox
 */
 for( int count=0; count<60;){
 Folder inbox = store.getFolder("Inbox");
 inbox.open(Folder.READ_WRITE);
 Message messages[] = inbox.getMessages();
 System.out.println(messages.length);
 /*
 * this for loop Starts with the latest mails in the inbox and goes on.
 */
 for (int i=messages.length-1;i>=0; i--) {
 //System.out.print(messages[i].getSubject());
 //This if condition checks for the mail with the given subject
 System.out.println(messages[i].getSubject());
 if(messages[i].getSubject().contains(subject)){
 System.out.println(i + ": " + messages[i].getFrom()[0]+ "\t" +
 messages[i].getSubject());
 //System.out.println(messages[i].getContent());
 String contentStr = null;
 ContentType ct = new ContentType(messages[i].getContentType() );
 if(ct.getPrimaryType().equals("multipart")) {
 //contentStr = getMultiPart(messages[i]);
 } else {
 contentStr = messages[i].getContent().toString();
 }
 return messages[i].getSubject().toString()+ " " + contentStr;
 }
 }
 count++;
 return "";
 }
 } catch (NoSuchProviderException e) {
 e.printStackTrace();
 return "";
 } catch (MessagingException e) {
 e.printStackTrace();
 return "";
 }
 catch(NullPointerException e)
 {
 System.out.println("in null catch");
 }
 catch (Exception e) {
 System.out.println("The error is " + e.toString());
 return "";
 }
 finally{
 store.close();
 }
 return "";
 }

 public static void check(String host, String storeType, String user,String
 password)
 {
 try {

 //create properties field
 Properties properties = new Properties();

 properties.put("mail.imap.host", host);
 properties.put("mail.imap.port", "995");
 properties.put("mail.imap.starttls.enable", "true");
 Session emailSession = Session.getDefaultInstance(properties);

 //create the POP3 store object and connect with the pop server
 Store store = emailSession.getStore("pop3s");

 store.connect(host, user, password);

 //create the folder object and open it
 Folder emailFolder = store.getFolder("INBOX");
 emailFolder.open(Folder.READ_ONLY);

 // retrieve the messages from the folder in an array and print it
 Message[] messages = emailFolder.getMessages();
 System.out.println("messages.length---" + messages.length);

 for (int i = 0, n = messages.length; i < n; i++) {
	 
	 
 Message message = messages[i];
 //if(message.getSubject().contains("Fwd: Welcome to Marta Customer Web, powered by Bytemark")) {
// if(message.getFrom()[0].equals("singaravelan@bytemark.co")) {
 System.out.println("---------------------------------");
 System.out.println("Email Number " + (i + 1));
 System.out.println("Subject: " + message.getSubject());
 System.out.println("From: " + message.getFrom()[0]);
 System.out.println("Text: " + message.getContent().toString());
 }

 //}

 //close the store and folder objects
 emailFolder.close(false);
 store.close();

 } catch (NoSuchProviderException e) {
 e.printStackTrace();
 } catch (MessagingException e) {
 e.printStackTrace();
 } catch (Exception e) {
 e.printStackTrace();
 }
 }

 public static void main(String[] args) throws Exception {

 String host = "imap.gmail.com";// change accordingly
 String mailStoreType = "imap";
 String username = "velann21@gmail.com";// change accordingly
 String password = "VELANN21";// change accordingly

 check(host, mailStoreType, username, password);
 getMail("Fwd: Welcome to Marta Customer Web, powered by Bytemark","singaravelan@bytemark.co","Siar@123");

 }


}
