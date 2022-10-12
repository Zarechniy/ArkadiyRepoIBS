package ru.ibs.framework;

import ru.ibs.framework.pages.BasePage;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Product extends BasePage {

    private String modelName;
    private int price;
    private int guarantee;
    private String description;
    public HashMap<String, Integer> modelPriceMap;

    public String getName() {
        return modelName;
    }

    public void setName(String name) {
        this.modelName = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, Integer> getModelPriceMap() {
        return modelPriceMap;
    }

    public void setModelPriceMap(HashMap<String, Integer> modelPriceMap) {
        this.modelPriceMap = modelPriceMap;
    }

}
