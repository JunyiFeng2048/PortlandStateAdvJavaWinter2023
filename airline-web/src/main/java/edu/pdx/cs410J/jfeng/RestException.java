package edu.pdx.cs410J.jfeng;

public class RestException {
    private final int httpStatusCode;
    private final String message;

    public RestException(int httpStatusCode, String message)
    {
        //super(message);
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public int getHttpStatusCode()
    {
        return this.httpStatusCode;
    }
}
