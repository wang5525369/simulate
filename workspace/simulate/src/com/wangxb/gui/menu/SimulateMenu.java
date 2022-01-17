package com.wangxb.gui.menu;

import com.wangxb.gui.listener.BaseActionListener;
import com.wangxb.simulate.BaseSimulate;

import java.awt.*;

public class SimulateMenu {

    BaseActionListener baseActionListener = null;
    private MenuBar menuBar = new MenuBar();

    public SimulateMenu(BaseSimulate baseSimulate){
        baseActionListener = new BaseActionListener(baseSimulate);
    }

    public MenuBar initMenu(){
        Menu systemMenu = getSystemMenu();
        menuBar.add(systemMenu);
        Menu romMenu = getRomMenu();
        menuBar.add(romMenu);
        return menuBar;
    }

    Menu getSystemMenu(){
        Menu menu = new Menu();
        menu.setLabel("系统");
        menu.setName("systemMenu");
        MenuItem menuItem = new MenuItem();
        menuItem.setLabel("退出");
        menuItem.setActionCommand("exit");
        menuItem.addActionListener(baseActionListener);
        menu.add(menuItem);
        return menu;
    }

    Menu getRomMenu(){
        Menu menu = new Menu();
        menu.setLabel("Rom");
        menu.setName("romMenu");
        MenuItem menuItem = new MenuItem();
        menuItem.setLabel("打开文件");
        menuItem.setActionCommand("openRom");
        menuItem.addActionListener(baseActionListener);
        menu.add(menuItem);
        return menu;
    }
}
