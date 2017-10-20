package com.hsd.jenkins.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 
 * Class Description
 * @version Oct 12, 20176:07:38 PM
 * @author Ford.CHEN
 */
public class ResVo<T extends BaseVO> implements Serializable {
    
    private static final long serialVersionUID = 7024107287283823683L;
    
    private String retCode;
    private String retMsg;
    
    private T body;
    
    public ResVo() {
    }
    
    public ResVo(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }
    
    /**
     * 设置返回信息
     * 
     * @version 2016-4-29上午11:14:05
     * @author jerry.deng
     * @param retCode
     * @param retMsg
     */
    public void setRetInfo(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }
    
    
    /**
     * @return the retCode
     */
    public String getRetCode() {
        return retCode;
    }

    /**
     * @param retCode the retCode to set
     */
    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    /**
     * @return the retMsg
     */
    public String getRetMsg() {
        return retMsg;
    }

    /**
     * @param retMsg the retMsg to set
     */
    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    /**
     * @return the body
     */
    public T getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
