package com.wangxb.gui.listener;

import com.wangxb.simulate.BaseSimulate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class BaseActionListener implements ActionListener {

    private BaseSimulate baseSimulate;

    public BaseActionListener(BaseSimulate baseSimulate){
        this.baseSimulate = baseSimulate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("exit".equals(command)) {
            System.exit(0);
        }else if ("openRom".equals(command)){
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int chooserStatus = jFileChooser.showOpenDialog(null);
            if(chooserStatus == 0){
                File file = jFileChooser.getSelectedFile();
                try {
                    baseSimulate.loadRomFile(file.getName());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
