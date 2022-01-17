package com.itrus.contract.service.impl;

import cn.com.itrus.atom.sign.api.fss.bean.DownloadResponse;
import cn.hutool.core.util.ObjectUtil;
import com.itrus.common.exception.MiniException;
import com.itrus.common.http.CommonRequest;
import com.itrus.common.request.atom.FssUploadRequest;
import com.itrus.contract.entity.ContractDocument;
import com.itrus.contract.entity.TemplateDocument;
import com.itrus.contract.service.FileMangerCommService;
import com.itrus.contract.tools.domain.api.HttpCode;
import com.itrus.contract.tools.enums.file.FileTypeEnum;
import com.itrus.contract.tools.enums.file.LastFileTypeEnum;
import com.itrus.contract.tools.file.FileTypeJudge;
import com.itrus.contract.tools.zip.ZipHelp;
import com.itrus.contract.tools.zip.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class FileMangerCommServiceImpl implements FileMangerCommService {

    @Resource
    private CommonRequest commonRequest;

    /**
     * 根据文件ID下载合同文档
     *
     * @param requestId
     * @param fileId
     * @return
     * @throws Exception
     */
    public DownloadResponse downloadFile(String requestId, Long fileId) throws Exception {
        DownloadResponse downloadResponse = null;
        if (StringUtils.isEmpty(requestId)) {
            downloadResponse = commonRequest.downloadFile(fileId);
        } else {
            downloadResponse = commonRequest.downloadFile(requestId, fileId);
        }
        return downloadResponse;
    }


    /**
     * 根据文档列表下载合同文档
     *
     * @param requestId
     * @param contractDocumentList
     * @return
     * @throws Exception
     */
    public byte[] downloadContractDocumentList(String requestId, List<ContractDocument> contractDocumentList, LastFileTypeEnum lastFileTypeEnum) throws Exception {
        List<ZipHelp> zipHelpList = Lists.newArrayList();
        String time = new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date());
        String fileExtName = lastFileTypeEnum.getName();
        for (ContractDocument contractDocument : contractDocumentList) {//多文件
            String fileName = contractDocument.getName() + "_" + contractDocument.getLastFileId() + "_" + time;
            Long fileId = contractDocument.getLastFileId();
            DownloadResponse downloadFile = null;
            if (ObjectUtil.isNotNull(requestId)) {
                downloadFile = commonRequest.downloadFile(requestId, fileId);
            } else {
                downloadFile = commonRequest.downloadFile(fileId);
            }

            ZipHelp zipHelp = new ZipHelp();
            zipHelp.setFileName(fileName + fileExtName);
            zipHelp.setFileContent(downloadFile.getFileBytes());
            zipHelpList.add(zipHelp);
        }
        byte[] zipBytes = ZipUtil.compress(zipHelpList);
        return zipBytes;
    }

    /**
     * 根据模板文档列表下载模板合同文档
     * @param templateDocumentList
     * @return
     * @throws Exception
     */
    public byte [] downloadTemplateDocumentList(String requestId, List<TemplateDocument> templateDocumentList, LastFileTypeEnum lastFileTypeEnum) throws Exception{
        List<ZipHelp> zipHelpList = Lists.newArrayList();
        String time = new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date());
        String fileExtName = lastFileTypeEnum == LastFileTypeEnum.OFD ? ".ofd" : ".pdf";
        for (TemplateDocument templateDocument : templateDocumentList) {//多文件
            String fileName = templateDocument.getName() + "_" + templateDocument.getLastFileId() + "_" + time;
            Long fileId = templateDocument.getLastFileId();
            DownloadResponse downloadFile = null;
            if (ObjectUtil.isNotNull(requestId)) {
                downloadFile = commonRequest.downloadFile(requestId, fileId);
            } else {
                downloadFile = commonRequest.downloadFile(fileId);
            }

            ZipHelp zipHelp = new ZipHelp();
            zipHelp.setFileName(fileName + fileExtName);
            zipHelp.setFileContent(downloadFile.getFileBytes());
            zipHelpList.add(zipHelp);
        }
        byte[] zipBytes = ZipUtil.compress(zipHelpList);
        return zipBytes;
    }


    /**
     * 上传文件
     *
     * @param fileBytes
     * @param fileName
     * @param bizType
     * @param encryptionType
     * @return
     * @throws Exception
     */
    public Long uploadFile(byte[] fileBytes, String fileName, String bizType, Integer encryptionType) throws Exception {
        FssUploadRequest fssUploadRequest = new FssUploadRequest(fileBytes, fileName, bizType, encryptionType);
        return commonRequest.uploadFile(fssUploadRequest);
    }

    /**
     * 根据文件ID下载合同文档
     * @param requestId
     * @param fileId
     * @param fileId
     * @return lastFileTypeEnum
     * @return httpServletResponse
     * @throws Exception
     */
    public HttpServletResponse downloadFileResponse(String requestId, Long fileId,HttpServletResponse httpServletResponse) throws Exception {
        DownloadResponse downloadResponse = downloadFile(requestId,fileId);
        byte[] fileBytes = downloadResponse.getFileBytes();
        FileTypeEnum fileTypeEnum = FileTypeJudge.getFileType(fileBytes);
        String fileTypeName = "octet-stream";
        if (ObjectUtils.isNotEmpty(fileTypeEnum)) {
            fileTypeName = fileTypeEnum.getName();
        }
        String fileName = downloadResponse.getFileName();
        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(fileBytes))) {
            httpServletResponse.setHeader("Content-Type", "application/" + fileTypeName);
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            httpServletResponse.setCharacterEncoding("UTF-8");
            IOUtils.copyLarge(bufferedInputStream, httpServletResponse.getOutputStream());
        }
        return httpServletResponse;
    }

    /**
     * 根据文件内容组装
     * @param fileBytes
     * @param fileName
     * @param httpServletResponse
     * @return lastFileTypeEnum
     * @return httpServletResponse
     * @throws Exception
     */
    public HttpServletResponse downloadFileResponse(byte[] fileBytes, String fileName,FileTypeEnum fileTypeEnum,HttpServletResponse httpServletResponse) throws Exception{
        String fileTypeName = "octet-stream";
        if (ObjectUtils.isNotEmpty(fileTypeEnum)) {
            fileTypeName = fileTypeEnum.getName();
        }
        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(fileBytes))) {
            httpServletResponse.setContentType("application/" + fileTypeName);
            if (StringUtils.isNotBlank(fileName)) {
                httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            }
            httpServletResponse.setCharacterEncoding("UTF-8");
            IOUtils.copyLarge(bufferedInputStream, httpServletResponse.getOutputStream());
        }
        return httpServletResponse;
    }

    /**
     * 根据文件流组装
     * @param inputStream
     * @param httpServletResponse
     * @return lastFileTypeEnum
     * @return httpServletResponse
     * @throws Exception
     */
    public HttpServletResponse downloadFileResponse(InputStream inputStream, String fileName, FileTypeEnum fileTypeEnum, HttpServletResponse httpServletResponse) throws Exception{
        String fileTypeName = "octet-stream";
        if (ObjectUtils.isNotEmpty(fileTypeEnum)) {
            fileTypeName = fileTypeEnum.getName();
        }

        httpServletResponse.setHeader("Content-Type", "application/" + fileTypeName);
        if (StringUtils.isNotBlank(fileName)) {
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        }
        httpServletResponse.setCharacterEncoding("UTF-8");
        IOUtils.copyLarge(inputStream, httpServletResponse.getOutputStream());
        return httpServletResponse;
    }


    /**
     * 根据文件ID获取文件BASE64
     *
     * @param requestId
     * @param fileId
     * @return
     * @throws Exception
     */
    public String downloadFileBase64(String requestId,Long fileId) throws Exception {
        DownloadResponse downloadResponse = downloadFile(requestId,fileId);
        return Base64Utils.encodeToString(downloadResponse.getFileBytes());
    }

    /**
     * 根据文件ID获取文件字节
     * @param requestId
     * @param fileId
     * @return
     * @throws Exception
     */
    public byte [] downloadFileBytes(String requestId,Long fileId) throws Exception{
        DownloadResponse downloadResponse = downloadFile(requestId,fileId);
        return downloadResponse.getFileBytes();
    }

    /**
     * 根据文件内容获取最终文件类型
     * @param base64
     * @return
     * @throws Exception
     */
    public LastFileTypeEnum getLastFileTypeEnumByBase64(String base64){
        LastFileTypeEnum lastFileTypeEnum = null;
        byte [] fileBytes = FileTypeJudge.convertBase64(base64,true);
        FileTypeEnum fileTypeEnum = FileTypeJudge.getFileType(fileBytes);
        if (ObjectUtils.isEmpty(fileTypeEnum)){
            return lastFileTypeEnum;
        }
        lastFileTypeEnum = LastFileTypeEnum.getLastFileType(fileTypeEnum.getType(),null);
        return lastFileTypeEnum;
    }
}
