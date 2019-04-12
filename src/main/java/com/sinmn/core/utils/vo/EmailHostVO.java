package com.sinmn.core.utils.vo;

public class EmailHostVO extends BaseBean {

    /** 邮箱host */
    private String host;

    /** 邮箱 */
    private String email;

    /** 密码 */
    private String password;

    /** 发送人 */
    private String fromName;
    
    /**端口**/
    private Integer port;

    public EmailHostVO(String host, String email, String password, String fromName) {
        this.host = host;
        this.email = email;
        this.password = password;
        this.fromName = fromName;
    }
    
    public EmailHostVO(String host, Integer port ,String email, String password, String fromName) {
        this.host = host;
        this.port=port;
        this.email = email;
        this.password = password;
        this.fromName = fromName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	
    
}
