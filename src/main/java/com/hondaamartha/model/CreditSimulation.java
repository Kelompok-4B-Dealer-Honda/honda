package com.hondaamartha.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CreditSimulation {
    private final StringProperty description;
    private final StringProperty oneYear;
    private final StringProperty twoYear;
    private final StringProperty threeYear;
    private final StringProperty fourYear;
    private final StringProperty fiveYear;

    public CreditSimulation(String description, String oneYear, String twoYear, String threeYear, String fourYear, String fiveYear) {
        this.description = new SimpleStringProperty(description);
        this.oneYear = new SimpleStringProperty(oneYear);
        this.twoYear = new SimpleStringProperty(twoYear);
        this.threeYear = new SimpleStringProperty(threeYear);
        this.fourYear = new SimpleStringProperty(fourYear);
        this.fiveYear = new SimpleStringProperty(fiveYear);
    }

    public String getdescription() { return description.get(); }
    public StringProperty descriptionProperty() { return description; }

    public String getOneYear() { return oneYear.get(); }
    public StringProperty oneYearProperty() { return oneYear; }

    public String getTwoYear() { return twoYear.get(); }
    public StringProperty twoYearProperty() { return twoYear; }

    public String getThreeYear() { return threeYear.get(); }
    public StringProperty threeYearProperty() { return threeYear; }

    public String getFourYear() { return fourYear.get(); }
    public StringProperty fourYearProperty() { return fourYear; }

    public String getFiveYear() { return fiveYear.get(); }
    public StringProperty fiveYearProperty() { return fiveYear; }
}
