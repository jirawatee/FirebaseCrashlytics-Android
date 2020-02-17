package com.example.crashlytics;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
	private static final String TAG = MainActivity.class.getSimpleName();
	private FirebaseCrashlytics crashlytics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		crashlytics = FirebaseCrashlytics.getInstance();
		crashlytics.log("Start logging!");

		crashlytics.setUserId("id-99999");
		crashlytics.setCustomKey("DisplayName", "FirebaseThailand");
		crashlytics.setCustomKey("Email", "firebasethailand@gmail.com");

		crashlytics.setCustomKey("key", "value");
		crashlytics.setCustomKey(TAG, true);
		crashlytics.setCustomKey("integer", 1234);
		crashlytics.setCustomKey("float", 567.89f);
		crashlytics.setCustomKey("timestamp", System.currentTimeMillis());

		bindWidget();
	}

	private void bindWidget() {
		findViewById(R.id.btn_force).setOnClickListener(this);
		findViewById(R.id.btn_exception).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_force:
				crashlytics.log("Log some message before a crash happen");
				throw new RuntimeException("Test Crash");
			case R.id.btn_exception:
				/*
				try {
					throw new NullPointerException();
				} catch (NullPointerException ex) {
					Crashlytics.logException(ex);
					Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
				}
				*/
				Button btn = findViewById(R.id.btn_exception);
				btn.setTextColor(null);
				break;
		}
	}
}