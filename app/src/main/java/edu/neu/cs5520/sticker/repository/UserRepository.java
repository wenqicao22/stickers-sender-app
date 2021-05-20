package edu.neu.cs5520.sticker.repository;

public interface UserRepository {
  String getCurrentUser();
  void saveCurrentUser(String username);
  void removeCurrentUser();
}
