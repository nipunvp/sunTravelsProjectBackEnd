package com.example.demo_web.exception;

public class InvalidSearchException extends RuntimeException
{
    public InvalidSearchException( String message )
    {
        super( message );
    }
}
