package com.example.safewallet.reports;
import com.aventstack.extentreports.ExtentReports;

public class ExtentFactory {
    public static ExtentReports getInsance() {
        ExtentReports extentReports = new ExtentReports();
        //Sumamos info al reporte que vamos a crear, con esto creamos una pequeña tabla de como ejecutamos las pruebas
        extentReports.setSystemInfo("Test type","LOGIN");
        extentReports.setSystemInfo("OS","Windows");
        return extentReports;


    }

}
