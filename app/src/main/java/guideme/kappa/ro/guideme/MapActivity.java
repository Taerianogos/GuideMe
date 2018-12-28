package guideme.kappa.ro.guideme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;


import com.google.firebase.auth.FirebaseAuth;

public class MapActivity extends AppCompatActivity {
    private MapView mapView;
    private Button b;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoiYW5kcmVmdHkiLCJhIjoiY2pxODE4MGcxMm5ldzQ4ank2dXg5b29rZSJ9.KxoOIJBxaY0ce5Il6apEWQ");
        setContentView(R.layout.activity_map);
        mapView = (MapView) findViewById(R.id.mapView);
        b = findViewById(R.id.buttonTest);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MapActivity.this, ThirdActivity.class));
                finish();
            }
        });
   //     mapView.onCreate(savedInstanceState);

    }
    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}