package com.example.demo_web.exception;

public class InvalidContractException extends RuntimeException
{
    public InvalidContractException( String message )
    {
        super( message );
    }
}
