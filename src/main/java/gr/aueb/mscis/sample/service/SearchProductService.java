package gr.aueb.mscis.sample.service;

import gr.aueb.mscis.sample.model.Lot;
import gr.aueb.mscis.sample.model.Product;

import java.util.List;

public class SearchProductService {

    private CatalogService cs;

    public SearchProductService(CatalogService cs) {
        this.cs = cs;
    }

    public void searchProductByName(String name) {
        List<Product> products = cs.findProductByName(name);
        for (Product p : products) {
            showProductDetails(p);
        }
    }

    public void searchProductByEofn(String eofn) {
        List<Product> products = cs.findProductByEOF(eofn);
        for (Product p : products) {
            showProductDetails(p);
        }
    }

    public void searchProductByCategory(String category) {
        List<Product> products = cs.findProductByCategory(category);
        for (Product p : products) {
            showProductDetails(p);
        }
    }

    private void showProductDetails(Product product) {
        System.out.println("Product Name: " + product.getName());
        System.out.println("Product Eofn: " + product.getEofn());
        System.out.println("Product Category: " + product.getCategory().getDescription());
        System.out.println("Product Price: " + product.getPrice());

        if (hasOnSale(product)) {
            System.out.println("Product Discount: " + product.getOnSale().getDiscount());
            System.out.println("Product Discount Start Date: " + product.getOnSale().getStartdate());
            System.out.println("Product Discount End Date: " + product.getOnSale().getEnddate());
        }

        if (hasLots(product)) {
            System.out.println("Product Stock: " + getStock(product));
        }
        System.out.println("-----------------------------------------------");
    }

    private int getStock(Product product) {
        int stock = 0;

        for (Lot lot : product.getLots()) {
            stock += lot.getQuantity();
        }
        return stock;
    }

    private boolean hasLots(Product product) {
        boolean answer = false;
        if (product.getLots().size() > 0) {
            answer = true;
        }
        return answer;
    }

    private boolean hasOnSale(Product product) {
       return product.getOnSale() != null;
    }


}
