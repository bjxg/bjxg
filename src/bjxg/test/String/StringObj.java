/*
 * @(#)StringObj.java    Created on 2011-8-22
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.String;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2011-8-22 下午10:28:40 $
 */
public class StringObj {
    String id = "2";

    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Object o = new StringObj();
        o.getClass().getDeclaredMethods()[2].setAccessible(true);
        try {
            System.out.println(o.getClass().getDeclaredField("id").get(o));
        }
        catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
