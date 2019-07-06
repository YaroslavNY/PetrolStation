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
        byte[] b = new byte[2048]; // ����� ������
        try{
            File f = new File(file);
// ������� ������� ����� ������ �� ����� f
            ais = AudioSystem.getAudioInputStream(f);
// ��������� �� ������ ���������� � ������� ������ �����
            AudioFormat af = ais.getFormat();
// ������� ��� ���������� � ������ info
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
// ���������, �������� �� ����� ������ ������ �����
            if (!AudioSystem.isLineSupported(info)){
                System.err.println("����� �� ��������������");
                System.exit(0);
            }
// �������� ������� �����
            line = (SourceDataLine)AudioSystem.getLine(info);
// ��������� �����
            line.open(af);
// �������� ������������
            line.start(); // ���� ��������� ������ � ������
            int num = 0;
// ��� �� ����� ��������� �����
            while(( num = ais.read(b)) != -1) line.write(b, 0, num);
// "�������" �����, ���������� ������� �����
            line.drain();
// ��������� �����
            ais.close();
        } catch (Exception e) {
            System.err.println(e);
        }
// ������������� ������������
        line.stop();
// ��������� �����
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