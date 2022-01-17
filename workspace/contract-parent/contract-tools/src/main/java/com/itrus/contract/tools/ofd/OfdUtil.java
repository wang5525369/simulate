package com.itrus.contract.tools.ofd;

import com.itrus.contract.tools.zip.ZipUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

public class OfdUtil {
    public static boolean checkOfd(byte[] fileBytes){
        boolean bCheck = false;
        try(ZipInputStream zipInputStream = ZipUtil.byteToZipInputStream(fileBytes)){
            bCheck = ZipUtil.findFile(zipInputStream,"OFD.xml");
        }catch (Exception e){
            e.printStackTrace();
            bCheck = false;
        }
        return bCheck;
    }
}
