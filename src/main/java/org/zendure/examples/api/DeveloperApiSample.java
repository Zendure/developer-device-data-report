package org.zendure.examples.api;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;

public class DeveloperApiSample {

    public static void main(String[] args) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("snNumber", "VU5D99F74021B04");
        paramMap.put("account", "dev@zendure.com");
        String response = HttpUtil.createPost("https://app.zendure.tech/v2/developer/api/apply")
                .contentType("application/json")
                .body(JSONUtil.toJsonStr(paramMap)).execute().body();
        System.out.println("The Response Of Api:"+response);
    }
}
