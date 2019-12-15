package kr.co.papercraft.eatgo.application;

public class EmailExistedException extends RuntimeException{
    EmailExistedException(String email){
        super("already registered email");
    }
}
