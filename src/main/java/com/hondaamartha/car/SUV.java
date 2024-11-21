package com.hondaamartha.car;

import com.hondaamartha.model.ServiceFee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SUV extends Category {
    @Override
    public double calculateFee(double kilometers) {
        return kilometers * 0.7;
    }

    @Override
    public ObservableList<ServiceFee> getServices(double kilometers) {
        ObservableList<ServiceFee> services = FXCollections.observableArrayList();

        if (kilometers >= 10000) {
            services.add(new ServiceFee("Machine Oil", 600000));
            services.add(new ServiceFee("Brake Fluid", 250000));
        }
        if (kilometers >= 40000) {
            services.add(new ServiceFee("Air Filter", 350000));
            services.add(new ServiceFee("Spark Plug", 300000));
        }
        if (kilometers >= 80000) {
            services.add(new ServiceFee("Transmission Fluid", 1000000));
        }

        return services;
    }
}
