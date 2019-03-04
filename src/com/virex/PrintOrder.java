package com.virex;

/*
  Тип сортировки
 */
public enum PrintOrder {

    //по порядку печати, по типу документов, по продолжительности печати, по размеру бумаги.
    UNDEFINED(""),
    INORDER("По порядку печати (по умолчанию)"),
    TYPE("По типу документов"),
    TIMEOUT("По продолжительности печати"),
    SIZE("По размеру бумаги");

    String description;
    PrintOrder(String description){
        this.description=description;
    }

    public static PrintOrder parseString(String value){
        for (PrintOrder item : PrintOrder.values()){
            if (item.name().equals(value.toUpperCase())){
                return item;
            }
        }
        return UNDEFINED;
    }

    public static String list(){
        String result="";
        for (PrintOrder item : PrintOrder.values()){
            if (!item.description.isEmpty())
                result +=String.format("\t%s - %s\n",item.name(), item.description);
        }
        return result;
    }
}
