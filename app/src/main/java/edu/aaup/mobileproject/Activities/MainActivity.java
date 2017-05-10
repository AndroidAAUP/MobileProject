package edu.aaup.mobileproject.Activities;

import Fragments.LoginFragment;
import Fragments.Register_Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import edu.aaup.mobileproject.R;

public class MainActivity extends AppCompatActivity {

  Register_Fragment register;
  LoginFragment login;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    login = new LoginFragment();
    register = new Register_Fragment();
    FragmentManager manager = getFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.add(R.id.container,login,"login");
    transaction.commit();

  }

}
