import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.io.Console;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Автоматизированное рабочее место продавца.");
            System.out.println("Выберите необходимое действие:");

            System.out.println("1. Оформить продажу.");
            System.out.println("2. Оформить поступление товара.");
            System.out.println("3. Создать новый товар.");
            System.out.println("4. Редактировать товар.");
            System.out.println("0. Выход из программы.");

           if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                if (choice == 1) {
                    createSaleReceipt();
                } else if (choice == 2) {
                    createPurchaseReceipt();
                } else if (choice == 3) {
                    createProduct();
                } else if (choice == 4) {
                    updateProduct();
                } else if (choice == 0) {
                    break;
                }else {
                    System.out.println("Вы ввели неправильно значение!");
                }
           } else {
               System.out.println("Вы ввели неправильно значение!");
           }
        }
        sc.close();
    }

    private static void createSaleReceipt() throws SQLException {
        int i=1;
        SaleReceipt saleDoc = new SaleReceipt(new Date(), 0);
        System.out.println("Для окончания ввода товаров введите '0'.");
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Товар № " + i);
            System.out.println("Введите id товара:");
            if (sc.hasNextInt()) {
                int idProd = sc.nextInt();
                if (idProd == 0) {
                    break;
                } else {
                    if(i == 1){
                        saleDoc.writeDoc();
                    }
                    System.out.println("Введите количество товара:");
                    int quan = sc.nextInt();
                    System.out.println("Введите цену товара:");
                    int price = sc.nextInt();
                    int sum = quan * price;
                    saleDoc.sum += sum;
                    saleDoc.writeProd(idProd, quan, price);
                    i += 1;
                }
            }
        }
        saleDoc.update();
    }
    private static void createPurchaseReceipt() throws SQLException {
        int i=1;
        PurchaseReceipt PurDoc = new PurchaseReceipt(new Date(), 0);
        System.out.println("Для окончания ввода товаров введите '0'.");
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Товар № " + i);
            System.out.println("Введите id товара:");
            if (sc.hasNextInt()) {
                int idProd = sc.nextInt();
                if (idProd == 0) {
                    break;
                } else {
                    if(i == 1){
                        PurDoc.writeDoc();
                    }
                    System.out.println("Введите количество товара:");
                    int quan = sc.nextInt();
                    System.out.println("Введите цену товара:");
                    int price = sc.nextInt();
                    int sum = quan * price;
                    PurDoc.sum += sum;
                    PurDoc.writeProd(idProd, quan, price);
                    i += 1;
                }
            }
        }
        PurDoc.update();
    }
    private static void createProduct() throws SQLException {
        Scanner sc = new Scanner(System.in);

        Product pd = new Product();
        System.out.println("Введите наименование товара:");
        pd.name = sc.nextLine();
        pd.create();
    }
    private static void updateProduct() throws SQLException {
        Scanner sc = new Scanner(System.in);

        Product pd = new Product();
        System.out.println("Введите id товара:");

        pd.id = sc.nextInt();
        pd.findById();
        if (pd.id == 0) {
            System.out.println("Товар не найден!");
            updateProduct();
        } else{
            System.out.println(pd.print());
            System.out.println("Укажите новое название или введите '0' для выхода:");
            String name = sc.nextLine();
            if(name!="0"){
                pd.name=sc.nextLine();
                pd.update();
            }
        }
    }
}


