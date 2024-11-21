package com.hondaamartha.car;

import com.hondaamartha.model.ServiceFee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Category {
    public abstract double calculateFee(double kilometers);

    public ObservableList<ServiceFee> getServices(double kilometers) {
        return FXCollections.observableArrayList();
    }
}
