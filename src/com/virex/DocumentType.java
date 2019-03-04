package com.virex;

/*
  Тип документа
 */
public enum DocumentType {
    WORD("Microsoft Word"),
    PDF("Portable Document Format"),
    EXCEL("Microsoft Excel");

    String description;
    DocumentType(String description){
        this.description=description;
    }
}
