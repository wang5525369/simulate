package com.wangxb.simulate;

import com.wangxb.gui.SimulateGui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

import static java.lang.Thread.sleep;

public class ChipSimulate implements BaseSimulate {

    int width = 64;
    int height = 32;

    SimulateGui simulateGui;

    //总共35个指令，每个指令占用2字节
    byte [] opcode = new byte[2];

    //0x000-0x1FF - Chip 8解释器(包含用于显示的字体)
    //0x050-0x0A0 - 用于生成 4x5 像素的字体集合 (从’0’到’F’)
    //0x200-0xFFF - 游戏ROM 与工作RAM
    byte [] memory = new byte[1024*4]; //4096内存

    //16个寄存器，每个寄存器占用1个字节
    byte [] register = new byte[16];

    //索引寄存器
    byte I;
    //程序计数器
    int pc;

    //屏幕显示，不是黑就是白
    byte [][] gfx = new byte[width][height];

    //屏幕刷新时间
    int delay_timer;

    //声音播放时间
    int sound_timer;

    //调用栈
    byte [] stack = new byte[16];
    //栈顶指针
    byte sp;
    //按键值
    byte [] key  = new byte[16];

    public void init(){
        ChipSimulateRunable chipSimulateRunable = new ChipSimulateRunable(this);
        initData();
        Thread thread=new Thread(chipSimulateRunable);
        thread.start();
    }

    void initData(){
        for(int i=0;i<opcode.length;i++){
            opcode[i] = 0;
        }

        for(int i=0;i<memory.length;i++){
            memory[i] = 0;
        }

        for(int i=0;i<register.length;i++){
            register[i] = 0;
        }

        I = 0;
        pc = 0x200;

        for(int i=0;i<stack.length;i++){
            stack[i] = 0;
        }

        sp = 0;

        for(int i=0;i<key.length;i++){
            key[i] = 0;
        }

        for(int i=0;i<gfx.length;i++){
            for(int j=0;j<gfx[0].length;j++) {
                gfx[i][j] = 0;
            }
        }

    }

    void run() throws InterruptedException {
        boolean draw = false;
        for(;;){
            if (draw == true) {
                BufferedImage bufferedImage = getScreenBufferedImage();
                this.simulateGui.drawScreen(bufferedImage);
            }
            Thread.sleep(1000);
        }
    }

    BufferedImage getScreenBufferedImage() {
        BufferedImage screenBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gp2d = (Graphics2D) screenBufferedImage.getGraphics();
        for(int i= 0;i<width;i++){
            for(int j = 0;j<height;j++){
                Color color = getScreenColor(gfx[i][j]);
                gp2d.setBackground(color);
                gp2d.setColor(color);
                gp2d.fillRect(i, j, 1, 1);
            }
        }
        gp2d.dispose();
        return screenBufferedImage;
    }

    Color getScreenColor(byte b){
        Color color;
        if (b == 0){
            color = Color.black;
        }else{
            color = Color.white;
        }
        return color;
    }

    Color randomColor(int index){
        Random random = new Random(index);
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        Color color = new Color(r,g,b);
        return color;
    }

    //重置
    void reset(){
        initData();
        BufferedImage bufferedImage = getScreenBufferedImage();
        this.simulateGui.drawScreen(bufferedImage);
    }

    //加载文件
    public void loadRomFile(String fileName) throws IOException {
        reset();

        FileInputStream fileInputStream = new FileInputStream(fileName);
        int fileLen = fileInputStream.available();
        byte [] fileBytes = new byte[fileLen];
        int read = 0;
        int start = 0;
        do {
            start = start + read;
            fileLen = fileLen - start;
            read = fileInputStream.read(fileBytes,start,fileLen);
        }while (read != -1);

        loadRom(fileBytes);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    void loadRom(byte [] fileBytes){
        int len = fileBytes.length;
        for(int i=0; i<len;i++){
            memory[i] = fileBytes[i];
        }
    }

    class ChipSimulateRunable implements Runnable{
        ChipSimulate chipSimulate;
        public ChipSimulateRunable(ChipSimulate chipSimulate){
            this.chipSimulate = chipSimulate;
        }

        public void run(){
            while(true){
                try {
                    chipSimulate.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setSimulateGui(SimulateGui simulateGui){
        this.simulateGui = simulateGui;
    }
}
