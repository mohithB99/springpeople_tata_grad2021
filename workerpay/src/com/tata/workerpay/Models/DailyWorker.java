package com.tata.workerpay.Models;

public class DailyWorker extends Worker {
    private int hoursWorked=0;
    private int hourlyPay=10;

    public int getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(int hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public int pay() {
        return this.hoursWorked*this.hourlyPay;
    }
}
