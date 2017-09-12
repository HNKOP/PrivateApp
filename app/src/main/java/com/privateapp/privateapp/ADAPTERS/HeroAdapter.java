package com.privateapp.privateapp.ADAPTERS;

        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.privateapp.privateapp.OBJECTS.Hero;
        import com.privateapp.privateapp.R;


        import java.util.List;


public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {

    private List<Hero> listitem;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameview,levelview,strview,agilview,intview;

        public ViewHolder(View view) {
            super(view);
            nameview = (TextView) view.findViewById(R.id.recycleitem_name);
            levelview = (TextView) view.findViewById(R.id.recycleitem_level);
            strview = (TextView) view.findViewById(R.id.recycleitem_strength);
            agilview = (TextView) view.findViewById(R.id.recycleitem_agility);
            intview = (TextView) view.findViewById(R.id.recycleitem_intelligence);
            view.setOnClickListener(this);


        }
        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public HeroAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_heroeslist_item_layout,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hero hero = listitem.get(position);
        holder.nameview.setText(hero.getName());
        holder.levelview.setText(hero.getLevel());
        holder.strview.setText(String.valueOf(hero.getStrength()));
        holder.agilview.setText(String.valueOf(hero.getAgility()));
        holder.intview.setText(String.valueOf(hero.getIntelligence()));

    }

    @Override
    public int getItemCount() {
        return listitem.size();
    }
}