import UI.LoggingInWindow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] arg)
    {
        try {
            System.out.println("server connecting....");
            Socket clientSocket = new Socket("127.0.0.1",2530);//установление //соединения между локальной машиной и указанным портом узла сети
            System.out.println("connection established....");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));//создание//буферизированного символьного потока ввода
            ObjectOutputStream coos = new ObjectOutputStream(clientSocket.getOutputStream());//создание//потока вывода
            ObjectInputStream  cois = new ObjectInputStream(clientSocket.getInputStream());//создание//потока ввода
            LoggingInWindow LogInThroughWindow = new LoggingInWindow(coos,cois);
            //USE CASE #6 WACCguide ++
            //USE CASE #7 Запись в файл1. Запись компаний +-
            //USE CASE #8 Запись в файл2. юзеров +-
            //USE CASE #9 Записать рассчёты в файл3. +-
            //USE CASE #10 FinanceGuide ++

            //USE CASE #11 Диаграмма "Сколько у пользователя компаний" ++++++
            //USE CASE #12 Диаграмма "Даты создания компаний" ++
            //USE CASE #13 Диаграмма "EVA компаний" ++
            //USE CASE #NULL(14) Диаграмма "Стоимость компаний"--------------

            //USE CASE #14(15) Записать в файл4. EVA компаний ++
            //USE CASE #15(16) Записать в файл5. Стоимость компаний ++

            System.out.println("You exit from system. Goodbye");
        }catch(Exception e)	{
            e.printStackTrace();//выполнение метода исключения е
        }
    }

}