package com.sinmn.core.utils.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinmn.core.utils.vo.JedisStat;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class MonitorUtil {
	
	private static Logger logger = LoggerFactory.getLogger(MonitorUtil.class);

	// 项目编号后缀格式
	public final static String PRO_NUMBER_SUFFIX_FMT = "%04d";
	
	private final static String TEST_KEY = "TEST_KEY_%s";

	/**
	 * 获取随机账号：随机账号规则：3位大写字母（去除IOL与数字相似字母）+3位数字
	 * 
	 * @return
	 */
	public static String getRandomAccount() {
		final int charRandomLength = 3;
		final int digitRandomLength = 3;
		final int maxCharNum = 23;
		final int maxDigitNum = 10;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] charArr = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
				'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		char[] digitArr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer randomAcct = new StringBuffer("");
		Random r = new Random();
		while (count < charRandomLength) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxCharNum)); // 生成的数最大为23-1

			if (i >= 0 && i < charArr.length) {
				randomAcct.append(charArr[i]);
				count++;
			}
		}
		while (count < charRandomLength + digitRandomLength) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxDigitNum)); // 生成的数最大为23-1

			if (i >= 0 && i < digitArr.length) {
				randomAcct.append(digitArr[i]);
				count++;
			}
		}
		return randomAcct.toString();
	}

	/**
	 * 获取随机账号：随机账号规则：3位大写字母（去除IOL与数字相似字母）+3位数字
	 * 
	 * @return
	 */
	public static String getRandomAcctPwd() {
		final int charRandomLength = 3;
		final int digitRandomLength = 3;
		final int maxCharNum = 23;
		final int maxDigitNum = 10;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] charArr = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
				'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		char[] digitArr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer randomAcct = new StringBuffer("");
		Random r = new Random();
		while (count < charRandomLength) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxCharNum)); // 生成的数最大为23-1

			if (i >= 0 && i < charArr.length) {
				randomAcct.append(charArr[i]);
				count++;
			}
		}
		while (count < charRandomLength + digitRandomLength) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxDigitNum)); // 生成的数最大为23-1

			if (i >= 0 && i < digitArr.length) {
				randomAcct.append(digitArr[i]);
				count++;
			}
		}
		return randomAcct.toString();
	}

	public static String genRandomNum(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	public static boolean isAdmin(String user_name) {
		if ("admin".equals(user_name)) {
			return true;
		}
		return false;
	}

	public static String listToString(List<String> stringList){
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }
	
	public static JedisStat createJedisMonitorVo(JedisPool jedisPool) {
		JedisStat jedisStatVO = new JedisStat();
		
		Jedis jedis = jedisPool.getResource();
		jedis.setex(String.format(TEST_KEY, TimeUtil.formatDateString(new Date(), "yyyy-MM-dd HH:mm:ss")), 1,TimeUtil.formatDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		jedisStatVO.setConnection_flag(true);
		
		return jedisStatVO;
	}

	
	public static void createDataSourceConnectTest(DataSource dataSource) throws SQLException{
		
		Connection connection = dataSource.getConnection();
		logger.info("[data_info]数据库连接正常........");
		connection.close();
		
	}
}
