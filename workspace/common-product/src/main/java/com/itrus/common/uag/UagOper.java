package com.itrus.common.uag;

import cn.com.itrus.common.bean.Result;
import com.alibaba.fastjson.JSONObject;
import com.itrus.common.err.ErrInfo;
import com.itrus.common.exception.MiniException;
import com.itrus.common.http.UagRequest;
import com.itrus.common.result.uag.UagResult;
import com.itrus.common.result.uag.request.GetCompanyInfoRequest;
import com.itrus.common.result.uag.request.UserRegisterAndAuthRequest;
import com.itrus.common.result.uag.request.UserShowUserRequest;
import com.itrus.common.result.uag.response.uag.UagGetCompanyResp;
import com.itrus.common.result.uag.response.uag.UagUserInfoGetResp;
import com.itrus.common.result.uag.response.uag.UagUserPlainResp;
import com.itrus.common.result.uag.response.uag.UagUserRegisterResp;
import com.itrus.common.uag.UagUserApiRequest;
import com.itrus.uc.common.bean.open.resp.company.GetCompanyResp;
import com.itrus.uc.common.bean.open.resp.user.get.UserInfoGetResp;
import com.itrus.uc.common.bean.portal.resp.user.get.UserPlainResp;
import com.itrus.uc.common.bean.portal.resp.user.register.UserRegisterResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UagOper {

    @Autowired
    UagUserApiRequest uagUserApiRequest;

    @Autowired
    UagCompanyApiRequest uagCompanyApiRequest;

    /*
    创建用户
     */
    public Triple<Boolean,String,UagResult> createUser(UserRegisterAndAuthRequest userRegisterRequest, String errorInfo) throws Exception {
        // 调用uag注册
        boolean bRet = false;
        String userUuid = null;
        String methedName = getCurrentMethedName(1);
        Result<UserRegisterResp> result = uagUserApiRequest.register(userRegisterRequest);
        UagResult<UagUserRegisterResp> uagResult = convertResult(result,UagResult.class);
        UagUserRegisterResp uagUserRegisterResp = checkObject(uagResult,methedName,errorInfo);
        if (ObjectUtils.isNotEmpty(uagUserRegisterResp) == true){
            userUuid = uagUserRegisterResp.getUserUuid();
            bRet = true;
        }

        Triple triple = new ImmutableTriple<Boolean,String,UagResult>(bRet,userUuid,uagResult);
        return triple;
    }



    /*
    查找个人基本信息
     */
    public Triple<Boolean,UagUserInfoGetResp,UagResult> findUserBaseInfo(String userUuid, String errorInfo) throws Exception {
        boolean bRet = false;
        String methedName = getCurrentMethedName(1);
        UserShowUserRequest userShowUserRequest = new UserShowUserRequest(userUuid);
        Result<UserInfoGetResp> result = uagUserApiRequest.getUserAuthInfoByCondition(userShowUserRequest);
        UagResult<UagUserInfoGetResp> uagResult = convertResult(result,UagResult.class);
        UagUserInfoGetResp uagUserInfoGetResp = checkObject(uagResult,methedName,errorInfo);
        if (ObjectUtils.isNotEmpty(uagUserInfoGetResp) == true){
            bRet = true;
        }

        Triple triple = new ImmutableTriple<Boolean,UagUserInfoGetResp,UagResult>(bRet,uagUserInfoGetResp,uagResult);
        return triple;
    }

    /*
    查找个人详细信息
     */
    public Triple<Boolean,UagUserPlainResp,UagResult> findUserDetailInfo(String userUuid, String errorInfo) throws Exception {
        boolean bRet = false;
        String methedName = getCurrentMethedName(1);
        UserShowUserRequest userShowUserRequest = new UserShowUserRequest(userUuid);
        Result<UserPlainResp> result = uagUserApiRequest.getPlainUserInfo(userShowUserRequest);
        UagResult<UagUserPlainResp> uagResult = convertResult(result,UagResult.class);
        UagUserPlainResp uagUserPlainResp = checkObject(uagResult,methedName,errorInfo);
        if (ObjectUtils.isNotEmpty(uagUserPlainResp) == true){
            bRet = true;
        }

        Triple triple = new ImmutableTriple<Boolean,UagUserPlainResp,UagResult>(bRet,uagUserPlainResp,uagResult);
        return triple;
    }





    /*
    查找公司信息
     */
    public Triple<Boolean,UagGetCompanyResp,UagResult> findCompanyInfo(String companyUuid, String errorInfo) throws Exception {
        boolean bRet = false;
        String methedName = getCurrentMethedName(1);
        GetCompanyInfoRequest getCompanyInfoRequest = new GetCompanyInfoRequest(companyUuid);
        Result<GetCompanyResp> result = uagCompanyApiRequest.getCompanyInfo(getCompanyInfoRequest);
        UagResult<UagGetCompanyResp> uagResult = convertResult(result,UagResult.class);
        UagGetCompanyResp uagGetCompanyResp = checkObject(uagResult,methedName,errorInfo);
        if (ObjectUtils.isNotEmpty(uagGetCompanyResp) == true){
            bRet = true;
        }

        Triple triple = new ImmutableTriple<Boolean,UagGetCompanyResp,UagResult>(bRet,uagGetCompanyResp,uagResult);
        return triple;
    }

    <T> T checkObject(UagResult<T> uagResult,String methedName,String errorInfo){
        T t = null;
        if(uagResult.isNotOk() == true) {
            printErrorInfo(uagResult,methedName,errorInfo);
        }else{
            t = uagResult.getData();
            if (ObjectUtils.isEmpty(t) == true) {
                printErrorInfo(uagResult,methedName,errorInfo);
            }
        }
        return t;

    }

    <T> void printErrorInfo(UagResult<T> uagResult,String methedName,String errorInfo){
        if (ObjectUtils.isNotEmpty(errorInfo)) {
            log.error("调用{}失败，错误信息:{}",methedName,JSONObject.toJSONString(uagResult));
            throw new MiniException(errorInfo);
        }
    }

    String getCurrentMethedName(int index){
        String name = "";
        Exception e = new Exception();
        StackTraceElement [] arrayStackTraceElement = e.getStackTrace();
        if (index < arrayStackTraceElement.length) {
            name = arrayStackTraceElement[index].getMethodName();
        }
        return name;
    }

    <T,K> K convertResult(Result<T> result,Class<K> kClass) throws IllegalAccessException, InstantiationException {
        K k = kClass.newInstance();
        BeanUtils.copyProperties(result,k);
        return k;
    }
}
