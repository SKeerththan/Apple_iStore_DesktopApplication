/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CODE;

/**
 *
 * @author exboy
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Manager extends Cashier {

    // Create a new account (can have different account types, ex: Cashier)
    public void createAccount(String username, String password, String userType) {
        User newUser = new User(username, password, userType);
        saveUserToFile(newUser);
    }

    public int saveUserToFile(User user) {
        try (FileReader fileReader = new FileReader("user.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length > 0 && userData[0].equals(user.getUsername())) {
                    System.out.println("Username already exists. Cannot save user.");

                    return 1;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the user file.");
            e.printStackTrace();
            return 0;
        }

        try (FileWriter fileWriter = new FileWriter("user.txt", true)) {

            String userData = user.getUsername() + "," + user.getPassword() + "," + user.getUserType() + "\n";
            fileWriter.write(userData);

            System.out.println("User saved successfully.");
            return 2;
        } catch (IOException e) {
            System.out.println("An error occurred while saving the user.");
            e.printStackTrace();
            return 0;
        }
    }

    // Add new products for different range and category
    public void addNewProduct(List<Product> products, String name, String category, double price, int stock) {
        Product newProduct = new Product(name, category, price, stock);
        products.add(newProduct);
    }

    public void saveProductsToFile(Product product) {
        try (FileReader fileReader = new FileReader("products.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            List<String> lines = new ArrayList<>();
            String line;
            boolean productExists = false;

            while ((line = bufferedReader.readLine()) != null) {
                String[] productDetails = line.split(",");
                String productNameFromFile = productDetails[0];

                if (productNameFromFile.equals(product.getName())) {
                    // Product name already exists in the file
                    productExists = true;

                    int currentStock = Integer.parseInt(productDetails[3]);
                    // int updatedStock = currentStock + product.getStock();
                    int updatedStock = product.getStock();
                    // Ask user for confirmation to update the existing data
                    int choice = JOptionPane.showConfirmDialog(null,
                            "Product already exists. Do you want to update the data?", "Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        // Update the stock count
                        product.setStock(updatedStock);
                    } else {
                        // User chose not to update the data, leave it as it is
                        System.out.println("Product data not updated.");
                        JOptionPane.showMessageDialog(null, "Product data not updated.");
                        lines.add(line); // Add the existing line back to the list
                    }
                } else {
                    lines.add(line); // Add the existing line to the list
                }
            }

            if (productExists) {
                // Save updated data to the file
                try (FileWriter fileWriter = new FileWriter("products.txt")) {
                    for (String updatedLine : lines) {
                        fileWriter.write(updatedLine);
                        fileWriter.write("\n");
                    }
                    String updatedProductData = product.getName() + "," + product.getCategory() + ","
                            + product.getPrice() + "," + product.getStock() + "\n";
                    fileWriter.write(updatedProductData);
                    System.out.println("Product data updated and saved to " + "products.txt" + " successfully.");
                    JOptionPane.showMessageDialog(null,
                            "Product data updated and saved to " + "products.txt" + " successfully.");
                } catch (IOException ex) {
                    System.out.println("Failed to save product data to " + "products.txt");
                    JOptionPane.showMessageDialog(null, "Failed to save product data to " + "products.txt");
                }
            } else {
                // Product name does not exist in the file, save the new product
                try (FileWriter fileWriter = new FileWriter("products.txt")) {
                    for (String lineToAdd : lines) {
                        fileWriter.write(lineToAdd);
                        fileWriter.write("\n");
                    }
                    String productData = product.getName() + "," + product.getCategory() + "," + product.getPrice()
                            + "," + product.getStock() + "\n";
                    fileWriter.write(productData);
                    System.out.println("Product saved to " + "products.txt" + " successfully.");
                    JOptionPane.showMessageDialog(null, "Product saved to " + "products.txt" + " successfully.");
                } catch (IOException ex) {
                    System.out.println("Failed to save product to " + "products.txt");
                    JOptionPane.showMessageDialog(null, "Failed to save product to " + "products.txt");
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to read products from " + "products.txt");
            JOptionPane.showMessageDialog(null, "Failed to read products from " + "products.txt");
        }

    }

}
