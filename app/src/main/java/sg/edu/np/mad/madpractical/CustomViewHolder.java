package sg.edu.np.mad.madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView userName, userDesc;
    ImageView userImage;
    public CustomViewHolder(View itemView){
        super(itemView);
        userName = itemView.findViewById(R.id.recyclerName);
        userDesc = itemView.findViewById(R.id.recyclerDesc);
        userImage = itemView.findViewById(R.id.img_profile);
    }
}
