/* 
 * @(#)ZipFileUtils.java    Created on 2011-4-19
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.file;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: 2011-4-19 ����03:52:11 $
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class ZipFileUtils {
    public static void unZip(String zipFileName, String destDir) {
        File unzipFile = new File(zipFileName);
        if (destDir == null || destDir.trim().length() == 0) {
            destDir = unzipFile.getParent();
        }

        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(unzipFile, "GBK");
            Enumeration entries = zipFile.getEntries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                File destFile = new File(destDir, entry.getName());

                if (entry.isDirectory()) {// ��Ŀ¼���򴴽�֮
                    destFile.mkdirs();
                }
                else {// ���ļ�
                    // ���ָ���ļ��ĸ�Ŀ¼������,�򴴽�֮.
                    File parent = destFile.getParentFile();
                    if (parent != null && !parent.exists()) {
                        parent.mkdirs();
                    }

                    // ִ�н�ѹ
                    InputStream inputStream = zipFile.getInputStream(entry);
                    FileOutputStream fileOut = new FileOutputStream(destFile);
                    byte[] buf = new byte[1024];
                    int readedBytes;
                    while ((readedBytes = inputStream.read(buf)) > 0) {
                        fileOut.write(buf, 0, readedBytes);
                    }
                    fileOut.close();
                    inputStream.close();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (zipFile != null) {
                    zipFile.close();
                }
            }
            catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        String inFile = "D:/hosts.zip";
        String dir = "D:/";
        unZip(inFile, dir);
    }
}
