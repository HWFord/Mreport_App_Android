package fr.regionbretagne.hwf.mreportapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Rapport implements Serializable {
    private String report;
    private String title;
    private ArrayList<Dataviz> dataviz;

    public Rapport() {
    }

    public Rapport(String report, String title, ArrayList<Dataviz> dataviz) {
        this.report = report;
        this.title = title;
        this.dataviz = dataviz;
    }

    public String getId() {
        return report;
    }

    public void setId(String id) {
        this.report = report;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Dataviz> getDataviz() {
        return dataviz;
    }

    public void setDataviz(ArrayList<Dataviz> dataviz) {
        this.dataviz = dataviz;
    }

    @Override
    public String toString() {
        return "Rapport{" +
                "id='" + report + '\'' +
                ", title='" + title + '\'' +
                ", dataviz=" + dataviz.toString() +
                '}';
    }
}
