package com.hondaamartha.car;

import com.hondaamartha.model.ServiceFee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Hatchback extends Category {
    @Override
    public double calculateFee(double kilometers) {
        return kilometers * 1.2;
    }

    @Override
    public ObservableList<ServiceFee> getServices(double kilometers) {
        ObservableList<ServiceFee> services = FXCollections.observableArrayList();

        if (kilometers >= 10000) {
            services.add(new ServiceFee("Machine Oil", 400000));
            services.add(new ServiceFee("Brake Fluid", 150000));
        }
        if (kilometers >= 40000) {
            services.add(new ServiceFee("Air Filter", 250000));
            services.add(new ServiceFee("Spark Plug", 200000));
        }
        if (kilometers >= 80000) {
            services.add(new ServiceFee("Transmission Fluid", 600000));
        }

        return services;
    }
}