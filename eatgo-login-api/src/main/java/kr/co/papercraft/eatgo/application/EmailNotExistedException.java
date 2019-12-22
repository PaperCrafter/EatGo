package kr.co.papercraft.eatgo.application;

public class EmailNotExistedException extends RuntimeException{
    EmailNotExistedException(String email){
        super("email is not registered");
    }
}
