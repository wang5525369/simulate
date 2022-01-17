package com.wangxb.feign.conrtoller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.collection.Seq;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @PostMapping("testTuple")
    public String testTuple() throws Exception {
        Pair<Boolean,String> pair = new ImmutablePair (true,"");
        Triple<Boolean,String,String> triple = getTriple();

        return triple.getMiddle();
    }

    <T> Triple<Boolean,T,String> getTriple(){
        JSONObject jsonObject = new JSONObject();
        Triple<Boolean, JSONObject,String> triple = new ImmutableTriple(true,jsonObject,"");
        return (Triple<Boolean, T, String>) triple;
    }

}
