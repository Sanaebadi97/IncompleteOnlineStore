package sanaebadi.ir.tandorosti.model;

/**
 * Created by sanaebadi on 3/24/18.
 */

public class NavList {
  private int navTitle;
  private int navIcon;
  private int headProf;
  private int headName;

  public NavList(int navTitle, int navIcon, int headProf, int headName) {
    this.navTitle = navTitle;
    this.navIcon = navIcon;
    this.headProf = headProf;
    this.headName = headName;
  }

  public NavList(int navTitle, int navIcon){
    this.navTitle = navTitle;
    this.navIcon = navIcon;
  }

  public NavList(){

  }


  public int getNavTitle() {
    return navTitle;
  }

  public void setNavTitle(int navTitle) {
    this.navTitle = navTitle;
  }

  public int getNavIcon() {
    return navIcon;
  }

  public void setNavIcon(int navIcon) {
    this.navIcon = navIcon;
  }

  public int getHeadProf() {
    return headProf;
  }

  public void setHeadProf(int headProf) {
    this.headProf = headProf;
  }

  public int getHeadName() {
    return headName;
  }

  public void setHeadName(int headName) {
    this.headName = headName;
  }
}
