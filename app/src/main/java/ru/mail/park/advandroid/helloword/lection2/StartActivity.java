package ru.mail.park.advandroid.helloword.lection2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;


public class StartActivity extends Activity implements View.OnClickListener {


    private static final List<String> buttons = Arrays.asList(
            "AbsoluteLayout",
            "FrameLayout",
            "LinearLayout",
            "RelativeLayout",
            "TableLayout",
            "GridLayout",
            "FragmentActivity",
            "",
            "ListView ArrayAdapter",
            "ListView BaseAdapter",
            "Recycler View",
            "Widgets",
            "",
            "ServiceActivity",
            "SendMessage",
            "",
            "Treads",
            "Loader"
    );

    private static final int layouts[] = {
            R.layout.absolute_layout,
            R.layout.frame_layout,
            R.layout.linear_layout,
            R.layout.relative_layout,
            R.layout.table_layout,
            R.layout.grid_layout,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        MyArrayAdapter myArrayAdapter = new MyArrayAdapter(this, R.id.action_list);
        myArrayAdapter.addAll(buttons);
        ListView lvw = (ListView)findViewById(R.id.action_list);
        lvw.setAdapter(myArrayAdapter);
    }

    @Override
    public void onClick(View v) {
        String str = ((Button)v).getText().toString();
        switch (str) {
            case "":
                break;
            case "SendMessage":
                // Implicit intent
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("http://mail.ru"));
                startActivity(sendIntent);
                break;
            case "Treads": {
                // Explicit intent
                Intent intent = new Intent(this, ThreadActivity.class);
                startActivity(intent);
                break;
            }
            case "Loader": {
                // Explicit intent
                Intent intent = new Intent(this, LoaderActivity.class);
                startActivity(intent);
                break;
            }
            case "ListView ArrayAdapter": {
                // Explicit intent
                Intent intent = new Intent(this, ListViewArrayAdapterActivity.class);
                startActivity(intent);
                break;
            }
            case "ListView BaseAdapter": {
                // Explicit intent
                Intent intent = new Intent(this, ListViewBaseAdapterActivity.class);
                startActivity(intent);
                break;
            }
            case "Recycler View": {
                Intent intent = new Intent(this, RecyclerActivity.class);
                startActivity(intent);
                break;
            }
            case "ServiceActivity": {
                Intent intent = new Intent(this, ServiceActivity.class);
                startActivity(intent);
                break;
            }
            case "Widgets": {
                // Explicit intent
                Intent intent = new Intent(this, UIElements.class);
                startActivity(intent);
                break;
            }
            case "FragmentActivity": {
                Intent intent = new Intent(this, FragmentActivity.class);
                startActivity(intent);
                break;
            }
            default: {
                // Explicit intent
                Intent intent = new Intent(this, LayoutActivity.class);
                Log.d("StartActivity", "Start " + str + " " + buttons.indexOf(str) + " " + layouts[buttons.indexOf(str)]);
                intent.putExtra("activity_type", layouts[buttons.indexOf(str)]);
                intent.putExtra("activity_type_name", str);
                startActivity(intent);
                break;
            }
        }
    }

    class MyArrayAdapter extends ArrayAdapter<String> {
        public MyArrayAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String str = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.action_button, parent, false);
            }

            TextView btn = (TextView)convertView.findViewById(R.id.button);
            btn.setText(str);
            btn.setVisibility(str.equals("") ? View.INVISIBLE : View.VISIBLE);
            btn.setOnClickListener(StartActivity.this);
            return convertView;
        }
    }
}
