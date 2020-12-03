package cl.inacap.simpsonsapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import cl.inacap.simpsonsapp.R;
import cl.inacap.simpsonsapp.dto.Personaje;

public class PersonajesAdapter extends ArrayAdapter {

    private List<Personaje> personajes;
    private Activity activity;

    public PersonajesAdapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.activity = context;
        this.personajes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_personajes,
                null, true);
        TextView quoteTxt = rowView.findViewById(R.id.quote_list_view);
        TextView nombreTxt = rowView.findViewById(R.id.nombre_list_view);
        ImageView image_view = rowView.findViewById(R.id.image_list_view);
        quoteTxt.setText(personajes.get(position).getQuote());
        nombreTxt.setText(personajes.get(position).getCharacter());
        Picasso.get().load(this.personajes.get(position).getImage())
                .resize(190, 300)
                .centerCrop().into(image_view);
        return rowView;
    }
}
