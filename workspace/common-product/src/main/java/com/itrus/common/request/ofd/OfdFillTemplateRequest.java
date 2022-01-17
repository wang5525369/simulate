package com.itrus.common.request.ofd;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class OfdFillTemplateRequest {
    /**
     * 必选	参数类型，文本：text、图片：pic
     */
    String type;
    /**
     * 必选	参数值，参数类型是text时为文本，是pic时为图片的Base64编码
     */
    String value;
    /**
     * 必选	页码
     */
    Integer pageNum;
    /**
     * 可选	文字大小；如果等于null，默认为12；
     */
    Integer fontSize;
    /**
     * 可选	文字颜色，#rrggbb
     */
    String fontColor;
    /**
     * 可选	字体类型：支持SimSun SimHei MSYaHei KaiTi FangSong TimesNewRoman
     */
    String fontType;
    /**
     * 必选	文本域宽度，单位:mm
     */
    Integer textareaWidth;
    /**
     * 必选	文本域高度，单位:mm
     */
    Integer textareaHeight;
    /**
     * 可选	文本水平对齐方式 0:左对齐、1:居中对齐、2:右对齐，默认:0 仅文本域支持
     */
    Integer alignment;
    /**
     * 必选	填充位置的x坐标 单位mm
     */
    Integer textareaX;
    /**
     * 必选	填充位置的y坐标 单位mm
     */
    Integer textareaY;
}
