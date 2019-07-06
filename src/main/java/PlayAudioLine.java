import javax.sound.sampled.*;
import java.io.*;
import java.util.Scanner;

class PlayAudioLine {
    PlayAudioLine(String s){
        play(s);
    }
    public void play(String file){
        SourceDataLine line = null;
        AudioInputStream ais = null;
        byte[] b = new byte[2048]; // Буфер данных
        try{
            File f = new File(file);
// Создаем входной поток байтов из файла f
            ais = AudioSystem.getAudioInputStream(f);
// Извлекаем из потока информацию о способе записи звука
            AudioFormat af = ais.getFormat();
// Заносим эту информацию в объект info
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
// Проверяем, приемлем ли такой способ записи звука
            if (!AudioSystem.isLineSupported(info)){
                System.err.println("Линия не поддерживается");
                System.exit(0);
            }
// Получаем входную линию
            line = (SourceDataLine)AudioSystem.getLine(info);
// Открываем линию
            line.open(af);
// Начинаем проигрывание
            line.start(); // Ждем появления данных в буфере
            int num = 0;
// Раз за разом заполняем буфер
            while(( num = ais.read(b)) != -1) line.write(b, 0, num);
// "Сливаем" буфер, проигрывая остаток файла
            line.drain();
// Закрываем поток
            ais.close();
        } catch (Exception e) {
            System.err.println(e);
        }
// Останавливаем проигрывание
        line.stop();
// Закрываем линию
        line.close();
    }
    public static void main(String[] args){
        Thread thread = new Thread(new SecondThread());
        thread.start();
        String s = "C:\\Users\\YaroslavPC\\workspace2\\PetrolStation\\target\\classes\\scom.au";
        if (args.length > 0) s = args[0];
        new PlayAudioLine(s) ;


    }

    static class SecondThread implements Runnable{

        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while (!scanner.hasNextLine()){
            }
            System.exit(0);
        }
    }

    static class ThirdTread extends Thread{
        @Override
        public void run (){
            Scanner scanner = new Scanner(System.in);
            while (!scanner.hasNextLine()){
            }
            System.exit(0);
        }

    }
}