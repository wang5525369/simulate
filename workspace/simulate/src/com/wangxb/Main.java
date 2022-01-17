package com.wangxb;

import com.wangxb.gui.SimulateGui;
import com.wangxb.simulate.ChipSimulate;

public class Main {

    public static void main(String[] args) {
        ChipSimulate chipSimulate = new ChipSimulate();
        SimulateGui simulateGui = new SimulateGui(chipSimulate);
    }
}
