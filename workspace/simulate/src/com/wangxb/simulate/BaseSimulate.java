package com.wangxb.simulate;

import com.wangxb.gui.SimulateGui;

import java.io.IOException;

public interface BaseSimulate {
    void loadRomFile(String fileName) throws IOException;
    int getWidth();
    int getHeight();
    void setSimulateGui(SimulateGui simulateGui);
}
