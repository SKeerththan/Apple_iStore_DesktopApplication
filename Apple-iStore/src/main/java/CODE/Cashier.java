/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CODE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author exboy
 */
public class Cashier {

    public void viewProductDetails(List<Product> products) {
        for (Product product : products) {
            System.out.println("Name: " + product.getName());
            System.out.println("Category: " + product.getCategory());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Stock: " + product.getStock());
            System.out.println("----------------------");
        }
    }

    // Search for stock details of different products
    public int searchStockDetails(List<Product> products, String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                System.out.println("Stock: " + product.getStock());
                return product.getStock();
            }
        }
        System.out.println("Product not found.");

        return 0;
    }

    // Search product details based on category, name, price, etc.
    public String searchProductDetailsBasedOnCategory(List<Product> products, String category) {
        String output = "";
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
//                System.out.println("Name: " + product.getName());
//                System.out.println("Price: " + product.getPrice());
//                System.out.println("----------------------");
                output += "Name: " + product.getName() + "\n" + "Price: " + product.getPrice() + "\n" + "Category: " + product.getCategory() + "\n" + "Stock: " + product.getStock() + "\n" + "----------------------"+ "\n";
            }
        }
        
        return output;

    }

    public String searchProductDetailsBasedOnName(List<Product> products, String name) {
        String output = "";
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
//                System.out.println("Name: " + product.getName());
//                System.out.println("Price: " + product.getPrice());
//                System.out.println("----------------------");
                output += "Name: " + product.getName() + "\n" + "Price: " + product.getPrice() + "\n" + "Category: " + product.getCategory() + "\n" + "Stock: " + product.getStock() + "\n" + "----------------------"+ "\n";
            }
        }
        return output;
    }

    public String searchProductDetailsBasedOnPrice(List<Product> products, String price) {
        String output = "";
        for (Product product : products) {
            if (price.equalsIgnoreCase(product.getPrice()+"")) {
//                System.out.println("Name: " + product.getName());
//                System.out.println("Price: " + product.getPrice());
//                System.out.println("----------------------");
                output += "Name: " + product.getName() + "\n" + "Price: " + product.getPrice() + "\n" + "Category: " + product.getCategory() + "\n" + "Stock: " + product.getStock() + "\n" + "----------------------"+ "\n";
            }
        }
       
        return output;
    }

    public List<Product> readProductsFromFile() {
        String filename = "products.txt";
        List<Product> productList = new ArrayList<>();

        try (FileReader fileReader = new FileReader(filename); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String name = data[0];
                    String category = data[1];
                    double price = Double.parseDouble(data[2]);
                    int stock = Integer.parseInt(data[3]);
                    // Create a new Product object and add it to the list
                    Product product = new Product(name, category, price, stock);
                    productList.add(product);

                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        System.out.println(productList);
        return productList;
    }

}
