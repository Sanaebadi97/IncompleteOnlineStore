package sanaebadi.ir.tandorosti.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sanaebadi on 3/22/18.
 */

public class BestPro {
  @SerializedName("id")
  private int id;

  @SerializedName("pro_image")
  private String pro_image;

  @SerializedName("pro_name")
  private String pro_name;

  @SerializedName("pro_view")
  private int pro_view;

  @SerializedName("pro_desc")
  private String pro_desc;

  @SerializedName("pro_price")
  private int pro_price;


  public BestPro(int id, String pro_image, String pro_name, int pro_view, String pro_desc, int pro_price) {
    this.id = id;
    this.pro_image = pro_image;
    this.pro_name = pro_name;
    this.pro_view = pro_view;
    this.pro_desc = pro_desc;
    this.pro_price = pro_price;
  }

  public BestPro() {

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

  public int getPro_view() {
    return pro_view;
  }

  public void setPro_view(int pro_view) {
    this.pro_view = pro_view;
  }

  public String getPro_desc() {
    return pro_desc;
  }

  public void setPro_desc(String pro_desc) {
    this.pro_desc = pro_desc;
  }

  public int getPro_price() {
    return pro_price;
  }

  public void setPro_price(int pro_price) {
    this.pro_price = pro_price;
  }
}