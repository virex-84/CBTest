package com.virex;

/*
  Абстрактный класс - документ
  Запрещаем создание типа документа - для этого есть наследники
 */
public abstract class Document implements IDocument {
    String name;
    DocumentType type;
    DocumentStatus status=DocumentStatus.ADDED;
    int printTimeOut;
    int size;

    public Document(String name, int size){
        this.name=name;
        this.size=size;
    }

    @Override
    public DocumentStatus getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(DocumentStatus status) {
        this.status=status;
    }

    @Override
    public DocumentType getType() {
        return this.type;
    }

    @Override
    public int getPrintTimeOut() {
        return this.printTimeOut;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return String.format("%s, размер:%d, время печати: %d, тип: %s, состояние: %s",this.name, this.size, this.printTimeOut, this.type, this.status);
    }
}
