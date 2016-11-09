package com.example.siddhantverma.assignment41.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Siddhant Verma on 06-Nov-16.
 */
public class Listitem implements Serializable {

    private int imageId;
    private String maintext;
    private String secondary_text;
    private  Boolean temp=false;
    private UUID taskid;


    public Listitem()
    {
        taskid=UUID.randomUUID();

    }

    public Listitem(String maintext, String secondary_text) {
        this.maintext = maintext;
        this.secondary_text = secondary_text;
    }

    public Listitem(UUID taskid, String secondary_text, String maintext) {
        this.taskid = taskid;
        this.secondary_text = secondary_text;
        this.maintext = maintext;
    }

    public UUID getTaskid()
    {
        return taskid;
    }
    public UUID setID(UUID taskid)
    {
        this.taskid=taskid;
        return taskid;
    }




    public String getTitle()
    {
        return maintext;
    }

    public void setTitle(String title)
    {
        this.maintext = title;
    }

    public int getImgID()
    {
        return imageId;

    }

    public String getSecondary_text()
    {
        return secondary_text;
    }
    public void setSecondary_text(String secondary_text)
    {
        this.secondary_text= secondary_text;
    }

    public boolean isTemp() {
        return temp;
    }

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    public void setImgID(int imageId)
    {
        this.imageId = imageId;
    }
}