package edu.neu.cs5520.sticker.repository;

import edu.neu.cs5520.sticker.model.Notification;

public interface NotificationRepository {
  void sendNotification(Notification notification);
}
