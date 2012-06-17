/* 
 * @(#)JavaBase.java    Created on 2011-6-15
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: 2011-6-15 上午11:31:27 $
 */
public class JavaBase {
    public static void main(String[] args) {
        for (int n = 0; n < 2; n++) {
            switch (n) {
            case 0:
                System.out.println("s" + n);
                break;
            case 1:
                System.out.println("s" + n);
                break;
            default:

            }
            System.out.println("for" + n);
        }
    }
}
