package com.wangxb.gui.plot;

import com.wangxb.gui.SimulateGui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SimulatePlot extends JPanel {

    private SimulateGui simulateGui;

    public SimulatePlot(SimulateGui simulateGui){
        this.simulateGui = simulateGui;
    }

    public void paint(Graphics gp){
        super.paint(gp);
        BufferedImage bufferedImage = simulateGui.getScreenBufferedImage();
        Graphics2D gp2d = (Graphics2D) gp;
        gp2d.drawImage(bufferedImage,0,0,null);
        gp2d.dispose();
    }

}
