/* 
 * @(#)ObjectUtils.java    Created on 2004-10-13
 * Copyright (c) 2004 ZDSoft Networks, Inc. All rights reserved.
 * $Id: ObjectUtils.java 3 2008-10-24 10:33:16Z huangwj $
 */
package bjxg.test.object;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.fileupload.FileItem;

import net.zdsoft.keel.util.DateUtils;
import net.zdsoft.keel.util.Validators;

/**
 * 瀵硅薄宸ュ叿锟�
 * 
 * @author liangxiao
 * @version $Revision: 3 $, $Date: 2008-10-24 18:33:16 +0800 (鏄熸湡锟�, 24 鍗佹湀 2008) $
 */

public final class ObjectUtils {

    private static final BeanUtilsBean beanUtilsBean = new BeanUtilsBean(new ConvertUtilsBean() {
        @Override
        public Object convert(String value, Class clazz) {
            if (clazz.isEnum()) {
                return Enum.valueOf(clazz, value);
            }
            else if (clazz.equals(Date.class)) {
                Date date = null;
                if (Validators.isDate(value)) {
                    date = DateUtils.string2Date(value);
                }
                else if (Validators.isDateTime(value)) {
                    date = DateUtils.string2DateTime(value);
                }

                return date == null ? value : date;
            }
            else {
                return super.convert(value, clazz);
            }
        }

    }, null);

    private ObjectUtils() {
    }

    /**
     * 鍙栧緱瀵硅薄鎸囧畾鍚嶇О瀵瑰簲鐨勫睘鎬х殑锟�
     * 
     * @param object
     *            瀵硅薄
     * @param name
     *            灞烇拷?锟藉悕锟�
     * @return 灞烇拷?锟界殑锟�
     */
    public static Object getProperty(Object object, String name) {
        try {
            // return BeanUtils.getProperty(model, propertyName);
            return new PropertyUtilsBean().getProperty(object, name);
        }
        catch (Exception e) {
            throw new RuntimeException("Could not getProperty[" + name + "]", e);
        }
    }

    /**
     * 鍙栧緱瀵硅薄鎸囧畾宓屽鐨勫悕绉板搴旂殑灞烇拷?锟界殑鍊硷紝涓棿锟�.闅斿紑锟� 鍙互绫讳技school.name锛岃〃绀哄彇寰梥chool灞烇拷?锟界殑name灞烇拷?锟界殑锟�
     * 
     * @param object
     *            瀵硅薄
     * @param nestedName
     *            宓屽鐨勫睘鎬у悕锟�
     * @return 灞烇拷?锟界殑锟�
     */
    public static Object getNestedProperty(Object object, String nestedName) {
        if (nestedName.indexOf(".") == -1) {
            return getProperty(object, nestedName);
        }

        String[] names = nestedName.split("\\.");
        for (int i = 0; i < names.length; i++) {
            object = getProperty(object, names[i]);
        }
        return object;
    }

    /**
     * 鎸夌収鍙傛暟Map璁剧疆瀵硅薄鐨勫睘鎬х殑锟�
     * 
     * @param bean
     *            瀵硅薄
     * @param parameters
     *            鍙傛暟
     */
    public static void setProperties(Object bean, Map parameters) {
        // try {
        // BeanUtils.populate(bean, parameters);
        // }
        // catch (Exception e) {
        // throw new RuntimeException("Could not setProperties", e);
        // }

        // Do nothing unless both arguments have been specified
        if ((bean == null) || (parameters == null)) {
            return;
        }

        // Loop through the property name/value pairs to be set
        Iterator iterator = parameters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();

            // Identify the property name and value(s) to be assigned
            String name = (String) entry.getKey();
            if (name == null) {
                continue;
            }
            Object value = entry.getValue();

            // Perform the assignment for this property
            boolean isSuccess = true;
            try {
                BeanUtils.setProperty(bean, name, value);
            }
            catch (Exception e) {
                isSuccess = false;
            }

            if (!isSuccess) {
                if (value instanceof String) {// 瀛楃涓茶ˉ鍏呭锟�
                    String string = ((String) value).trim();
                    Date date = null;

                    // 锟�瑕佸垽鏂棩鏈燂拷?锟芥椂闂存槸鍚︾鍚堟牸锟�
                    if (Validators.isDate(string)) {
                        date = DateUtils.string2Date(string);
                    }
                    else if (Validators.isDateTime(string)) {
                        date = DateUtils.string2DateTime(string);
                    }

                    if (date == null) {
                        continue;
                    }

                    try {
                        BeanUtils.setProperty(bean, name, date);
                        isSuccess = true;
                    }
                    catch (Exception e) {
                    }

                    if (!isSuccess) {
                        try {
                            BeanUtils.setProperty(bean, name, new String[] { (String) value });
                        }
                        catch (Exception e) {
                        }
                    }
                }
                else {// 鏂囦欢琛ュ厖澶勭悊
                    try {
                        BeanUtils.setProperty(bean, name, new FileItem[] { (FileItem) value });
                    }
                    catch (Exception e) {
                    }
                }
            }
        }
    }

    /**
     * 璁剧疆瀵硅薄鎸囧畾鍚嶇О鐨勫睘鎬х殑锟�
     * 
     * @param object
     *            瀵硅薄
     * @param name
     *            鍙傛暟鍚嶇О
     * @param value
     *            鍙傛暟鐨勶拷??
     */
    public static void setProperty(Object object, String name, Object value) {
        try {
            beanUtilsBean.setProperty(object, name, value);
        }
        catch (Exception e) {
            throw new RuntimeException("Could not setProperty[" + name + "]", e);
        }
    }

    /**
     * 鏍规嵁绫诲垱寤哄锟�
     * 
     * @param clazz
     *            锟�
     * @return 瀵硅薄
     */
    public static Object createInstance(Class clazz) {
        try {
            return clazz.newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException("Could not create instance", e);
        }
    }

    /**
     * 鏍规嵁绫诲悕鍒涘缓瀵硅薄
     * 
     * @param className
     *            绫诲悕
     * @return 瀵硅薄
     */
    public static Object createInstance(String className) {
        try {
            return Class.forName(className).newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException("Could not create instance[" + className + "]", e);
        }
    }

    public static void main(String[] args) {
    }

}
