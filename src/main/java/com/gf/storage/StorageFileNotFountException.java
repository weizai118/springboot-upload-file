package com.gf.storage;

/**
 * <p>Title: StorageFileNotFountException</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author guofu
 * @version 1.0
 * @date 2018-04-10 13:15
 */
public class StorageFileNotFountException extends StorageException{

    public StorageFileNotFountException(String message){
        super(message);
    }

    public StorageFileNotFountException(String message , Throwable cause){
        super(message , cause);
    }


}
