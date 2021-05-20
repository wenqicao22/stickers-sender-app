package edu.neu.cs5520.sticker.model;

import java.util.UUID;

public class Message {
  private String sender;
  private String receiver;
  private String iconName;
  private String datetime;
  private UUID Uuid;

  public Message(String sender, String receiver, String iconName, String datetime) {
    this.sender = sender;
    this.receiver = receiver;
    this.iconName = iconName;
    this.datetime = datetime;
    this.Uuid = UUID.randomUUID();
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  public String getIconName() {
    return iconName;
  }

  public void setIconName(String iconName) {
    this.iconName = iconName;
  }

  public String getDatetime() {
    return datetime;
  }

  public void setDatetime(String datetime) {
    this.datetime = datetime;
  }

  public String getUuid(){return this.Uuid.toString();}
}
