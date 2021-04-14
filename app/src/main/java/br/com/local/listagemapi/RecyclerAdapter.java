package br.com.local.listagemapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<Hero> listHero;

    public RecyclerAdapter(Context context, List<Hero> listHero) {
        this.context = context;
        this.listHero = listHero;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itensLista = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_layout,
                        parent, false);

        return new MyViewHolder( itensLista );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Hero hero = listHero.get( position );
        holder.textHeroi.setText( hero.getHeroi() );
        holder.textNome.setText( hero.getNome() );
        holder.textEditora.setText( hero.getEditora() );
        holder.textRating.setText( hero.getRating() + "º");

    }

    @Override
    public int getItemCount() {
        return listHero.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textHeroi, textRating, textNome, textEditora;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textHeroi = itemView.findViewById(R.id.textHeroi);
            textNome = itemView.findViewById(R.id.textNome);
            textEditora = itemView.findViewById(R.id.textEditora);
            textRating = itemView.findViewById(R.id.textRating);
        }
    }

    public void addItens(ArrayList<Hero> heroLista){
        listHero.addAll( heroLista );
        notifyDataSetChanged();
    }

}
