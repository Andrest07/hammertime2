package com.hammertime.hammertime2.domain.images;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Image {
    private @Id @GeneratedValue Long id;

    String directory;
    Long jobID;

    Image(){}

    public Image(String directory, long jobID){
        this.directory = directory;
        this.jobID = jobID;
    }

    // Accessors
    public Long getImageId() {return id;}
    public String getDirectory() {return directory;}
    public Long getJobId() {return jobID;}

    // Dupe Check
    public boolean equals(Image o) {
        if (this == o)
            return true;
        if (!(o instanceof Image))
            return false;
            Image image = (Image) o;
        return Objects.equals(this.id, image.id)
        && Objects.equals(this.directory, image.directory)
        && Objects.equals(this.jobID, image.jobID);
    }

    @Override
    public int hashCode() {return Objects.hash(this.id, this.directory, this.jobID);}

    @Override
    public String toString() {
        return "Image{" + "id=" + this.id + "\'"
        + ", directory='" + this.directory + "\'"
        + ", jobID='" + this.jobID + "}";
    }
}
