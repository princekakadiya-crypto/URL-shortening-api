package com.tss.URL_Shortening.cache;

import com.tss.URL_Shortening.entity.SystemConfig;
import com.tss.URL_Shortening.repository.SystemConfigRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SystemConfigCache {

    private final SystemConfigRepository repository;

    private Map<String, String> configs=new ConcurrentHashMap<>();

    @PostConstruct
    public void loadConfigs() {
        List<SystemConfig> configList=repository.findAll();
        for (SystemConfig config:configList){
            configs.put(config.getConfigKey(), config.getConfigValue());
        }
    }

    public String get(String key) {
        return configs.get(key);
    }

    public Integer getInt(String key) {
        return Integer.parseInt(configs.get(key));
    }

    public void update(String key, String value) {
        configs.put(key, value);
    }

}
