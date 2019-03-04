package com.virex;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static String HELLO_TEXT= "Привет! Эмулятор очереди печати MockPrinterSpooler готов к работе";
    private static String INPUT_HELP_TEXT= "Введите 'help' для вывода всех команд";

    //строка приветствия
    static void hello(){
        print(HELLO_TEXT);
        print(INPUT_HELP_TEXT);
    }

    //вывод списка команд
    static void help(){
        for(CommandType commandType : CommandType.values()){
            print(commandType.toString());
        }
    }

    //вывод текста
    static void print(String message){
        System.out.println(message);
    }

    static void print(String[] messages){
        for (String message: messages){
            System.out.println(message);
        }
    }

    //точка входа в программу
    public static void main(String[] args) {

        hello();//печатаем строку приветствия

        MockPrinterSpooler spooler = new MockPrinterSpooler();//спулер
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//чтение с клавиатуры
        try {
            //бесконечный цикл
            while (true) {

                //считываем строку
                String line = br.readLine();

                //пустая строка - игнорируем
                if (line.isEmpty()) continue;

                try {
                    //пытаемся определить команду
                    Command command = Command.parseCommand(line);
                    switch (command.getType()) {
                        case EXIT:
                            spooler.cancel();
                            return;
                        case HELP:
                            help();
                            break;
                        case LIST:
                            print(spooler.showPrinted(command.getPrintOrder()));
                            print(String.format("Cредняя продолжительность печати: %d",spooler.middlePrintTime()));
                            break;
                        case LISTALL:
                            print(spooler.showAll());
                            break;
                        case ADD:
                            //spooler.add(new WordDocument("file1.doc",30));
                            //spooler.add(new ExcelDocument("file3.xml",33));
                            //spooler.add(new PDFDocument("file2.pdf",15));
                            IDocument document = DocumentFactory.parceArguments(command.getArgs());
                            spooler.add(document);
                            break;
                        case CANCEL:
                            spooler.cancel();
                            break;
                        case STOP:
                            spooler.stop();
                            print(spooler.showPrinted(command.getPrintOrder()));
                            print(String.format("Cредняя продолжительность печати: %d",spooler.middlePrintTime()));
                            return; //выходим из программы
                    }
                } catch (DocumentException e) {
                    print(e.getMessage()); //ошибка при создании документа
                } catch (CommandException e) {
                    print(e.getMessage()); //ошибка при создании команды
                    print(INPUT_HELP_TEXT);//напоминаем что можно ввести команду help
                }
            }
        } catch (Exception e){
            print(e.toString());
        } finally {
            //br=null;
        }
    }
}
