package com.itrus.common.request.ofd;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class OfdImageMarkRequest {
    /**
     * 二选一	OFD文档地址，如果 ofdURI和ofdData都传，则取ofdURI
     */
    String ofdURI;
    /**
     * 二选一	OFD文档的Base64字符串，如果 ofdURI和ofdData都传，则取ofdURI
     */
    String ofdData;
    /**
     * 可选	返回类型： base64 ：返回文档base64编码（默认） fss : 返回文档存储后的ID
     */
    String returnType;
    /**
     * 必选	水印图片的base64编码
     */
    String imageData;
    /**
     * 可选	签章页码范围，格式：“1~5,8,-3~-1”，说明如下： 0：所有页（默认） 1~5：第1页至第5页 -3~-1：倒数第3页至倒数第1页
     */
    String pages;
    /**
     * 可选	透明度；范围：0至1；0：透明，1：不透明；如果等于null，默认为1；如果小于0，默认为0；如果大于1，默认为1；
     */
    Float opacity;
    /**
     * 可选	旋转角度；正数：逆时针旋转，负数：顺时针旋转；如果等于null，默认为0；
     */
    Float rotation;
    /**
     * 可选	列数，默认1列 水印内容展示的列数
     */
    Integer column;
    /**
     * 可选	行数，默认1行 水印内容展示的行数
     */
    Integer row;
    /**
     * 可选	x-偏移 默认10mm
     */
    Integer offsetX;
    /**
     * 可选	y-偏移 默认10mm
     */
    Integer offsetY;
}
