package com.view.requisition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import com.logic.model.ArticleDTO;
import com.preference.PreferenceStock;
import com.stockphone.R;

public class RequisitionAdapter extends ArrayAdapter<ArticleDTO> {

	private ArrayList<ArticleDTO> listArticle;
	private ArrayList<ArticleDTO> filtered;
	private Context context;
	private ItemFilter itemFilter;
	
	public RequisitionAdapter(Context context, int textViewResourceId,
			ArrayList<ArticleDTO> listArticle) {
		super(context, textViewResourceId, listArticle);
		this.filtered = listArticle;	
		this.listArticle = this.filtered;
		this.context = context;
		this.setNotifyOnChange(true);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		ArticleDTO articleDTO = this.filtered.get(position);
		
		if(convertView == null) {			
			convertView = View.inflate(this.getContext(), R.layout.lyt_requisition_item, null);
			holder = new ViewHolder();
			holder.lblStockItemCodArticle = (TextView) convertView.findViewById(R.id.lblRequisitionItemCodArticle);
			holder.lblStockItemDescription = (TextView) convertView.findViewById(R.id.lblRequisitionItemDescription);
			holder.lblStockItemCodArtivleProvider = (TextView) convertView.findViewById(R.id.lblRequisitionItemCodArticleProvider);
			holder.lblStockItemQuantity = (TextView) convertView.findViewById(R.id.lblRequisitionItemQuantity);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();			
		}		
		
		if(articleDTO != null) {
			holder.lblStockItemCodArticle.setText(articleDTO.getCod_article());
			holder.lblStockItemDescription.setText(articleDTO.getDescription());
			holder.lblStockItemCodArtivleProvider.setText(articleDTO.getCod_article_provider());
			holder.lblStockItemQuantity.setText(String.valueOf(articleDTO.getQuantity()));
		}
		
		return convertView;		
	}
	
	@Override
	public Filter getFilter() {		
	    if(this.itemFilter == null) {
	    	this.itemFilter = new ItemFilter();
	    }	    	
	    return this.itemFilter;			
	}
	
	private class ItemFilter extends Filter {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			// NOTE: this function is *always* called from a background thread, and
	        // not the UI thread.
	        constraint = constraint.toString().toUpperCase();	       
	        PreferenceStock preferenceStock = new PreferenceStock();
	        preferenceStock.loadPreference(context);
	        FilterResults result = new FilterResults();
	        if(constraint != null && constraint.toString().length() > 0) {
	        	ArrayList<ArticleDTO> filt = new ArrayList<ArticleDTO>();
	        	int size = listArticle.size();
	        	ArticleDTO articleDTO = null;
	        	for(int i = 0; i < size; i++) {
	        		switch(preferenceStock.getCustomFilter()) {
	        			case PreferenceStock.VALUE_FILTER_CATEGORY: {
	        				articleDTO = listArticle.get(i);
	        				if(articleDTO.getCategoryName().contains(constraint)) {
	        					filt.add(articleDTO);
	        				}
	        			} break;
	        			case PreferenceStock.VALUE_FILTER_COD_ARTICLE: {
	        				articleDTO = listArticle.get(i);
	        				if(articleDTO.getCod_article().contains(constraint)) {
	        					filt.add(articleDTO);
	        				}
	        			} break;
	        			case PreferenceStock.VALUE_FILTER_COD_ARTICLE_PROVIDER: {
	        				articleDTO = listArticle.get(i);
	        				if(articleDTO.getCod_article_provider().contains(constraint)) {
	        					filt.add(articleDTO);	        						
	        				}	        						        				
	        			}
	        			case PreferenceStock.VALUE_FILTER_PROVIDER: {
	        				articleDTO = listArticle.get(i);
	        				if(articleDTO.getProviderName().contains(constraint)) {
	        					filt.add(articleDTO);	        						
	        				}	 
	        			} break;
	        			
	        			case PreferenceStock.VALUE_FILTER_DESCRIPTION: {
	        				articleDTO = listArticle.get(i);
	        				if(articleDTO.getDescription().contains(constraint)) {
	        					filt.add(articleDTO);	        						
	        				}	 
	        			} break;
	        		}
	        	}
	        
	        	switch(preferenceStock.getCustomOrder()) {
	        	
	        		case PreferenceStock.VALUE_ORDER_CATEGORY: {
	        			Collections.sort(filt, new Comparator() {	    					
	    					public int compare(Object o1, Object o2) {  
	    		                ArticleDTO bl1 = (ArticleDTO) o1;  
	    		                ArticleDTO bl2 = (ArticleDTO) o2;  
	    		                return bl1.getCategoryName().compareToIgnoreCase(bl2.getCategoryName());  
	    		            }  
	    				});	
	        		} break;
	        		
	        		case PreferenceStock.VALUE_ORDER_COD_ARTICLE: {
	        			Collections.sort(filt, new Comparator() {	    					
	    					public int compare(Object o1, Object o2) {  
	    		                ArticleDTO bl1 = (ArticleDTO) o1;  
	    		                ArticleDTO bl2 = (ArticleDTO) o2;  
	    		                return bl1.getCod_article().compareToIgnoreCase(bl2.getCod_article());  
	    		            }  
	    				});	
	        		} break;	
	        		
	        		case PreferenceStock.VALUE_ORDER_COD_ARTICLE_PROVIDER: {
	        			Collections.sort(filt, new Comparator() {	    					
	    					public int compare(Object o1, Object o2) {  
	    		                ArticleDTO bl1 = (ArticleDTO) o1;  
	    		                ArticleDTO bl2 = (ArticleDTO) o2;  
	    		                return bl1.getCod_article_provider().compareToIgnoreCase(bl2.getCod_article_provider());  
	    		            }  
	    				});	
	        		} break;
	        		
	        		case PreferenceStock.VALUE_ORDER_PROVIDER: {
	        			Collections.sort(filt, new Comparator() {	    					
	    					public int compare(Object o1, Object o2) {  
	    		                ArticleDTO bl1 = (ArticleDTO) o1;  
	    		                ArticleDTO bl2 = (ArticleDTO) o2;  
	    		                return bl1.getProviderName().compareToIgnoreCase(bl2.getProviderName());  
	    		            }  
	    				});	
	        		} break;
	        	}	        	
	        	
	        	result.count = filt.size();
	            result.values = filt;
	        }
	        else {	      
	            synchronized(listArticle) {	            	
	                result.values = listArticle;
	                result.count = listArticle.size();
	            }
	        }	        
	        return result;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {			
			// NOTE: this function is *always* called from the UI thread.
	         filtered = (ArrayList<ArticleDTO>) results.values;
	         notifyDataSetChanged();
		}		
	}
	
	private static class ViewHolder {
		TextView lblStockItemCodArticle;
		TextView lblStockItemDescription;
		TextView lblStockItemCodArtivleProvider;
		TextView lblStockItemQuantity;
	}
	
	@Override
	public int getCount() {
		return this.filtered.size();
	}
	
	@Override
	public ArticleDTO getItem(int position) {
		return this.filtered.get(position);
	}
	
	public void notifyDataSetInvalidated() {
	    super.notifyDataSetInvalidated();
	}
	
}
