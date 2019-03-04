package com.virex;

import java.util.*;

/*
  Класс - диспетчер очереди печати
 */
public class MockPrinterSpooler {

    //список задач
    private ArrayList<Shedule> list = new ArrayList<>();
    Timer timer = new Timer(true);
    TimerTask timerTask = new ShedulerTimerTask();

    //декоратор для документа, подсчитывающий количество пройденного времени для задачи
    class Shedule{
        IDocument document;
        boolean isActive=false;
        boolean isFinished=false;
        int timeLast=0;

        Shedule(IDocument document){
            this.document=document;
        }
    }

    //класс - таймер
    class ShedulerTimerTask extends TimerTask{
        @Override
        public void run() {
            //увеличиваем счетчик
            for (Shedule shedule : list){
                if (shedule.isActive)
                    shedule.timeLast++;
            }

            //помечаем следующую задачу как активную
            setNextActive();
        }

        //помечаем следующую задачу как активную
        private void setNextActive(){
            int countRunning=0;

            //если время истекло - помечаем задачу выполненной
            //заодно подсчитываем количество запущенных задач
            for (Shedule shedule : list){
                if (shedule.isActive)
                    if (shedule.timeLast>=shedule.document.getPrintTimeOut()) {
                        shedule.isActive=false;
                        shedule.isFinished=true;
                        shedule.document.setStatus(DocumentStatus.PRINTED);
                    }
                if (shedule.isActive) countRunning++;
            }

            //если нет ни одной запущенной задачи - запускаем первую неактивную и не завершенную
            if (countRunning==0)
            for (Shedule shedule : list){
                if (!shedule.isFinished && !shedule.isActive) {
                    shedule.isActive=true;
                    shedule.document.setStatus(DocumentStatus.PRINTING);
                    break;
                }
            }
        }
    }

    //конструктор диспетчера
    public MockPrinterSpooler(){
        //запускаем каждую секунду
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }


    //добавляем в очередь печати
    public void add(IDocument document){
        if (document!=null)
            list.add(new Shedule(document));
    }

    //отменяем печать последнего добавленного не распечатанного документа
    public void cancel(){
        if (list.size()==0) return;
        IDocument lastDocument=list.get(list.size()-1).document;
        if (lastDocument!=null)
            if (lastDocument.getStatus()==DocumentStatus.ADDED)
                list.remove(list.size()-1);
    }

    public void stop(){
        timer.cancel();
    }

    //отображаем распечатанные документы
    public String[] showPrinted(PrintOrder printorder){
        ArrayList<Shedule> sorted = new ArrayList<>();
        for (Shedule shedule: list){
            if (shedule.document.getStatus()== DocumentStatus.PRINTED)
                sorted.add(shedule);
        }
        //сортируем
        sort(sorted,printorder);
        String[] result=new String[sorted.size()];
        for (int i=0; i<sorted.size(); i++){
            result[i]=sorted.get(i).document.toString();
        }
        return result;
    }

    //отображаем все документы (для наглядности)
    public String[] showAll(){
        String[] result=new String[list.size()];
        for (int i=0; i<list.size(); i++){
            result[i]=list.get(i).document.toString();
        }
        return result;
    }

    //среднее время печати всех распечатанных документов
    public int middlePrintTime(){
        int result=0;
        int count=0;
        for (Shedule shedule: list){
            if (shedule.document.getStatus()== DocumentStatus.PRINTED){
                result+=shedule.timeLast;
                count++;
            }
        }
        if (count==0)
            return 0;
        else
            return result/count;
    }

    //сортировка списка очереди печати
    private void sort(ArrayList<Shedule> list, PrintOrder printorder){
        switch(printorder){
            case INORDER:
                //у нас уже упорядоченная по времени добавления очередь
                break;
            case TYPE:
                Collections.sort(list, new Comparator<Shedule>() {
                    @Override
                    public int compare(Shedule o1, Shedule o2) {
                        return o1.document.getType().compareTo(o2.document.getType());
                    }
                });
                break;
            case TIMEOUT:
                Collections.sort(list, new Comparator<Shedule>() {
                    @Override
                    public int compare(Shedule o1, Shedule o2) {
                        return o1.document.getPrintTimeOut()-o2.document.getPrintTimeOut();
                    }
                });
            case SIZE:
                Collections.sort(list, new Comparator<Shedule>() {
                    @Override
                    public int compare(Shedule o1, Shedule o2) {
                        return o1.document.getSize()-o2.document.getSize();
                    }
                });
        }
    }

}
