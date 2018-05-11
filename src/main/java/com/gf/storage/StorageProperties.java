package com.gf.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Title: StorageProperties</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author guofu
 * @version 1.0
 * @date 2018-04-10 13:18
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "uplaod-dir";

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

}
