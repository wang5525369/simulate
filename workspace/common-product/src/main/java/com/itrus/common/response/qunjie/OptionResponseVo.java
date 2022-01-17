package com.itrus.common.response.qunjie;

import lombok.Data;

@Data
public class OptionResponseVo {
    String ocr;  //ocr开关配置项 0：关闭，1：开启
    String watermark;  //水印开关配置项 0：关闭，1：开启
    String preventWaterMark;  //水印事前开关配置项 0：关闭，1：开启
    String preventPower;  //远程授权开关配置项 0：关闭，1：开启
    String prevent;  //二维码事前开关配置项 0：关闭，1：开启
}
