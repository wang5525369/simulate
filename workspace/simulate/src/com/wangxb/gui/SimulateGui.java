package com.wangxb.gui;

import com.wangxb.gui.menu.SimulateMenu;
import com.wangxb.gui.plot.SimulatePlot;
import com.wangxb.simulate.BaseSimulate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SimulateGui extends JFrame {

    int screenWidth;
    int screenHeight;
    Color [][] screenColor;
    int defaultSpace = 10;
    SimulatePlot simulatePlot;
    BufferedImage screenBufferedImage = null;
    BaseSimulate baseSimulate = null;

    public SimulateGui(BaseSimulate baseSimulate){
        super();
        this.baseSimulate = baseSimulate;
        baseSimulate.setSimulateGui(this);
        this.screenWidth = baseSimulate.getWidth() * defaultSpace;
        this.screenHeight = baseSimulate.getHeight() * defaultSpace;
        initMenu();
        initialize();
    }

    void initMenu(){
        SimulateMenu simulateMenu = new SimulateMenu(baseSimulate);
        MenuBar menuBar = simulateMenu.initMenu();
        this.setMenuBar(menuBar);
    }


    public synchronized BufferedImage getScreenBufferedImage(){
        if (screenBufferedImage == null) {
            screenBufferedImage = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D gp2d = (Graphics2D) screenBufferedImage.getGraphics();
            gp2d.setBackground(Color.black);
            gp2d.fillRect(0,0,screenWidth,screenHeight);
            gp2d.dispose();
        }
        return screenBufferedImage;
    }

    private synchronized void  setScreenBufferedImage(BufferedImage bufferedImage){
        Graphics2D gp2d = (Graphics2D) screenBufferedImage.getGraphics();
        gp2d.drawImage(bufferedImage,0,0,screenWidth, screenHeight,null);
        gp2d.dispose();
    }

    public void drawScreen(BufferedImage bufferedImage) {
        setScreenBufferedImage(bufferedImage);
        simulatePlot.repaint();
    }

    private void initialize() {

        this.setSize(screenWidth, screenHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体关闭模式
        this.setTitle("模拟器");
        this.setLocationRelativeTo(null);//窗体居中
        this.setVisible(true);//设置窗体的可见性

        simulatePlot = new SimulatePlot(this);
        setContentPane(simulatePlot);
    }


}
