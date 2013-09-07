package com.rss.service.tools;

import java.util.Calendar;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	
	private String userName;
 
	private String password;
	
	private String email;
	
	private String smtpHost = "smtp.263xmail.com";
	
	public SendMail(String userName, String password, String email){
		
		this.userName = userName;
		this.password = password;
		this.email    = email;
	}
	
	public void sendEmail (){
		
		String mailContent = "<div>亲爱的"+ userName + ":</div>";
		mailContent += "<div style='margin-left:2em;'>您好！</div>";
		mailContent += "<div style='margin-left:2em'>欢迎您在iRss申请注册，因此我们发送这封邮件进行确认。</div>";
		mailContent += "<br /><div style='margin-left:2em'>您的用户名为："+ userName + "</div>";
		mailContent += "<div style='margin-left:2em'>您的密码为："+ password + "</div>";
		
		//mailContent += "<div style='margin-left:2em'>请您在七天内点击下面的链接来验证您的邮箱。</div>";
		//mailContent += "<div style='margin-left:4em'>http://59.64.131.41/iRss/user/validate.action?email="+ email +"</div>";
		//mailContent += "<div style="margin-left:2em">如果无法点击上面的链接，您可以复制该地址，并粘贴在浏览器的地址栏中访问。</div>";
		
		mailContent += "<br /><div>此致</div>";
		mailContent += "<div style='margin-left:4em'>敬礼</div>";
		
		mailContent += "<div style='margin-left:15em'>iRss团队</div>";
		
		Calendar now=Calendar.getInstance();
		String datestr=now.get(Calendar.YEAR)+"年"+(now.get(Calendar.MONTH)+1)+"月"+now.get(Calendar.DAY_OF_MONTH) + "日";
    	mailContent = mailContent + "<div style='margin-left:14em;'>" + datestr + "</div>";
 
    	
    	Properties props = System.getProperties();

		props.put("mail.smtp.host", smtpHost);

		props.put("mail.smtp.auth", "true");

		MailAuthenticator auth = new MailAuthenticator();

		Session session = Session.getInstance(props,auth);
 
		MimeMessage mm = new MimeMessage(session);

		Transport trans = null;

		try {

			mm.setFrom(new InternetAddress("irssteam@gmail.com"));
			
        	mm.setRecipient(Message.RecipientType.TO, new InternetAddress(
            email));
		    mm.setSubject("欢迎来到iRss");
		     // 4、邮件正文
		     MimeBodyPart mbp = new MimeBodyPart();
		     mbp.setContent(mailContent, "text/html;charset=utf-8");
		     // 5、描述关系
		     MimeMultipart mul = new MimeMultipart();
		     mul.addBodyPart(mbp);
		     // 设置
		     mm.setContent(mul);
		     mm.saveChanges();

			// send the message

			trans = session.getTransport("smtp");

			try {

				trans.connect(smtpHost, "lizhenyu_1@ebupt.com",
						"lizhenyu_1");

			} catch (AuthenticationFailedException e) {

				e.printStackTrace();

				System.out.println("连接邮件服务器错误：");

			} catch (MessagingException e) {

				System.out.println("连接邮件服务器错误：");

			}

			trans.send(mm);

			trans.close();

		} catch (MessagingException mex) {

			System.out.println("发送邮件失败：");

			mex.printStackTrace();

			Exception ex = null;

			if ((ex = mex.getNextException()) != null) {

				System.out.println(ex.toString());

				ex.printStackTrace();

			}
		}
    	
	}
	
	
	public static void main (String args[]){
		
		SendMail rs = new SendMail("wuchanglong", "123456", "wuchanglong@ebupt.com");
		

		rs.sendEmail();

	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}


class MailAuthenticator extends Authenticator 

{ 
    //****************************** 

    //由于发送邮件的地方比较多, 

    //下面统一定义发送邮件的用户名,口令. 

    //****************************** 

    public static String HUAWEI_MAIL_USER = "lizhenyu_1@ebupt.com"; 

    public static String HUAWEI_MAIL_PASSWORD = "lizhenyu_1"; 
  
    public MailAuthenticator() 

    { 

    } 
 

    protected PasswordAuthentication getPasswordAuthentication() 

    { 

        return new PasswordAuthentication(HUAWEI_MAIL_USER, HUAWEI_MAIL_PASSWORD); 

    } 

  

} 


