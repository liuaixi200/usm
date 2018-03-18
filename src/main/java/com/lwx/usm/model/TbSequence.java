package com.lwx.usm.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_sequence")
public class TbSequence implements Serializable {
    @Id
    private String name;

    /**
     * 序列当前值
     */
    @Column(name = "current_value")
    private Long currentValue;

    private Integer increment;

    /**
     * 当前日期
     */
    @Column(name = "curr_date")
    private Date currDate;

    private static final long serialVersionUID = 1L;

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取序列当前值
     *
     * @return current_value - 序列当前值
     */
    public Long getCurrentValue() {
        return currentValue;
    }

    /**
     * 设置序列当前值
     *
     * @param currentValue 序列当前值
     */
    public void setCurrentValue(Long currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * @return increment
     */
    public Integer getIncrement() {
        return increment;
    }

    /**
     * @param increment
     */
    public void setIncrement(Integer increment) {
        this.increment = increment;
    }

    /**
     * 获取当前日期
     *
     * @return curr_date - 当前日期
     */
    public Date getCurrDate() {
        return currDate;
    }

    /**
     * 设置当前日期
     *
     * @param currDate 当前日期
     */
    public void setCurrDate(Date currDate) {
        this.currDate = currDate;
    }
}