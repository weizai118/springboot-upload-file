package com.gf.storage;

/**
 * <p>Title: StorageException</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author guofu
 * @version 1.0
 * @date 2018-04-10 13:12
 */
public class StorageException extends RuntimeException{

    public StorageException(String message){
        super(message);
    }

    public StorageException(String message , Throwable casuse){
        super(message , casuse);
    }

}
