package com.virex;

/*
  Фабрика по созданию документов
 */

public class DocumentFactory {

    //список расришений файлов
    private static String[] word = {".DOC",".DOCX"};
    private static String[] excel = {".XLS"};
    private static String[] pdf = {".PDF"};

    public static IDocument parceArguments(String[] args) throws DocumentException{
        String filename;
        int size;

        try {
            filename = args[0];
        }catch(Exception e){
            throw new DocumentException("Не определено имя файла");
        }
        try {
            size= Integer.parseInt(args[1]);
        }catch(Exception e){
            throw new DocumentException("Не определен размер файла");
        }

        for (String item : word){
            if (filename.toUpperCase().contains(item.toUpperCase())) return new WordDocument(filename,size);
        }

        for (String item : excel){
            if (filename.toUpperCase().contains(item.toUpperCase())) return new ExcelDocument(filename,size);
        }

        for (String item : pdf){
            if (filename.toUpperCase().contains(item.toUpperCase())) return new PDFDocument(filename,size);
        }

        throw new DocumentException("Не удалось определить тип файла");
    }
}
