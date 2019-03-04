package com.virex;

/*
  Исключение для команды
 */
public class CommandException extends Exception {
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
