package sanaebadi.ir.tandorosti.NewsModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sanaebadi on 3/31/18.
 */

public class Dairy {
  @SerializedName("id")
  private int id;

  @SerializedName("pro_image")
  private String pro_image;

  @SerializedName("pro_name")
  private String pro_name;

  @SerializedName("pro_price")
  private int pro_price;

  @SerializedName("pro_weight")
  private int pro_weight;

  @SerializedName("pro_desc")
  private String pro_desc;

  public Dairy(int id, String pro_image, String pro_name, int pro_price, int pro_weight, String pro_desc) {
    this.id = id;
    this.pro_image = pro_image;
    this.pro_name = pro_name;
    this.pro_price = pro_price;
    this.pro_weight = pro_weight;
    this.pro_desc = pro_desc;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPro_image() {
    return pro_image;
  }

  public void setPro_image(String pro_image) {
    this.pro_image = pro_image;
  }

  public String getPro_name() {
    return pro_name;
  }

  public void setPro_name(String pro_name) {
    this.pro_name = pro_name;
  }

  public int getPro_price() {
    return pro_price;
  }

  public void setPro_price(int pro_price) {
    this.pro_price = pro_price;
  }

  public int getPro_weight() {
    return pro_weight;
  }

  public void setPro_weight(int pro_weight) {
    this.pro_weight = pro_weight;
  }

  public String getPro_desc() {
    return pro_desc;
  }

  public void setPro_desc(String pro_desc) {
    this.pro_desc = pro_desc;
  }
}
