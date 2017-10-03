package org.nddb.www.mvptest.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.nddb.www.mvptest.Adapter.MainAdapter;
import org.nddb.www.mvptest.Model.User;
import org.nddb.www.mvptest.Presenter.MainPresnter;
import org.nddb.www.mvptest.R;
import org.nddb.www.mvptest.Model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements MainView {
    EditText etGetData;
    Button button;
    MainPresnter mainPresnter;
    RecyclerView recyclerView;
    TextView tvOneResponse, tvSecondResponse, tvThirdResponse;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findId();
        init();
    }

    private void init() {
        mainPresnter.GetDataFromServer(this);
        mainPresnter.GetData(MainActivity.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresnter.AttemptoLogin(etGetData.getText().toString());
                mainPresnter.GetData(MainActivity.this);
            }
        });
    }

    private void findId() {
        etGetData = (EditText) findViewById(R.id.et_use_name);
        button = (Button) findViewById(R.id.btn);
        button.setText("Submit");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mainPresnter = new MainPresnter(MainActivity.this, MainActivity.this);
        tvOneResponse = (TextView) findViewById(R.id.main_act_txt_one);
        tvSecondResponse = (TextView) findViewById(R.id.main_act_txt_two);
        tvThirdResponse = (TextView) findViewById(R.id.main_act_txt_three);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
    }

    @Override
    public void setProgressBarVisiblity(int v) {
       progressBar.setVisibility(v);
    }

    @Override
    public void onSuccses(long l) {
        Log.d("jai", "long :" + l);
        Toast.makeText(MainActivity.this, "succes", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(long l) {
        Log.d("jai", "long :" + l);
        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnSuccesist(ArrayList<HashMap<String, String>> list) {
        if (list != null && list.size() > 0) {
            MainAdapter mainAdapter = new MainAdapter(list, MainActivity.this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(mainAdapter);
        }
    }

    @Override
    public void OnUsersSucces(final User user) {
        if (user != null) {
            String data1 = "";
            data1 = user.getPage().toString() + "\n" + user.getTotalPages() + "\n" + user.getTotal() + "\n";
            for (data data : user.getData()) {
                data1 = "\n" + data1 + data.getId() + "\n" + data.getFirstName() + "\n" + data.getLastName() + "\n";
            }
            tvOneResponse.setText(data1);
        }
    }

    @Override
    public void onUserResError(String s) {
      tvOneResponse.setText(s);
    }
}
