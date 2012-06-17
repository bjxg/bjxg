/* 
 * @(#)ZIPFile.java    Created on Jun 1, 2009
 * Copyright (c) 2009 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.ant;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: Jun 1, 2009 9:57:49 AM $
 */
public class ZIPFile {

    private File zipFile;

    public ZIPFile(String pathName) {
        zipFile = new File(pathName);
    }

    public static void zip(String zipFileName, String dirPath,
            String... fileNames) {

        File zipFile = new File(zipFileName);
        File srcdir = new File(dirPath);
        if (srcdir.exists()) {
            Project prj = new Project();
            Zip zip = new Zip();
            zip.setProject(prj);
            zip.setDestFile(zipFile);
            FileSet fileSet = new FileSet();
            fileSet.setProject(prj);
            fileSet.setDir(srcdir);
            if (fileNames != null && fileNames.length > 0) {
                for (String includes : fileNames) {
                    fileSet.setIncludes(includes);
                }
            }
            // fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹
            // eg:zip.setIncludes("*.java");
            // fileSet.setExcludes(...); 排除哪些文件或文件夹
            zip.addFileset(fileSet);
            zip.execute();
        }
    }

    public void zip(String srcPathName) {
        File srcdir = new File(srcPathName);
        if (!srcdir.exists()) {
            throw new RuntimeException(srcPathName + "不存在！");
        }

        Project prj = new Project();
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(zipFile);
        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        fileSet.setDir(srcdir);
        // fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹
        // eg:zip.setIncludes("*.java");
        // fileSet.setExcludes(...); 排除哪些文件或文件夹
        zip.addFileset(fileSet);

        zip.execute();
    }

    public static void zip(String zipFileName, int a, String... filePaths) {
        File zipFile = new File(zipFileName + ".zip");
        File dir = new File(".");
        Project prj = new Project();
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(zipFile);
        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        fileSet.setDir(dir);
        for (String filePath : filePaths) {
            fileSet.setIncludes(filePath); // 包括哪些文件或文件夹
        }

        zip.addFileset(fileSet);
        zip.execute();
    }
}
