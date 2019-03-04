package com.virex;

import java.util.Arrays;

/*
  Команда
 */
public class Command{

    //тип команды
    private CommandType commandType;
    //аргументы
    private String[] args;

    //тип печати
    private PrintOrder printOrder=PrintOrder.UNDEFINED;

    public Command(CommandType commandType, String[] args){
        this.commandType=commandType;
        if (args!=null) this.args=args.clone();

        //ищем тип печати
        for (String line : args){
            if (PrintOrder.parseString(line)!=PrintOrder.UNDEFINED){
                this.printOrder=PrintOrder.parseString(line);
                break;
            }
        }
    }

    public PrintOrder getPrintOrder(){
        return this.printOrder;
    }

    public CommandType getType(){
        return this.commandType;
    }

    //получение списка аргументов
    public String[] getArgs(){
        return this.args;
    }

    //статический метод распознавания команды
    public static Command parseCommand(String text) throws CommandException{
        try{
            String[] array=text.split(" ");

            //первый аргумент - команда
            CommandType commandType = CommandType.valueOf(array[0].toUpperCase());
            //остальные - аргументы
            String[] args=Arrays.copyOfRange(array,1,array.length);

            return new Command(commandType,args);
        } catch (Exception e){
            throw new CommandException("Команда не распознана", e);
        }
    }
}