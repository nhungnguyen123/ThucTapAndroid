package com.uiapp.thuctap.model;

import java.util.List;

/**
 * Created by hongnhung on 8/21/16.
 */
public class GardenClient {

    public String nameVegetable;

    public String description;
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int total = 1;


    public List<Garden> mListGarden;

    public String getNameVegetable() {
        return nameVegetable;
    }

    public void setNameVegetable(String nameVegetable) {
        this.nameVegetable = nameVegetable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Garden> getmListGarden() {
        return mListGarden;
    }

    public void setmListGarden(List<Garden> mListGarden) {
        this.mListGarden = mListGarden;
    }


}
