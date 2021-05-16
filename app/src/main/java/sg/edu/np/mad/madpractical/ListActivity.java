package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity implements CustomAdaptor.OnItemClicked {
    ArrayList<User> userList = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        for (int i = 0; i < 20; i++){
            User newUser = new User();
            newUser.setName("Name " + (new Random().nextInt() & Integer.MAX_VALUE));
            newUser.setDescription("Description " + (new Random().nextInt() & Integer.MAX_VALUE));
            newUser.setFollowed(new Random().nextBoolean());
            newUser.setId(i);
            userList.add(newUser);
        }
        recyclerView = findViewById(R.id.rv);
        CustomAdaptor customAdaptor = new CustomAdaptor(userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(customAdaptor);
        customAdaptor.setOnClick(ListActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                User returnUser = (User) data.getSerializableExtra("ReturnResult");
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).getId() == returnUser.getId()) {
                        userList.set(i, returnUser);
                    }
                }
            }
        }
    }

    @Override
    public void onItemClick(int position){
        User selectedUser = userList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(userList.get(position).getName());
        builder.setMessage(selectedUser.getDescription());
        builder.setCancelable(true);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                intent.putExtra("SelectedUser", selectedUser);
                startActivityForResult(intent, 1);
            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
            }
        });
        builder.show();
    }

}