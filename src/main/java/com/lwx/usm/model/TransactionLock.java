package com.lwx.usm.model;

import java.util.Date;

public class TransactionLock {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction_lock.id
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    private String lockId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction_lock.lock_name
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    private String lockName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction_lock.lock_type
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    private String lockType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction_lock.lock_count
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    private Integer lockCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction_lock.create_user
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    private String createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction_lock.update_user
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    private String updateUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction_lock.create_time
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction_lock.update_time
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    private Date updateTime;


    public String getLockId() {
		return lockId;
	}

	public void setLockId(String lockId) {
		this.lockId = lockId;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction_lock.lock_name
     *
     * @return the value of transaction_lock.lock_name
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    public String getLockName() {
        return lockName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction_lock.lock_name
     *
     * @param lockName the value for transaction_lock.lock_name
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction_lock.lock_type
     *
     * @return the value of transaction_lock.lock_type
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    public String getLockType() {
        return lockType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction_lock.lock_type
     *
     * @param lockType the value for transaction_lock.lock_type
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    public void setLockType(String lockType) {
        this.lockType = lockType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction_lock.lock_count
     *
     * @return the value of transaction_lock.lock_count
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    public Integer getLockCount() {
        return lockCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction_lock.lock_count
     *
     * @param lockCount the value for transaction_lock.lock_count
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    public void setLockCount(Integer lockCount) {
        this.lockCount = lockCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction_lock.create_user
     *
     * @return the value of transaction_lock.create_user
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction_lock.create_user
     *
     * @param createUser the value for transaction_lock.create_user
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction_lock.update_user
     *
     * @return the value of transaction_lock.update_user
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction_lock.update_user
     *
     * @param updateUser the value for transaction_lock.update_user
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction_lock.create_time
     *
     * @return the value of transaction_lock.create_time
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction_lock.create_time
     *
     * @param createTime the value for transaction_lock.create_time
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction_lock.update_time
     *
     * @return the value of transaction_lock.update_time
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction_lock.update_time
     *
     * @param updateTime the value for transaction_lock.update_time
     *
     * @mbggenerated Wed Sep 06 10:04:28 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}