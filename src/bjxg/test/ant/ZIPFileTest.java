/* 
 * @(#)ZIPFileTest.java    Created on Jun 1, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.ant;

import org.junit.Test;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Jun 1, 2009 10:05:30 AM $
 */
public class ZIPFileTest {

    ZIPFile zip = new ZIPFile("test.zip");

    /**
     * Test method for {@link bjxg.test.ant.ZIPFile#zip(java.lang.String)}.
     */
    @Test
    public void testZip() {
        // zip.zip(".\\lib");
        ZIPFile.zip("t.zip", "lib/", "ant.jar");
    }
}
