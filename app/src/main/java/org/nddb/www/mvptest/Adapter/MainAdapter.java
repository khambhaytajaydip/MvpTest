package org.nddb.www.mvptest.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.nddb.www.mvptest.Constant.Constant;
import org.nddb.www.mvptest.DbHelper.DbHelper;
import org.nddb.www.mvptest.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 09/28/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    Context context;
    ArrayList<HashMap<String, String>> arrayList;

    public MainAdapter(ArrayList<HashMap<String, String>> array, Context context) {
        this.context = context;
        this.arrayList = array;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, final int position) {
        final HashMap<String, String> hashmap = arrayList.get(position);
        holder.tvName.setText(hashmap.get(Constant.fieldUserName));

        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Update Name");

                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(LinearLayout.VERTICAL);

                final EditText editText = new EditText(context);
                editText.setHint((hashmap.get(Constant.fieldUserName)).toString());
                linearLayout.addView(editText);
                builder.setView(linearLayout);

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = editText.getText().toString();

                        DbHelper dbHelper = new DbHelper(context);
                        HashMap<String,Object> hashMap1 = new HashMap<String, Object>();
                        HashMap<String,String> hashMap2 = new HashMap<String, String>();

                        hashMap1.put(Constant.fieldUserName,name);
                        hashMap2.put(Constant.fieldUserName,name);
                        dbHelper.updateRecord(Constant.tableUser,hashMap1,Constant.fieldUserId + " = ?", new String[]{hashmap.get(Constant.fieldUserId)});
                        notifyDataSetChanged();
                        arrayList.set(position,hashMap2);



                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
                return false;

            }
        });


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder aleartDialog = new AlertDialog.Builder(context);
                aleartDialog.setTitle("Delete Name");
                aleartDialog.setMessage("Are You Sure You Want To Delete ???");
                aleartDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DbHelper dbHelper = new DbHelper(context);
                        String qry = "Delete from " + Constant.tableUser + " Where " + Constant.fieldUserId + " = " + hashmap.get(Constant.fieldUserId);
                        dbHelper.deleteRecordQuery(qry);
                        notifyDataSetChanged();
                        arrayList.remove(position);
                        dialogInterface.dismiss();


                    }
                });
                aleartDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                aleartDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.adapter_tv_name);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.adapter_ll_adapter);
        }
    }

}
