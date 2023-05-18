package com.smartWorkers.gestionBudgets.entities;

import java.util.Date;

public class Notification {
  private String message;
  private Date date;
  private Categories category;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Categories getCategory() {
    return category;
  }

  public void setCategory(Categories category) {
    this.category = category;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
