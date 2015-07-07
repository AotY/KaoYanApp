package com.qtao.kaoyanknowledge.utils;

import com.kenumir.materialsettings.storage.StorageInterface;

import java.util.Map;

/**
 * Created by AotY on 2015/7/7.
 */
public class SaveSettingUtil extends StorageInterface {

    @Override
    public void save(String key, Boolean value) {

    }

    @Override
    public boolean load(String key, Boolean defaultValue) {
        return false;
    }

    @Override
    public void save(String key, String value) {

    }

    @Override
    public String load(String key, String defaultValue) {
        return null;
    }

    @Override
    public void save(String key, Integer value) {

    }

    @Override
    public Integer load(String key, Integer defaultValue) {
        return null;
    }

    @Override
    public void save(String key, Float value) {

    }

    @Override
    public Float load(String key, Float defaultValue) {
        return null;
    }

    @Override
    public void save(String key, Long value) {

    }

    @Override
    public Long load(String key, Long defaultValue) {
        return null;
    }

    @Override
    public Map<String, ?> getAll() {
        return null;
    }
}
