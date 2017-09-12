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
            nameview = view.findViewById(R.id.recycleitem_name);
            levelview = view.findViewById(R.id.recycleitem_level);
            strview =  view.findViewById(R.id.recycleitem_strength);
            agilview = view.findViewById(R.id.recycleitem_agility);
            intview = view.findViewById(R.id.recycleitem_intelligence);
            view.setOnClickListener(this);


        }
        @Override
        public void onClick(View v) {

        }
    }

    public HeroAdapter(List<Hero> items) {  listitem = items;  }

    @Override
    public HeroAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_heroeslist_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hero hero = listitem.get(position);
        holder.nameview.setText(hero.getName());
        holder.levelview.setText("Уровень: " + String.valueOf(hero.getLevel()));
        holder.strview.setText("STR: " + String.valueOf(hero.getStrength()));
        holder.agilview.setText("AGL: " + String.valueOf(hero.getAgility()));
        holder.intview.setText("INT: " + String.valueOf(hero.getIntelligence()));

    }

    @Override
    public int getItemCount() {
        return listitem.size();
    }
}