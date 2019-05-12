package com.skbaby.orange.exception;

public class DaoException extends Exception {
    //无参构造方法
    public DaoException(){
        super();
    }

    //有参的构造方法
    public DaoException(String message){
        super(message);
    }

    // 用指定的详细信息和原因构造一个新的异常
    public DaoException(String message, Throwable cause){
        super(message,cause);
    }

    //用指定原因构造一个新的异常
    public DaoException(Throwable cause) {
        super(cause);
    }
}
