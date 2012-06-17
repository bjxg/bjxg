/* 
 * @(#)AdcSiServiceException.java    Created on 2007-11-1
 * Copyright (c) 2007 ZDSoft Networks, Inc. All rights reserved.
 * $Id: AdcSiServiceException.java 1843 2009-09-01 07:12:31Z huangwj $
 */
package bjxg.test.excep;


/**
 * 用于描述提供给ADC方调用的web服务接口出现的问题的异常类.
 * 
 * @author huangwj
 * @version $Revision: 1843 $, $Date: 2009-09-01 15:12:31 +0800 (星期二, 01 九月 2009) $
 */
public class AdcSiServiceException extends RuntimeException {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 161572381965757273L;

    public AdcSiServiceException() {
        super();
    }

    public AdcSiServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdcSiServiceException(String message) {
        super(message);
    }

    public AdcSiServiceException(Throwable cause) {
        super(cause);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(new AdcSiServiceException("错误").getMessage());
    }
}
