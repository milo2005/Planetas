package unicauca.movil.eplanetas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import unicauca.movil.eplanetas.databinding.TemplatePlanetaBinding;
import unicauca.movil.eplanetas.models.Planeta;

/**
 * Created by Dario Chamorro on 22/05/2016.
 */
public class PlanetaAdapter extends BaseAdapter {

    LayoutInflater inflater;
    List<Planeta> data;

    public PlanetaAdapter(LayoutInflater inflater, List<Planeta> data) {
        this.inflater = inflater;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TemplatePlanetaBinding binding = TemplatePlanetaBinding.inflate(inflater);
        binding.setPlaneta(data.get(position));
        return binding.getRoot();
    }
}
