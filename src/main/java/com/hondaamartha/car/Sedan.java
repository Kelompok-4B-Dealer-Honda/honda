package com.hondaamartha.car;

import com.hondaamartha.model.ServiceFee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Sedan extends Category {

    @Override
    public double calculateFee(double kilometers) {
        return kilometers * 0.5;
    }

    @Override
    public ObservableList<ServiceFee> getServices(double kilometers) {
        ObservableList<ServiceFee> services = FXCollections.observableArrayList();

        if (kilometers >= 10000) {
            services.add(new ServiceFee("Machine Oil", 500000));
            services.add(new ServiceFee("Brake Grease", 200000));
        }
        if (kilometers >= 40000) {
            services.add(new ServiceFee("Air Filter", 300000));
            services.add(new ServiceFee("Spark Plug", 250000));
        }
        if (kilometers >= 80000) {
            services.add(new ServiceFee("Timing Belt", 800000));
        }

        return services;
    }
}
