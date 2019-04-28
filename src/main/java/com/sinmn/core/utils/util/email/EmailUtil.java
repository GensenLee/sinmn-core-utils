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
//		Map<String, Object> data=new HashMap<String, Object>();
//		data.put("email", "linrs@sinmn.com");
//		data.put("url", "ww.baidu.com");
//		data.put("content", "测试的内容");
		String content="fdsakljfjklasdfjl";
		send("gz.lee@castlers.com", "bug提醒", content);


//        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));

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

}

