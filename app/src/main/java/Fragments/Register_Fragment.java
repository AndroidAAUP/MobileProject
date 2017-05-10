package Fragments;

import Models.ConnectionDetector;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.aaup.mobileproject.Activities.MainActivity;
import edu.aaup.mobileproject.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;;

import static android.app.Activity.RESULT_OK;
import static edu.aaup.mobileproject.R.id.tv_address;

public class Register_Fragment extends Fragment implements View.OnClickListener {
  EditText et_username, et_password, et_phone, et_password1, et_address;
  Button bn_register, bn_chooseLocation;
  TextView tv_login;
  ConnectionDetector connection;
  Context ctx;
  private final static int MY_PERMISSION_FINE_LOCATION = 101;
  private final static int PLACE_PICKER_REQUEST = 1;
  private final static LatLngBounds bounds =
      new LatLngBounds(new LatLng(32.302265, 35.184714), new LatLng(32.438945, 35.403817));

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {

    View v = inflater.inflate(R.layout.fragment_register, container, false);
    initialize(v);
    requestPermission();
    tv_login.setOnClickListener(this);
    bn_register.setOnClickListener(this);
    bn_chooseLocation.setOnClickListener(this);
    return v;
  }

  @Override public void onAttach(Context context) {
    ctx = context;
    super.onAttach(context);
  }

  private void initialize(View v) {
    connection = new ConnectionDetector(ctx);

    et_username = (EditText) v.findViewById(R.id.et_username);
    et_password = (EditText) v.findViewById(R.id.et_password);
    et_password1 = (EditText) v.findViewById(R.id.et_password1);
    et_phone = (EditText) v.findViewById(R.id.et_phone);
    et_address = (EditText) v.findViewById(tv_address);
    tv_login = (TextView) v.findViewById(R.id.tv_login);
    bn_register = (Button) v.findViewById(R.id.bn_register);
    bn_chooseLocation = (Button) v.findViewById(R.id.choose_location);
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bn_register: {

      }
      break;
      case R.id.choose_location: {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        builder.setLatLngBounds(bounds);
        try {
          Intent intent = builder.build(getActivity());
          startActivityForResult(intent, PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
          e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
          e.printStackTrace();
        }

      }
      break;
      case R.id.tv_login: {
        LoginFragment login = new LoginFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, login, "login");
        transaction.commit();
      }
      break;
    }
  }

  private void requestPermission() {
    if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        requestPermissions(new String[] { android.Manifest.permission.ACCESS_FINE_LOCATION },
            MY_PERMISSION_FINE_LOCATION);
      }
    }
  }
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    switch (requestCode) {
      case MY_PERMISSION_FINE_LOCATION:
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
          Toast.makeText(ctx, "This app requires location permissions to be granted", Toast.LENGTH_LONG).show();
         // finish();
        }
        break;
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == PLACE_PICKER_REQUEST){
      if (resultCode == RESULT_OK){
        Place place = PlacePicker.getPlace(getActivity(), data);
        et_address.setText(place.getName()+" "+place.getAddress());

      }
    }
  }
}
