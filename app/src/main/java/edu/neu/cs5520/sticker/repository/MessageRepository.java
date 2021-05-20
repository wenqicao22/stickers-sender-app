package edu.neu.cs5520.sticker.repository;

import edu.neu.cs5520.sticker.model.Message;
import java.util.List;

public interface MessageRepository {
  void insertMessage(Message message);
  List<Message> getMessageListSentBy(String username);
  List<Message> getMessageListReceivedBy(String username);
  int countMessageSentBy(String username);
  int countMessageReceivedBy(String username);
}
