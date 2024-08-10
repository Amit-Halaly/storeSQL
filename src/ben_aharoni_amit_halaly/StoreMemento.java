package ben_aharoni_amit_halaly;

import java.util.TreeSet;

public class StoreMemento {
	private TreeSet<products> productList = new TreeSet<products>();

	    public StoreMemento(TreeSet<products> productList)  {
	       for(products product : productList) {
	    	  // this.productList.add(product.pclone());
	       }
	    }

	    public TreeSet<products> getProductList() {
	        return productList;
	    }
	    
	    TreeSet<products> getState() {
			return productList;
		}

}
