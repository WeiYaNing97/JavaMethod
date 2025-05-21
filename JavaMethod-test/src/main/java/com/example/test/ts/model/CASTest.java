package com.example.test.ts.model;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.test.ts.model.CLGL_CARS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import springfox.documentation.spring.web.json.Json;

public class CASTest {
    public static void main(String[] args) {
        String str = "{\"ID\":3,\"CARNO\":\"鄂WFH533\",\"CARTYPE\":null,\"CARRYING\":7,\"CARS_SJID\":8,\"CHECKDATE\":null,\"REMARK\":null,\"CS\":null,\"NYLX\":null,\"CSJG\":null,\"PICTURES\":\"\",\"CARBH\":null,\"OILNO\":95,\"CQXZ\":2,\"YXRL\":65,\"CJH\":null,\"FDJH\":null,\"PFBZ\":null,\"GCFPJE\":null,\"GCFPH\":null,\"ZCSJ\":null,\"GZYJ\":null,\"GZBH\":null,\"ZJLY\":null,\"CLLY\":null,\"CQDW\":null,\"ETCID\":null,\"ISVALUE\":null,\"TJR\":\"孙伟\",\"TJSJ\":\"2025-04-02T12:36:38\",\"PRJID\":23,\"GSID\":null,\"CLPP\":\"丰田\",\"JYKID\":null,\"BXGS\":\"中国太平洋财产保险股份有限公司\",\"SYZT\":\"正常使用\",\"NEXTCHECKDATE\":null,\"PL\":null,\"CSLCBS\":46.1,\"DWTYPE\":2,\"DWID\":1,\"STIME\":null,\"ETIME\":null,\"SJID\":null,\"SJNAME\":\"彭国凡\",\"SJPHONE\":\"17671159787\",\"SCCCSJ\":null,\"SCCCSJ2\":null,\"STATESTR\":\"空闲\",\"YCCS\":null,\"LCS\":null,\"PCBID\":null,\"ETCNO\":null,\"JYKNO\":null,\"CLGL_CARS_JYF\":null,\"CLGL_CARS_PCB\":null,\"LOCATION\":\"{\\\"X\\\":112.24442,\\\"Y\\\":32.08036,\\\"H\\\":98.200,\\\"ONTIME\\\":\\\"2025-05-19T15:30:07\\\"}\",\"PEOPLEID\":\"undefined\"}";

        // 创建ObjectMapper实例
        ObjectMapper mapper = new ObjectMapper();
        
        // 先将字符串解析为JsonNode以处理嵌套的JSON
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(str);
            
            // 获取LOCATION字段的值并解析为单独的JsonNode
            String locationStr = rootNode.get("LOCATION").asText();
            // 处理LOCATION字段中的转义引号
            locationStr = locationStr.replace("\\\"", "\"");
            JsonNode locationNode = mapper.readTree(locationStr);
            
            // 创建一个新的ObjectNode来替换LOCATION字段
            ((ObjectNode) rootNode).replace("LOCATION", locationNode);
            
            // 将处理后的JsonNode转换为Java对象
            CLGL_CARS cars = mapper.treeToValue(rootNode, CLGL_CARS.class);
            System.out.println(cars);
            
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON解析失败", e);
        }
    }
}
