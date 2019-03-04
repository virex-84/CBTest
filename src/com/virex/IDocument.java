package com.virex;

/*
 Интерфейс документа с обобщенными методами
 */
public interface IDocument {
    DocumentStatus getStatus();
    DocumentType getType();
    int getPrintTimeOut();
    int getSize();

    void setStatus(DocumentStatus status);
    String toString();
}
