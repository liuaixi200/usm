package com.lwx.usm.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lwx.usm.exception.BizException;

/**
 * 分布 式锁工具类
 * 原理  利用数据库事物实现
 * 获取锁，往表中插入或更新一条数蛋白石
 * 释放锁，提交或回滚事物
 * @author liuax01
 *
 */
public class DistributeLockUtil {
	
	protected static Log logger = LogFactory.getLog(DistributeLockUtil.class);
	
	private static final String LOCK_NAME = "batck_lock";
	private static final String LOCK_TYPE = "batck_lock";
	
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

	public static void lock(String key){
		Connection conn = threadLocal.get();
		if(null != conn){
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("关闭连接失败:",e);
			}
		}
		threadLocal.remove();
		DataSource dataSource = SpringContextUtil.getBean("dataSource");
		try {
			conn = dataSource.getConnection();
			threadLocal.set(conn);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			logger.error("获取连接失败:",e);
			throw new BizException("获取连接失败，",e);
		}
		//开始更新数据库
		PreparedStatement state = null;
		try {
			state = conn.prepareStatement("select lock_type from transaction_lock where lock_id=?");
			state.setString(1, key);
			ResultSet set = state.executeQuery();
			if(set.next()){
				//更新
				state = conn.prepareStatement("update transaction_lock set lock_count=lock_count+1 where lock_id=?");
				state.setString(1, key);
				state.executeUpdate();
			} else {
				//插入
				state = conn.prepareStatement("insert into transaction_lock(lock_id,lock_name,lock_type,lock_count,create_user,update_user) values(?,?,?,0,'sys','sys')");
				state.setString(1, key);
				state.setString(2, LOCK_NAME);
				state.setString(3, LOCK_TYPE);
				state.executeUpdate();
			}
		} catch (SQLException e) {
			throw new BizException("编译SQL出错，"+e.getMessage(),e);
		}

	}
	
	public static void unlock(String key){
		Connection conn = threadLocal.get();
		if(null == conn){
			logger.error("释放锁失败，锁不存在");
		} else {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				logger.error("关闭连接失败",e);
			}
			threadLocal.remove();
		}
	}
}
