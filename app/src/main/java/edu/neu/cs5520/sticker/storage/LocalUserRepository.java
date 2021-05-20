package edu.neu.cs5520.sticker.storage;

import android.content.Context;
import android.content.SharedPreferences;
import edu.neu.cs5520.sticker.R;
import edu.neu.cs5520.sticker.repository.UserRepository;

public class LocalUserRepository implements UserRepository {

  private final Context mContext;

  public LocalUserRepository(Context context) {
    this.mContext = context;
  }

  @Override
  public String getCurrentUser() {
    SharedPreferences sharedPref = mContext.getSharedPreferences(
        mContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    return sharedPref.getString(mContext.getString(R.string.saved_username), null);
  }

  @Override
  public void saveCurrentUser(String username) {
    SharedPreferences sharedPref = mContext.getSharedPreferences(
        mContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.putString(mContext.getString(R.string.saved_username), username);
    editor.apply();
  }

  @Override
  public void removeCurrentUser() {
    SharedPreferences sharedPref = mContext.getSharedPreferences(
        mContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.remove(mContext.getString(R.string.saved_username));
    editor.apply();
  }
}
