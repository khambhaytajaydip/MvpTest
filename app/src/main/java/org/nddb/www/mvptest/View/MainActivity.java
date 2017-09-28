package org.nddb.www.mvptest.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.nddb.www.mvptest.Adapter.MainAdapter;
import org.nddb.www.mvptest.Presenter.MainPresnter;
import org.nddb.www.mvptest.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements MainView {
    EditText etGetData;
    Button button;
    MainPresnter mainPresnter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etGetData = (EditText)findViewById(R.id.et_use_name);
        button = (Button)findViewById(R.id.btn);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mainPresnter = new MainPresnter(MainActivity.this,MainActivity.this);
        mainPresnter.GetData(MainActivity.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresnter.AttemptoLogin(etGetData.getText().toString());
                mainPresnter.GetData(MainActivity.this);
            }
        });
    }



    @Override
    public void onSuccses(long l) {
        Log.d("jai","long :"+l);
        Toast.makeText(MainActivity.this, "succes",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(long l) {
        Log.d("jai","long :"+l);
    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnSuccesist(ArrayList<HashMap<String,String>> list)
    {
        if (list !=  null && list.size() > 0)
        {
            MainAdapter mainAdapter = new MainAdapter(list,MainActivity.this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(mainAdapter);
        }
    }
}
