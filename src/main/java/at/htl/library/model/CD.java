package at.htl.library.model;

import javax.persistence.*;

@Entity
public class CD extends Item {
    String composer;
    double runtime;

    //region constructors
    public CD(String name, double price,String genre ,String composer, double runtime) {
        super(name,genre ,price);
        this.composer = composer;
        this.runtime = runtime;
    }

    public CD() {
    }
    //endregion

    //region getter and setter
    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public double getRuntime() {
        return runtime;
    }

    public void setRuntime(double runtime) {
        this.runtime = runtime;
    }
    //endregion
}
