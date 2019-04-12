package com.sinmn.core.utils.junit;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sinmn.core.utils.spring.SpringContextUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"}) //加载配置文件 
public abstract class AbstractJUnit4Action extends AbstractJUnit4SpringContextTests{

	@Before
	public void _before(){
		SpringContextUtil.setContext(this.applicationContext);
	}
}
