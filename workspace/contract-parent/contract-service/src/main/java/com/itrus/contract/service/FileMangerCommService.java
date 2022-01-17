package com.itrus.contract.service;

import cn.com.itrus.atom.sign.api.fss.bean.DownloadResponse;
import com.itrus.contract.entity.ContractDocument;
import com.itrus.contract.entity.TemplateDocument;
import com.itrus.contract.tools.enums.file.FileTypeEnum;
import com.itrus.contract.tools.enums.file.LastFileTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;


public interface FileMangerCommService {
    /**
     * 根据文件ID下载合同文档
     * @param requestId
     * @param fileId
     * @return
     * @throws Exception
     */
    DownloadResponse downloadFile(String requestId,Long fileId) throws Exception;

    /**
     * 根据文档列表下载合同文档
     * @param contractDocumentList
     * @return
     * @throws Exception
     */
    byte [] downloadContractDocumentList(String requestId, List<ContractDocument> contractDocumentList, LastFileTypeEnum lastFileTypeEnum) throws Exception;

    /**
     * 根据模板文档列表下载模板合同文档
     * @param templateDocumentList
     * @return
     * @throws Exception
     */
    byte [] downloadTemplateDocumentList(String requestId, List<TemplateDocument> templateDocumentList, LastFileTypeEnum lastFileTypeEnum) throws Exception;


    /**
     * 上传文件
     * @param fileBytes
     * @param fileName
     * @param bizType
     * @param encryptionType
     * @return
     * @throws Exception
     */
    Long uploadFile(byte [] fileBytes,String fileName,String bizType, Integer encryptionType) throws Exception;

    /**
     * 根据文件ID下载合同文档
     * @param requestId
     * @param fileId
     * @param fileId
     * @return lastFileTypeEnum
     * @return httpServletResponse
     * @throws Exception
     */
    HttpServletResponse downloadFileResponse(String requestId, Long fileId,HttpServletResponse httpServletResponse) throws Exception;

    /**
     * 根据文件内容组装
     * @param fileBytes
     * @param httpServletResponse
     * @return lastFileTypeEnum
     * @return httpServletResponse
     * @throws Exception
     */
    HttpServletResponse downloadFileResponse(byte [] fileBytes, String fileName, FileTypeEnum fileTypeEnum,HttpServletResponse httpServletResponse) throws Exception;

    /**
     * 根据文件流组装
     * @param inputStream
     * @param httpServletResponse
     * @return lastFileTypeEnum
     * @return httpServletResponse
     * @throws Exception
     */
    HttpServletResponse downloadFileResponse(InputStream inputStream, String fileName, FileTypeEnum fileTypeEnum, HttpServletResponse httpServletResponse) throws Exception;


    /**
     * 根据文件ID获取文件BASE64
     *
     * @param requestId
     * @param fileId
     * @return
     * @throws Exception
     */
    String downloadFileBase64(String requestId,Long fileId) throws Exception;

    /**
     * 根据文件ID获取文件字节
     * @param requestId
     * @param fileId
     * @return
     * @throws Exception
     */
    byte [] downloadFileBytes(String requestId,Long fileId) throws Exception;

    /**
     * 根据文件内容获取最终文件类型
     * @param base64
     * @return
     * @throws Exception
     */
    LastFileTypeEnum getLastFileTypeEnumByBase64(String base64);

}
