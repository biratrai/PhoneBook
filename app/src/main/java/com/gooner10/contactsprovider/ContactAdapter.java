package com.gooner10.contactsprovider;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolderData> {
    public LayoutInflater layoutInflater;
    public Context mContext;
    ArrayList<ContactModel> mContactDataList;

    public ContactAdapter(Context context, ArrayList<ContactModel> mContactArray) {
        layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mContactDataList = mContactArray;
    }

    @Override
    public ViewHolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.single_contact_row, parent, false);
        ViewHolderData viewHolderData = new ViewHolderData(view);
        return viewHolderData;
    }

    @Override
    public void onBindViewHolder(ViewHolderData holder, final int position) {
//        if(mContactDataList.get())
        ContactModel contactModel = mContactDataList.get(position);

        holder.number.setText(contactModel.getmNumber());
        holder.lastContacted.setText(contactModel.getmLastContact());
//        String deleted = String.valueOf(contactModel.getmDeleted());
        holder.deleted.setText(String.valueOf(contactModel.getmDeleted()));
        //  Get and Post the event
//        EventBus.getDefault().postSticky(new OnItemClickEvent(bundle));
    }
//});


    @Override
    public int getItemCount() {
        return mContactDataList.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView number;
        public final TextView deleted;
        public final TextView lastContacted;

        public ViewHolderData(View itemView) {
            super(itemView);
            mView = itemView;
            number = (TextView) itemView.findViewById(R.id.number);
            deleted = (TextView) itemView.findViewById(R.id.deleted);
            lastContacted = (TextView) itemView.findViewById(R.id.last_contact);
        }
    }
}



