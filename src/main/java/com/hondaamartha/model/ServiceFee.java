package com.hondaamartha.model;

public class ServiceFee {
    private final String serviceName;
    private final double fee;

    public ServiceFee(String serviceName, double fee) {
        this.serviceName = serviceName;
        this.fee = fee;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getFee() {
        return fee;
    }
}