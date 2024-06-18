package rp.springboot.journalApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;
//To make it Generic we have to add <T> first
    public <T> T get(String key, Class<T> entity){
        try{
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(o.toString(), entity);
        }
        catch (Exception e){
            log.error(e.toString());
            return null;
        }
    }

    public void set (String key, Object o, Long expiryTime){
        try{
            ObjectMapper objectMapper = new ObjectMapper(); //As we are serializing to String
            String json = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, json, expiryTime, TimeUnit.SECONDS); //How much time data will be cached
         }
        catch (Exception e){
            log.error(e.toString());
        }
    }
}
