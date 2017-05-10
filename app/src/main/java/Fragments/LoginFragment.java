package Fragments;

import Models.ConnectionDetector;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import edu.aaup.mobileproject.R;

public class LoginFragment extends Fragment implements View.OnClickListener{

  EditText et_username, et_password;
  TextView tv_register;
  Button bn_login;
  ConnectionDetector connection;
  Context ctx;
  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_login,container,false);

    initialize(view);
    btnHandler();
    return view;
  }
  private void initialize(View v){
    connection = new ConnectionDetector(ctx);
    et_username = (EditText)v. findViewById(R.id.et_username);
    et_password = (EditText)v. findViewById(R.id.et_password);
    tv_register = (TextView)v.findViewById(R.id.tv_register);
    bn_login = (Button)v.findViewById(R.id.bn_login);
  }
  protected void btnHandler(){
    bn_login.setOnClickListener(this);
    tv_register.setOnClickListener(this);
  }

  @Override public void onClick(View v) {
    switch (v.getId()){
      case R.id.bn_login:{


      }break;
      case R.id.tv_register:{
        Register_Fragment register  = new Register_Fragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container,register,"register");
        transaction.commit();

      }break;
    }

  }

  @Override public void onAttach(Context context) {
    ctx = context;
    super.onAttach(context);
  }
}
