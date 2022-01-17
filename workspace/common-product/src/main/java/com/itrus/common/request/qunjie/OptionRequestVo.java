package com.itrus.common.request.qunjie;

import lombok.Data;

@Data
public class OptionRequestVo {
    String ocr;  //ocr开关配置项不填默认关闭，填写只能输入0和1
    String watermark;  //水印开关配置项 不填默认关闭，填写只能输入0和1
    String preventWaterMark; //水印事前开关配置项不填默认关闭，填写只能输入0和1
    String preventPower; //远程授权开关配置项 不填默认关闭，填写只能输入0和1
    String prevent; //二维码事前开关配置项 不填默认关闭，填写只能输入0和1
}
