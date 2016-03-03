package ru.mail.park.advandroid.helloword.lection2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UIElements extends Activity {

	Button pushButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_elements);

		pushButton = (Button)findViewById(R.id.push_button);

		setListeners();
	}

	private void setListeners() {
		pushButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(UIElements.this, "Button pressed", Toast.LENGTH_LONG).show();
			}
		});
	}
}
