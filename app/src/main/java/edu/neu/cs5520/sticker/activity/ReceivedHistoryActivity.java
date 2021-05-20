package edu.neu.cs5520.sticker.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.neu.cs5520.sticker.R;
import edu.neu.cs5520.sticker.model.Message;
import edu.neu.cs5520.sticker.repository.Impl.MessageRepositoryImpl;
import edu.neu.cs5520.sticker.repository.MessageRepository;

public class ReceivedHistoryActivity extends AppCompatActivity {

  MessageRepository messageRepository = new MessageRepositoryImpl();
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_received_history);
  }
}