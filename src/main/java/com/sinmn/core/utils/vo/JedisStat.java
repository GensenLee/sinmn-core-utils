package com.sinmn.core.utils.vo;

import java.io.Serializable;

public class JedisStat implements Serializable{
	private static final long serialVersionUID = 4851841566809261454L;
	/** 最小空闲连接 */ 
    private int minIdle;
	/**保持idel状态的对象数*/
	private int maxIdle;
	/** 最大分配的对象数，建议稍大于中间件最大并发线程数（如Tomcat的maxThreads值）  */
    private int maxTotal;
    /** 每个请求最大等待时间 */
    private long maxWaitMillis;
    /** 在空闲连接回收器线程运行期间休眠的时间值,以毫秒为单位 */
    private long timeBetweenEvictionRunsMillis;
    /** 空闲连接多长时间后会被收回 */
    private long minEvictableIdleTimeMillis;
    /** 当调用borrow Object方法时，是否进行有效性检查 */
    private boolean testOnBorrow;
    /** 当前连接数量 */
    private int numActive;
    /** 当前空闲连接 */
    private int numIdle;
    /** 当前等待数 */
    private int numWaiters;
    /** 每次空闲连接回收器线程(如果有)运行时检查的连接数量 */
    private int numTestsPerEvictionRun;
    /** 超时时间(以秒数为单位) */
    private int removeAbandonedTimeout;
    /** 当池耗尽的时候是否block，如果block的话则会idleObjects.pollFirst(borrowMaxWaitMillis,TimeUnit.MILLISECONDS);否则直接throw new NoSuchElementException("Pool exhausted"); */
    private boolean blockWhenExhausted;
    /** 连接池中，已经创建jedis对象的数量 */
    private long createdCount;
    /** 连接池中，已经被取出jedis对象的数量 */
    private long borrowedCount;
    /** 连接池中，已经释放回收的jedis对象数量，该值+直接销毁的Jedis对象数量=createdCount */
    private long returnedCount;
    /** 连接池中，在取出jedis对象前，检查不通过销毁的Jedis对象数量 */
    private long destroyedByBorrowValidationCount;
    /** 连接池中，通过空闲连接回收器线程销毁的jedis对象数量 */
    private long destroyedByEvictorCount;
    /** 连接池中，已经销毁的Jedis对象数量=destroyedByBorrowValidationCount+destroyedByEvictorCount+直接释放的对象数量 */
    private long destroyedCount;
    private String creationStackTrace;
    private String evictionPolicyClassName;
    private String factoryType;
    private String jmxName;
    private boolean lifo;
    /** 连接被泄露时是否打印  */
    private boolean logAbandoned;
    
    private boolean connection_flag = true;
    
	public int getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public int getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}
	public long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}
	public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}
	public long getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}
	public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	public int getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
	public int getNumActive() {
		return numActive;
	}
	public void setNumActive(int numActive) {
		this.numActive = numActive;
	}
	public int getNumIdle() {
		return numIdle;
	}
	public void setNumIdle(int numIdle) {
		this.numIdle = numIdle;
	}
	public int getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}
	public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}
	public int getNumWaiters() {
		return numWaiters;
	}
	public void setNumWaiters(int numWaiters) {
		this.numWaiters = numWaiters;
	}
	public int getRemoveAbandonedTimeout() {
		return removeAbandonedTimeout;
	}
	public void setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}
	public boolean isBlockWhenExhausted() {
		return blockWhenExhausted;
	}
	public void setBlockWhenExhausted(boolean blockWhenExhausted) {
		this.blockWhenExhausted = blockWhenExhausted;
	}
	public long getBorrowedCount() {
		return borrowedCount;
	}
	public void setBorrowedCount(long borrowedCount) {
		this.borrowedCount = borrowedCount;
	}
	public long getCreatedCount() {
		return createdCount;
	}
	public void setCreatedCount(long createdCount) {
		this.createdCount = createdCount;
	}
	public String getCreationStackTrace() {
		return creationStackTrace;
	}
	public void setCreationStackTrace(String creationStackTrace) {
		this.creationStackTrace = creationStackTrace;
	}
	public long getDestroyedByBorrowValidationCount() {
		return destroyedByBorrowValidationCount;
	}
	public void setDestroyedByBorrowValidationCount(
			long destroyedByBorrowValidationCount) {
		this.destroyedByBorrowValidationCount = destroyedByBorrowValidationCount;
	}
	public long getDestroyedByEvictorCount() {
		return destroyedByEvictorCount;
	}
	public void setDestroyedByEvictorCount(long destroyedByEvictorCount) {
		this.destroyedByEvictorCount = destroyedByEvictorCount;
	}
	public long getDestroyedCount() {
		return destroyedCount;
	}
	public void setDestroyedCount(long destroyedCount) {
		this.destroyedCount = destroyedCount;
	}
	public String getEvictionPolicyClassName() {
		return evictionPolicyClassName;
	}
	public void setEvictionPolicyClassName(String evictionPolicyClassName) {
		this.evictionPolicyClassName = evictionPolicyClassName;
	}
	public String getFactoryType() {
		return factoryType;
	}
	public void setFactoryType(String factoryType) {
		this.factoryType = factoryType;
	}
	public String getJmxName() {
		return jmxName;
	}
	public void setJmxName(String jmxName) {
		this.jmxName = jmxName;
	}
	public boolean isLifo() {
		return lifo;
	}
	public void setLifo(boolean lifo) {
		this.lifo = lifo;
	}
	public boolean isLogAbandoned() {
		return logAbandoned;
	}
	public void setLogAbandoned(boolean logAbandoned) {
		this.logAbandoned = logAbandoned;
	}
	public long getReturnedCount() {
		return returnedCount;
	}
	public void setReturnedCount(long returnedCount) {
		this.returnedCount = returnedCount;
	}
	public long getMaxWaitMillis() {
		return maxWaitMillis;
	}
	public void setMaxWaitMillis(long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}
	public boolean isConnection_flag() {
		return connection_flag;
	}
	public void setConnection_flag(boolean connection_flag) {
		this.connection_flag = connection_flag;
	}
    
}
