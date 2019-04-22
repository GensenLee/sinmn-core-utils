/**
 * 发邮件
 */

package com.sinmn.core.utils.util.email;

import com.sinmn.core.utils.vo.EmailHostVO;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wangxc
 *
 */
public class EmailUtil{

	private static Logger logger=LoggerFactory.getLogger(EmailUtil.class);

	/** 邮箱host */
	private static String host = null;

	/** 邮箱 */
	private static String email = null;

	/** 密码 */
	private static String password = null;

	/** 发送人 */
	private static String fromName = null;

	public static void getTemplate() {

	}

	public static void main(String[] args) throws ResourceNotFoundException, ParseErrorException, Exception {
		EmailHostVO emailHostVO = new EmailHostVO("smtp.163.com", "sinmn_test@163.com", "sinmn888", "sinmn test-新月测试");
		setVO(emailHostVO);
		//send("linrs@sinmn.com", "测试", "有按钮吗", "www.baidu.com");
//
//		Map<String, Object> data=new HashMap<String, Object>();
//		data.put("email", "linrs@sinmn.com");
//		data.put("url", "ww.baidu.com");
//		data.put("content", "测试的内容");
//		String content="fdsakljfjklasdfjl";
//		send("ligz@sinmn.com", "bug提醒", content);


        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));

    }

	public static void setVO(EmailHostVO emailHostVO) {
		host = emailHostVO.getHost();
		email = emailHostVO.getEmail();
		password = emailHostVO.getPassword();
		fromName = emailHostVO.getFromName();
	}
  
	/**
	 * 
	 * @param email（邮箱）
	 * @param subject(主题)
	 * @param content(发送的内容)
	 */
	public static void send(String email,String subject,String content){
		
		MailInfo mailInfo = new MailInfo();
		mailInfo.setMailServerHost(host);
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUsername(EmailUtil.email);
		mailInfo.setPassword(password);// 您的邮箱密码
		mailInfo.setFromAddress(EmailUtil.email);
		mailInfo.setNickName(fromName);
		mailInfo.setToAddress(email);//发送的对象
		mailInfo.setSubject(subject);
		mailInfo.setContent(content);
		SimpleMail.sendHtmlMail(mailInfo);
	}

	/**
	 * 填充模板信息
	 * @Param fileName:模板的名称
	 * @Param data:模板需要填充的信息
	 * @return
	 */
	public static String fillTemplate(String fileName,Map<String,Object> data) {
		
		StringWriter stringWriter = new StringWriter();

		try {
			// velocity引擎
			VelocityEngine velocityEngine = new VelocityEngine();
			//获取某个模板
			Template template = velocityEngine.getTemplate("/src/main/resources/templates/"+fileName, "utf-8");
			// 填充模板内容
			VelocityContext velocityContext = new VelocityContext();
			for (Map.Entry<String,Object> entry : data.entrySet()) {
				String key=entry.getKey();
				Object value=entry.getValue();
				if(value!=null) {
					velocityContext.put(key, value);
				}
			}
			// 写到模板
			template.merge(velocityContext, stringWriter);
			
			return stringWriter.toString();
		} catch (Exception e) {
			logger.debug("找不到模板消息");
			return null;
		}

	}

	public static void send(String email, String subString, String content, String url) {
		MailInfo mailInfo = new MailInfo();
		mailInfo.setMailServerHost(host);
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUsername(EmailUtil.email);
		mailInfo.setPassword(password);// 您的邮箱密码
		mailInfo.setFromAddress(EmailUtil.email);
		mailInfo.setNickName(fromName);
		mailInfo.setToAddress(email);
		mailInfo.setSubject(subString);

		//附件
		//        String[] attachFileNames={"d:/Sunset.jpg"};
		//        mailInfo.setAttachFileNames(attachFileNames);

		// 这个类主要来发送邮件
		//mailInfo.setContent("设置邮箱内容");
		//SimpleMail.sendTextMail(mailInfo);// 发送文体格式
		StringBuffer demo = new StringBuffer();
		demo.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")
		.append(
				"<html>\n" +
						"    <head>\n" +
						"        <title>链股邮箱</title>\n" +
						"        <style>\n" +
						"            @media screen and (max-width: 1920px) {\n" +
						"                .contant{\n" +
						"                    /* padding: 0 20%; */\n" +
						"                    width: 600px;\n" +
						"                    margin: auto;\n" +
						"                }\n" +
						"            }\n" +
						"            @media (max-width: 768px) {\n" +
						"                .contant{\n" +
						"                    padding: 0 10px;\n" +
						"                    width: 600px;\n" +
						"                }\n" +
						"            }\n" +
						"            .title{\n" +
						"                display: inline-block;\n" +
						"                line-height: 30px;\n" +
						"                /* padding: 10px 15% 0 5%; */\n" +
						"                font-size: 18px;\n" +
						"                font-weight: 400;\n" +
						"                position: absolute;\n" +
						"                right: 0;\n" +
						"                padding: 10px 30px 10px 0;\n" +
						"                color: #fff;\n" +
						"            }\n" +
						"            .fz14{\n" +
						"                font-size: 14px;\n" +
						"            }\n" +
						"            .a-box{\n" +
						"                text-align: center;\n" +
						"                margin: 20px 0 40px;\n" +
						"                width: 90px;\n" +
						"                height: 30px;\n" +
						"                line-height: 30px;\n" +
						"                background: #257efe;\n" +
						"                color: #fff;\n" +
						"                border-radius: 6px;\n" +
						"            }\n" +
						"            \n" +
						"        </style>\n" +
						"    </head>\n" +
						"    <body>\n" +
						"        <div class=\"contant\">\n" +
						"            <div style=\"position: relative;font-size: 0px;background: #257efe;\">\n" +
						"                <div style=\"position: relative;left: 0;height: 50px;line-height: 80px;display: inline-block;\"><img src=\"http://new.linkkap.com/static/img/linkkapWhite.png\" style=\"height: 30px;padding-left: 30px;float:left;margin-top:10px;\"/></div>\n" +
						"                <div class=\"title\">\n" +
						"                    让激励更简单、可信赖\n" +
						"                </div>\n" +
						"            </div>\n" +
						"            <div style=\"padding: 30px;\">\n" +
						"                <div style=\"font-size: 16px;font-weight: 600;margin-bottom: 10px;\">\n" +
						"                    " + email + " 您好:\n" +
						"                </div>\n" +
						"                <div style=\" color: #333;\">\n" +
						"                        "+content+"\n" +
						"                </div>\n" +
						"                <div class=\"a-box\">\n" +
						"                    <a style=\"text-decoration: none; font-size: 14px; color:#fff;\" href=\""+url+"\" target=\"view_window\">点击进入</a>\n" +
						"                </div>\n" +
						"                <div class=\"fz14\" style=\"color: #999;\">\n" +
						"                        按钮无效？请复制黏贴以下网址至浏览器地址栏打开：\n" +
						"                </div>\n" +
						"                <div style=\"padding-bottom: 20px; color: #257efe;\">\n" +
						"                        "+url+"\n" +
						"                </div>\n" +
						"                <div class=\"fz14\" style=\"color: #999;\">\n" +
						"                        如有疑问，请致电客服电话：020-89285042 或发邮件至 service@linkkap.com\n" +
						"                </div>\n" +
						"                <div style=\"position: relative;height:100px;line-height: 100px; color: #999;\">\n" +
						"                    <span style=\"font-size: 14px;\">关注我们了解更多:</span>\n" +
						"                    <img src=\"http://www.linkkap.com/static/img/code.jpg\" style=\"position: absolute;height: 100px;\"/>\n" +
						"                </div>\n" +
						"            </div>\n" +
						"            <div style=\"text-align: right;height: 40px; line-height: 40px;background-color: #f3f3f3;padding-right: 30px;font-size: 14px;\">\n" +
						"                © 粤ICP备18072939号-1&nbsp;&nbsp;|&nbsp;&nbsp;www.linkkap.com\n" +
						"            </div>\n" +
						"        </div>\n" +
						"        \n" +
						"    </body>\n" +
				"</html>");
		mailInfo.setContent(demo.toString());
		SimpleMail.sendHtmlMail(mailInfo);
	}

}

