package edu.neu.cs5520.sticker.firebase;

import edu.neu.cs5520.sticker.model.Message;
import edu.neu.cs5520.sticker.repository.MessageRepository;
import java.util.List;

public class FakeMessageRepository implements MessageRepository {

  @Override
  public void insertMessage(Message message) {

  }

  @Override
  public List<Message> getMessageListSentBy(String username) {
    return null;
  }

  @Override
  public List<Message> getMessageListReceivedBy(String username) {
    return null;
  }

  @Override
  public int countMessageSentBy(String username) {
    // let's simulate some network/database lag
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return 4;
  }

  @Override
  public int countMessageReceivedBy(String username) {
    // let's simulate some network/database lag
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return 5;
  }
}
