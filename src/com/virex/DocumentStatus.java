package com.virex;

/*
  Тип статуса документа
 */
public enum DocumentStatus {
    ADDED("Добавлено на печать"),
    PRINTING("В процессе печати"),
    PRINTED("Успешно распечатано");

    String description;
    DocumentStatus(String description){
        this.description=description;
    }
}
