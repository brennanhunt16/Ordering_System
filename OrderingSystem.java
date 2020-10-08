/**
 * ---------------------------------------------------------------------------
 *File name: OrderingSystem
 * Project: CSV formatted ordering system
 * ---------------------------------------------------------------------------
 *Author's name and email: Brennan Hunt


 * ---------------------------------------------------------------------------*/
import java.util.Scanner;

import java.text.DecimalFormat;

public class OrderingSystem {


    public static void main(String[] args){

        Scanner kb = new Scanner(System.in);

        //String csvString = "Product ID", "Quantity Ordered", "Price Per";

        boolean menuLoop;    //declarinng boolean that will be used in loop

        String productID = "";  //string variable to hold id

        String quantity = ""; //string variable to hold quanity

        double productPrice = 0; //setting product price to 0

        String order = "ProductID " + " Quantity " + " Price   " + "Cost \r\n";  //making the string text for the headings of the order reciept

        double total = 0; //creating the variable that will hold the to orders total cost

        String productName = ""; //declarinng string to store product names

        double priceOfThatItem = 0;  //declaring total price for items

        String finalOrder = "\r\n";  //creating finalOrder to store the receipt values

        do {
            System.out.println("Enter the number of what you would like to do next");
            //allow user to make selections from menu
            System.out.println("1. Add product to your order");
            //System.out.println("3. Remove product");
            System.out.println("4. Finish your order and display a receipt ");


            String userMenuChoice = kb.nextLine();  //getting user choice
            int whole = Integer.parseInt(userMenuChoice); //turning input from string to a integer


            if (whole == 1) {
                String list = getProductList(); //getting the product list from method and saving it in variable list
                System.out.println(list);
                System.out.println("Enter the Product ID to add it to your order");
                productID = kb.nextLine();     //getting the productID from the user for desired product

                System.out.println("Now enter the quantity desired");
                quantity = kb.nextLine();    //quantity from user as string

                double quant = Double.parseDouble(quantity); //converting to decimal

                if (quant < 0) {
                    quant = 0;
                    System.out.println("Quantity cant be negative");

                }



                productPrice = getProductPrice(productID); //calling get price method and saving the result as the price of that product
                priceOfThatItem = productPrice * quant; //getting the total cost of that item
                total += priceOfThatItem;        			  //increment the total final price of the reciept
                order = order + productID + "           " + quantity + "       " +  productPrice + "     " + priceOfThatItem + "\r\n";


                productName = findProductName(productID);
                finalOrder = finalOrder + productName + "     " + productPrice + "     " + priceOfThatItem + "\r\n";

                order = addProductToOrder(order, productID, quant);


                System.out.println(order);


            }

            //else if (whole == 3) {
            //
            //	double quant = Double.parseDouble(quantity);
            //	System.out.println("Enter the ID of the product you wish to remove ");
            //	productID = kb.nextLine();
            //	productPrice = getProductPrice(productID);
            //	productPrice = productPrice * quant;
            //
            //
            //	order = removeProductInOrder(order, productID, productPrice, total);
            //	order = order.replace(quantity, "0");
            //	order = order - priceOfThatItem;
            //
            //	total = total - productPrice;
            //
            //
            //	System.out.println(order);
            //
            //
            //
            //}


            else if (whole == 4) {
                //call print reciept method and print receipt
                DecimalFormat df = new DecimalFormat("##.##");
                menuLoop = false;								//breaking while loop
                System.out.println("Product Name   Price    Cost");
                System.out.print("----------------------------");
                String receipt = calcReceipt(finalOrder);      //retreving receipt from method
                System.out.println(receipt);
                System.out.println("Your total is $" + df.format(total)); //printing out the final total
                break;
            }

            else if (whole > 4) {
                System.out.println("Invalid Menu Selection");
            }



        } while (menuLoop = true);



    }

    public static String getProductList() {

        String productList =  "Product ID | Product Name | Price Per ($) \r\n"  //saving the product list in a variable(productList) and returning the list
                + "----------------------------------------- \r\n"
                + "H2O        | Water Bottle |      1.25     \r\n"
                + "----------------------------------------- \r\n"
                + "AU10K      |10 Karat Gold |	 55.00    \r\n"
                + "----------------------------------------- \r\n"
                + "CD         | Compact Disk |     5.30      \r\n"
                + "----------------------------------------- \r\n"
                + "FD         | Floppy Disk  |     0.35      \r\n"
                + "----------------------------------------- \r\n"
                + "USBFD      |USB FlashDrive|     8.76      \r\n"
                + "----------------------------------------- \r\n";
        return productList;

    }


    //when they enter what they want add after entering 1 on the menu, you call this method
    //to retrive price of product and multiply by how much they want
    public static double getProductPrice(String productID) {
        double price = -1;				//creating price variable for different items to store the price
        if (productID.equals("H2O")) {
            price = 1.25;
        }
        else if (productID.equals("AU10K")) {
            price = 55.00;
        }
        else if (productID.equals("CD")) {
            price = 5.30;
        }
        else if (productID.equals("FD")) {
            price = 0.35;
        }
        else if (productID.equals("USBFD")) {
            price = 8.76;
        }
        else {
            System.out.println("Invalid ID");
        }


        return price;


    }


    //This method will accept the id of a product and return the productâ€™s name.
    public static String findProductName(String productID) {
        String prodName = "";
        if (productID.equals("H2O")) {				//assigning the productIDs to product names
            prodName = "Water Bottle";
        }
        else if (productID.equals("AU10K")) {
            prodName = "10 Karat Gold";
        }
        else if (productID.equals("CD")) {
            prodName = "Compact Disk";
        }
        else if (productID.equals("FD")) {
            prodName = "Floppy Drive";
        }
        else if (productID.equals("USBFD")) {
            prodName = "USB FlashDrive";
        }

        return prodName;
    }



    public static String addProductToOrder(String order, String productID, double quantity) {
        //This method will accept the userâ€™s current order, the id of the product they wish to add, and the quantity they wish to purchase.
        //If the product id is valid the method will append the new product to the order and return the updated order.
        DecimalFormat df = new DecimalFormat("##.##");
        double pPrice = -1;

        double quant = quantity;

        if (productID.equals("H2O")) {
            pPrice = 1.25;
        }
        else if (productID.equals("AU10K")) {
            pPrice = 55.00;
        }
        else if (productID.equals("CD")) {
            pPrice = 5.30;
        }
        else if (productID.equals("FD")) {
            pPrice = 0.35;
        }
        else if (productID.equals("USBFD")) {
            pPrice = 8.76;
        }
        double costOfItem = quant * pPrice;
        String s=Double.toString(costOfItem);


        //order = order + productID + "     " +  quantity + "      " + pPrice + "   " + df.format(costOfItem) + "\r\n";
        return order;

    }



    public static String readCurrentOrder(String order) {
        //This method will return a String representing the userâ€™s current order
        return order;

    }



    public static String calcReceipt(String finalOrderorder) {



        return finalOrderorder;

    }



    //public static String removeProductInOrder(String order, String productID, double productPrice, double total) {
    //
    //
    //	order = order.replace(productID, "REMOVED");
    //
    //
    //
    //	return order;
    //}
}
