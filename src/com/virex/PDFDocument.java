package com.virex;

/*
 Частный случай документа - документ pdf
 */
public class PDFDocument extends Document{

    public PDFDocument(String name, int size){
        super(name, size);
        this.type=DocumentType.PDF;
        this.printTimeOut=10;
    }
}
