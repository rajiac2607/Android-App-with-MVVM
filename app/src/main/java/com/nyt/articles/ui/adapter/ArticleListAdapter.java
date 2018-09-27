package com.nyt.articles.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.nyt.articles.R;
import com.nyt.articles.data.model.Article;
import com.nyt.articles.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raji on 26/09/2018.
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticlesListViewHolder>
        implements Filterable {

    private List<Article> articles;
    private List<Article> articlesFiltered;

    private final OnItemSelected onItemSelected;

    public ArticleListAdapter(OnItemSelected onItemSelected) {
        this.onItemSelected = onItemSelected;
        articles = new ArrayList<>();
        articlesFiltered = new ArrayList<>();
    }

    @NonNull
    @Override
    public ArticlesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item, parent, false);
        return new ArticlesListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesListViewHolder holder, final int position) {
        holder.bind(articles.get(position));
        holder.listItemBinding.cardView.setOnClickListener(view -> onItemSelected.onItemSelected(articles.get(position))
        );
    }


    @Override
    public int getItemCount() {
        return articlesFiltered == null || articlesFiltered.isEmpty() ? 0 : articlesFiltered.size();
    }

    /**
     * method for filtering list on search query
     * @return filtered list
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    articlesFiltered = articles;
                } else {
                    List<Article> filteredList = new ArrayList<>();
                    for (Article row : articles) {

                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()))
                            filteredList.add(row);

                    }

                    articlesFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = articlesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                articlesFiltered = (ArrayList<Article>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ArticlesListViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding listItemBinding;


        ArticlesListViewHolder(@NonNull ListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;

        }

        void bind(Article Articles) {
            listItemBinding.setArticle(Articles);

        }
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        this.articlesFiltered = articles;
        notifyDataSetChanged();

    }

    /**
     * call back for recyclerview item selection event
     */
    public interface OnItemSelected {
        void onItemSelected(Article article);
    }

}
