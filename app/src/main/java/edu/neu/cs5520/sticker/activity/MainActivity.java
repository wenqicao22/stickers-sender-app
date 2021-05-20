package edu.neu.cs5520.sticker.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import edu.neu.cs5520.sticker.R;
import edu.neu.cs5520.sticker.firebase.FakeMessageRepository;
import edu.neu.cs5520.sticker.firebase.FakeNotificationRepository;
import edu.neu.cs5520.sticker.repository.MessageRepository;
import edu.neu.cs5520.sticker.repository.NotificationRepository;
import edu.neu.cs5520.sticker.repository.UserRepository;
import edu.neu.cs5520.sticker.storage.LocalUserRepository;

@SuppressLint("NonConstantResourceId")
public class MainActivity extends AppCompatActivity {

  @BindView(R.id.info_textview)
  TextView mInfoTextView;
  @BindView(R.id.field_username)
  EditText mFieldUsername;
  @BindView(R.id.button_sign_in)
  Button mButtonSignIn;
  @BindView(R.id.button_sign_out)
  Button mButtonSignOut;
  @BindView(R.id.button_sent_history)
  Button mButtonSentHistory;
  @BindView(R.id.button_received_history)
  Button mButtonReceivedHistory;
  @BindView(R.id.fab_new_message)
  FloatingActionButton mFabNewMessage;
  @BindView(R.id.progressbar_message)
  ProgressBar mProgressBarMessage;

  private UserRepository mUserRepository;
  private MessageRepository mMessageRepository;
  private NotificationRepository mNotificationRepository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    mUserRepository = new LocalUserRepository(getApplicationContext());
    mMessageRepository = new FakeMessageRepository(); // TODO: replace with the firebase implementation
    mNotificationRepository = new FakeNotificationRepository(); // TODO: replace with the firebase implementation
    updateUI();
  }

  @OnClick(R.id.button_sent_history)
  public void launchSentHistoryActivity(View view) {
    Intent intent = new Intent(this, SentHistoryActivity.class);
    startActivity(intent);
  }

  @OnClick(R.id.button_received_history)
  public void launchReceivedHistoryActivity(View view) {
    Intent intent = new Intent(this, SentHistoryActivity.class);
    startActivity(intent);
  }

  @OnClick(R.id.button_sign_in)
  public void signIn(View view) {
    String username = mFieldUsername.getText().toString();
    mUserRepository.saveCurrentUser(username);
    updateUI();
  }

  @OnClick(R.id.button_sign_out)
  public void signOut(View view) {
    mUserRepository.removeCurrentUser();
    updateUI();
  }

  private void updateUI() {
    String username = mUserRepository.getCurrentUser();
    if (username == null || username.isEmpty()) {
      mButtonSentHistory.setEnabled(false);
      mButtonReceivedHistory.setEnabled(false);
      mFabNewMessage.setEnabled(false);
      mButtonSignOut.setVisibility(View.INVISIBLE);

      mButtonSignIn.setEnabled(true);
      mButtonSignIn.setVisibility(View.VISIBLE);
      mFieldUsername.setVisibility(View.VISIBLE);
      mInfoTextView.setText(R.string.enter_username);
    } else {
      Thread messageThread = new Thread(new MessageCountThread(username));
      mButtonSignIn.setEnabled(false);
      mProgressBarMessage.setVisibility(View.VISIBLE);
      mButtonSignIn.setVisibility(View.INVISIBLE);
      mFieldUsername.setVisibility(View.INVISIBLE);
      mInfoTextView.setText(R.string.loading);
      messageThread.start();
    }
  }

  class MessageCountThread implements Runnable {

    private final String mUsername;

    public MessageCountThread(String username) {
      this.mUsername = username;
    }

    @Override
    public void run() {
      int countSent = mMessageRepository.countMessageSentBy(mUsername);
      int countReceived = mMessageRepository.countMessageReceivedBy(mUsername);
      runOnUiThread(() -> {
        mProgressBarMessage.setVisibility(View.INVISIBLE);
        mInfoTextView.setText(getString(R.string.welcome_fmt, mUsername, countSent, countReceived));

        mButtonSentHistory.setEnabled(true);
        mButtonReceivedHistory.setEnabled(true);
        mFabNewMessage.setEnabled(true);
        mButtonSignOut.setVisibility(View.VISIBLE);
      });
    }
  }
}