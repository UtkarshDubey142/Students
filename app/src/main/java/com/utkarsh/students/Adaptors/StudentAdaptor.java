package com.utkarsh.students.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.utkarsh.students.Databases.StudentTable;
import com.utkarsh.students.Helper.DatabaseHelper;
import com.utkarsh.students.R;
import com.utkarsh.students.UpdateDataActivity;

import java.util.List;

public class StudentAdaptor extends RecyclerView.Adapter<StudentAdaptor.ViewHolder> {

    Context context;
    List<StudentTable> studentTablesList;
    View view;

    DatabaseHelper helper;

    // Constructor
    public StudentAdaptor(Context context) {
        this.context = context;
    }

    // Constructor 02
    public StudentAdaptor(Context context, List<StudentTable> studentTablesList) {
        this.context = context;
        this.studentTablesList = studentTablesList;

        helper = DatabaseHelper.getInstance(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.list_items , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (studentTablesList != null && studentTablesList.size() > 0)
            {
                StudentTable studentTable = studentTablesList.get(position);
                holder.tv_rollNo.setText(String.valueOf(studentTable.getId()));
                holder.tv_fullName.setText(studentTable.getStu_name());
                holder.tv_standard.setText(studentTable.getStu_standard());

                holder.more_iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popupMenu = new PopupMenu(context , holder.more_iv);
                        popupMenu.getMenuInflater().inflate(R.menu.items,popupMenu.getMenu());
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId())
                                {
                                    case R.id.update_id:
                                        context.startActivity(new Intent(context , UpdateDataActivity.class)
                                        .putExtra("stu_table" , studentTable));
                                        break;
                                    case R.id.delete_id:
                                        helper.deleteData(studentTable);
                                        studentTablesList.remove(position);
                                        notifyDataSetChanged();
                                        notifyItemChanged(position , studentTablesList.size());
                                        break;
                                }
                                return false;
                            }
                        });
                        popupMenu.show();
                    }
                });

            }
    }

    @Override
    public int getItemCount() {
        return studentTablesList.size();
    }

    // Class ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_rollNo , tv_fullName , tv_standard;
        ImageView more_iv;
        // Constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_rollNo = itemView.findViewById(R.id.tv_rollNo);
            tv_fullName = itemView.findViewById(R.id.tv_fullName);
            tv_standard = itemView.findViewById(R.id.tv_standard);
            more_iv = itemView.findViewById(R.id.option_menu);
        }
    }
}
