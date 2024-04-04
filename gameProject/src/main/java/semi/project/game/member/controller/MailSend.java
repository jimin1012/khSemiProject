package semi.project.game.member.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mailSend")
public class MailSend extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String userEmail = req.getParameter("userEmail");

		final String bodyEncoding = "UTF-8"; //콘텐츠 인코딩

		String subject = "[아무고토못하조GAMES] 회원가입 코드 : "; // 이메일 제목
		String fromEmail = "iamjimin0722@gmail.com"; // 이메일을 보낼 이메일(지금은 지민아이디)
		String fromUsername = "아무고토못하조GAMES";
		String toEmail = userEmail; // 콤마(,)로 여러개 나열

		final String username = "iamjimin0722@gmail.com";         
		final String password = "ondd bchs cgyl logl";

		String portNo = "587"; // 587 또는 465;


		// 대문자만 나오도록
		int leftLimit = 65; // letter 'A'
		int rightLimit = 90; // letter 'Z'
		int targetStringLength = 5; // 인증코드 최대길이
		Random random = new Random(); // 랜덤 함수 호출
		String AuthenticationCode = random.ints(leftLimit, rightLimit + 1)
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		
		// 이미지 때문에 url 따옴
		StringBuffer url = req.getRequestURL();
		String http = (String) url.subSequence(0, url.indexOf(req.getContextPath()));
		
		// 메일에 출력할 텍스트
		StringBuffer sb = new StringBuffer();
		sb.append("<div style='background-color: black; width: 500px; text-align: center; border: 1px solid rgb(84, 85, 84);'>"+
		"<table style='width: 100%;'>"+
		"<tr>"+
		"<td><img style='width:150px; height: auto;' src='"+http+req.getContextPath()+"/resources/images/main/image.png'></td>"
		+"</tr>"
		+"<tr>"+
		"<td style='color:white; font-size: 26px; padding-top: 20px;'>인증코드</td>"+
		"</tr>"
		+"<tr>"+
		"<td style='display: flex;justify-content: center;'>"+
		"<div style='width: 400px; height: 200px; margin-top: 20px;'>"+
		"<div style='font-size: 20px; color: gray;'>회원가입 인증코드입니다.</div>"
		+"<div style=' margin-top: 30px; width: 100%; height: 100px; line-height: 90px;  font-size: 30px;letter-spacing: 10px; background-color: rgb(231, 233, 225);'>"+AuthenticationCode+"</div>"
		+"</div>"
		+"</td>"
		+"</tr>"+
		"<tr style='display: flex; justify-content: center;'>"+
		"<td><img style='width: 40px;' src='https://lolstatic-a.akamaihd.net/email-marketing/betabuddies/facebook-logo.png'></td>"+
		"<td><img style='width: 40px; margin: 0 20px;' src='https://lolstatic-a.akamaihd.net/email-marketing/betabuddies/instagram-logo.png'></td>"+
		"<td><img style='width: 40px; margin-right: 20px;' src='https://lolstatic-a.akamaihd.net/email-marketing/betabuddies/youtube-logo.png'></td>"+
		"<td><img style='width: 40px;' src='https://lolstatic-a.akamaihd.net/email-marketing/betabuddies/twitter-logo.png'></td>"
		+"</tr>"
		+"<tr>"
		+"<td>"
		+"<p style='color:gray; font-weight: bold; margin-top:40px'>개인정보 | 처리방침고객지원서비스 | 약관</p>"
		+"</td>"
		+"</tr>"
		+"<tr>"
		+"<td>"
		+"<p style='color:gray; font-size: 11px; margin: 20px 0'>본 메일은 이용안내 알림을 위해 발송되었습니다.</p> "
		+"</td>"
		+"</tr>"
		+"<tr>"
		+"<td>"
		+"<p style='color: gray; font-size: 11px; margin-bottom: 10px'>Copyright © 2023-2024 Web site developed by 아무고토못하조</p>"
		+"</td>"
		+"</tr>"
		+"</table>"+
		"</div>\n"); 
		String html = sb.toString();

		// 메일 옵션 설정
		Properties props = new Properties();    
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", portNo);
		props.put("mail.smtp.auth", "true");

		// 따로 추가한 부분
		props.put("mail.debug", "true");
		props.put("mail.smtp.starttls.required","true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.starttls.enable", "true");
		
		props.put("mail.smtp.quitwait", "false");

		try {
			// 메일 서버  인증 계정 설정
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			};

			// 메일 세션 생성
			Session session = Session.getInstance(props, auth);

			// 메일 송/수신 옵션 설정
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail, fromUsername));
			message.setRecipients(RecipientType.TO, InternetAddress.parse(toEmail, false));
			message.setSubject(subject+AuthenticationCode);
			message.setSentDate(new Date());

			// 메일 콘텐츠 설정
			Multipart mParts = new MimeMultipart();
			MimeBodyPart mTextPart = new MimeBodyPart();
			MimeBodyPart mFilePart = null;

			// 메일 콘텐츠 - 내용
			mTextPart.setText(html, bodyEncoding, "html");
			mParts.addBodyPart(mTextPart);

			// 메일 콘텐츠 설정
			message.setContent(mParts);

			// MIME 타입 설정
			MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
			CommandMap.setDefaultCommandMap(MailcapCmdMap);

			// 메일 발송
			message.saveChanges();
			Transport.send( message );
			session.setDebug(true);


			// ajax로 인증코드 전송
			resp.getWriter().print(AuthenticationCode);

		} catch ( Exception e ) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}

	}


}
