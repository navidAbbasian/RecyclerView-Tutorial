package com.example.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    //ArryList is for arry with change able length
    private ArrayList<String> contacts = new ArrayList<>();
    private static final String TAG = "ContactAdapter";
    private ItemClickListener itemClickListener;
    //constructor
    public ContactAdapter(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
        contacts.add("Ruthann Trustrie");
        contacts.add("Peadar Dawtrey");
        contacts.add("Felipe Bradtke");
        contacts.add("Claude Crissil");
        contacts.add("Jacky Girardeau");
        contacts.add("Rubia Dominguez");
        contacts.add("Michaela Churchley");
        contacts.add("Harvey Pentelow");
        contacts.add("Neilla Langton");
        contacts.add("Marco Greaves");
        contacts.add("Liz Batchley");
        contacts.add("Lamond Littlepage");
        contacts.add("Malina Weir");
        contacts.add("Tomlin Lenchenko");
        contacts.add("Hy Pavelin");
        contacts.add("Jenelle Palin");
        contacts.add("Damon Knewstubb");
        contacts.add("Alex Ivanusyev");
        contacts.add("Hamil Callery");
        contacts.add("Karol Syer");
    }

    //add item to arry
    public void addNewContact (String fullname){
        contacts.add(0,fullname);
        //say to recycler view we add new item
        notifyItemInserted(0);
    }
    //update item 
    public void updateContact (String fullName,int position){
        contacts.set(position,fullName);
        //say to recycler view we change some item
        notifyItemChanged(position);
    }

    @NonNull
    @Override
    //hold the view(xml to java)....
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    //set full name to the recyclerview
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: position=>"+position);
        holder.bindContact(contacts.get(position));
    }

    @Override
    public int getItemCount() {

        return contacts.size();
    }//end of adapter

    //ViewHolder beginning

    // give full name from view
    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView firstCharacterTV;
        private TextView fullnameTV;
        //constructor
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            firstCharacterTV=itemView.findViewById(R.id.tv_contact_firstCharacter);
            fullnameTV=itemView.findViewById(R.id.tv_contact_fullname);
        }
        //set full name and first character and set action on view(edit & delete)
        public void bindContact (final String fullname){
            fullnameTV.setText(fullname);
            firstCharacterTV.setText(fullname.substring(0,1));
            //set action on view
            //edit
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClick(fullname, getAdapterPosition());
                }
            });
            //delete
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    contacts.remove(getAdapterPosition());
                    //say to recycler view we remove a item
                    notifyItemRemoved(getAdapterPosition());
                    return false;
                }
            });
        }

    }
    //say action to MainActivity
    public interface ItemClickListener {
        void onClick(String fullname, int position);
    }
}
