package com.nefu.workmanage.component;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class EncryptorComponent {
    @Value("${my.secretkey}")
    private String secretkey;
    @Value("${my.salt}")
    private String salt;
    @Autowired
    private ObjectMapper mapper;

    //加密
    public String encrypt(Map payload){
        String json = null;
        try {
            json = mapper.writeValueAsString(payload);
            return Encryptors.text(secretkey, salt).encrypt(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密
    public Map<String,Object> decrypt(String encryptString){
        String json = Encryptors.text(secretkey, salt).decrypt(encryptString);
        try {
            return mapper.readValue(json, Map.class);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"未登录");
        }
    }
}
