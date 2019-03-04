package com.virex;

/*
 Частный случай документа - таблица эксель
 */
public class ExcelDocument extends Document {

    public ExcelDocument(String name, int size){
        super(name, size);
        this.type=DocumentType.EXCEL;
        this.printTimeOut=4;
    }
}
