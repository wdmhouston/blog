package com.deming.blog.ml;

/**
 * Fruit class for image classification (fruit)
 */
public class Fruit {
    //image name
    String image;
    //predict name
    String result;
    //probability
    String probability;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "image='" + image + '\'' +
                ", result='" + result + '\'' +
                ", probability='" + probability + '\'' +
                '}';
    }
}
