package bjxg.test.object;
import java.io.Serializable;

/* 
 * @(#)Obj.java    Created on Oct 29, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Oct 29, 2009 4:57:27 PM $
 */
public class Obj implements Serializable {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4827922674588588120L;
    private String id = "id";
    private String name = "name";
    private String date = "20090408";
    private String date2 = "20090408";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

}
