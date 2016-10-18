package proyectos.antonio.securedhome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity{

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = MainActivity.class.getSimpleName();

    Button takePhoto;
    Button listPhotos;

    MyAPI apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPlayServices();
        takePhoto = (Button) findViewById(R.id.button_take);
        listPhotos = (Button) findViewById(R.id.button_list);

        // Django token administration
        //String token = "bee27582f196eca69b22b1d26ccd77611aba20fb";
        String token = PreferencesManager.getString(getApplicationContext(),"token");

        apiService = APIClient.createService(MyAPI.class, token);

        takePhoto.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<APIMessage> call = apiService.takePhoto();
                call.enqueue(new Callback<APIMessage>() {
                     @Override
                     public void onResponse(Call<APIMessage> call, Response<APIMessage> response) {
                         if (response.isSuccessful()) {
                             Toast.makeText(MainActivity.this,"Photo taken succesfully",Toast.LENGTH_LONG);
                         } else {
                             Log.d(TAG, "onResponse: " + response.toString());
                         }
                     }

                     @Override
                     public void onFailure(Call<APIMessage> call, Throwable t) {
                         // something went completely south (like no internet connection)
                         Log.d(TAG, "onFailure" + t.getMessage());
                     }
                });
                Toast.makeText(MainActivity.this, "Taking photo", Toast.LENGTH_LONG).show();
            }
        });
        listPhotos.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Listing photos", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }


    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Toast.makeText(MainActivity.this,"This device is not supported",Toast.LENGTH_LONG);
                finish();
            }
            return false;
        }
        return true;
    }


}
