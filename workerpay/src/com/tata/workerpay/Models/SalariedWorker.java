package com.tata.workerpay.Models;

public class SalariedWorker extends Worker {
    private int fixedWage=0;

    public int getFixedWage() {
        return fixedWage;
    }

    public void setFixedWage(int fixedWage) {
        this.fixedWage = fixedWage;
    }

    @Override
    public int pay(){
        return fixedWage;
    }

}