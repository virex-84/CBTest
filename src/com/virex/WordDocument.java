package com.virex;

/*
 Частный случай документа - документ word
 */
public class WordDocument extends Document {

    public WordDocument(String name, int size){
        super(name, size);
        this.type=DocumentType.WORD;
        this.printTimeOut=2;
    }
}
