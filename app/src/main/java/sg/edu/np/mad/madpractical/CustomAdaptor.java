package sg.edu.np.mad.madpractical;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdaptor extends RecyclerView.Adapter<CustomViewHolder>{

    ArrayList<User> data;
    private OnItemClicked onClick;

    public interface OnItemClicked{
        void onItemClick(int position);
    }

    public CustomAdaptor(ArrayList<User> input) {
        data = input;
    }

    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View selectedView;
        if(viewType == 0){
            selectedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview1, parent, false);
        }
        else{
            selectedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview2, parent, false);
        }
        return new CustomViewHolder(selectedView);
    }

    public void onBindViewHolder(CustomViewHolder holder, int position){
        User selectedUser = data.get(position);
        holder.userName.setText(selectedUser.getName());
        holder.userDesc.setText(selectedUser.getDescription());
        holder.userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });
    }

    public int getItemCount(){
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getName().endsWith("7")) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setOnClick(OnItemClicked onClick){
        this.onClick = onClick;
    }
}
