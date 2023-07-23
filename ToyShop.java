import java.io.*;

import java.util.ArrayList;

import java.util.List;

import java.util.Random;

import java.util.Scanner;


public class ToyShop {

    private List<Toy> toys;


    public ToyShop() {

        toys = new ArrayList<>();

    }


    public void addToy(Toy toy) {

        toys.add(toy);

    }




    public void setToyWeight(int id, double weight) {

        for (Toy toy : toys) {

            if (toy.getId() == id) {

                toy.setWeight(weight);

                break;

            }

        }

    }



    public void drawToys() {

        if (toys.isEmpty()) {

            System.out.println("В магазине нет игрушек.");

            return;

        }


        double totalWeight = 0;

        for (Toy toy : toys) {

            totalWeight += toy.getWeight();

        }



        Random random = new Random();

        double randomNumber = random.nextDouble() * totalWeight;


        double currentWeight = 0;

        Toy chosenToy = null;

        for (Toy toy : toys) {

            currentWeight += toy.getWeight();

            if (randomNumber <= currentWeight) {

                chosenToy = toy;

                break;

            }

        }


        if (chosenToy != null) {

            System.out.println("Поздравляем! Вы выиграли игрушку: " + chosenToy.getName());

            if (chosenToy.getQuantity() > 0) {

                chosenToy.setQuantity(chosenToy.getQuantity() - 1);

            } else {

                System.out.println("К сожалению, данная игрушка закончилась.");

            }

        } else {

            System.out.println("Ничего не выиграли.");

        }

    }


    public void saveToysToFile(String filename) {

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(filename);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(toys);

            objectOutputStream.close();

            fileOutputStream.close();

            System.out.println("Список игрушек успешно сохранен в файл " + filename);

        } catch (IOException e) {

            System.out.println("Ошибка при сохранении списка игрушек в файл " + filename);

            e.printStackTrace();

        }

    }


    public void loadToysFromFile(String filename) {

        try {

            FileInputStream fileInputStream = new FileInputStream(filename);

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            toys = (List<Toy>) objectInputStream.readObject();

            objectInputStream.close();

            fileInputStream.close();

            System.out.println("Список игрушек успешно загружен из файла " + filename);

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Ошибка при загрузке списка игрушек из файла " + filename);

            e.printStackTrace();

        }

    }



    public static void main(String[] args) {

        ToyShop toyShop = new ToyShop();


        toyShop.addToy(new Toy(1, "Мяч", 10, 20));

        toyShop.addToy(new Toy(2, "Кукла", 5, 30));

        toyShop.addToy(new Toy(3, "Машинка", 8, 50));



        Scanner scanner = new Scanner(System.in);

        boolean exit = false;



        while (!exit) {

            System.out.println("Выберите действие:");

            System.out.println("1. Розыгрыш игрушки");

            System.out.println("2. Добавление новой игрушки");

            System.out.println("3. Изменение веса (частоты выпадения) игрушки");

            System.out.println("4. Сохранить список игрушек в файл");

            System.out.println("5. Загрузить список игрушек из файла");

            System.out.println("6. Выйти");



            int choice = scanner.nextInt();



            switch (choice) {

                case 1:

                    toyShop.drawToys();

                    break;

                case 2:

                    System.out.println("Введите id новой игрушки:");

                    int id = scanner.nextInt();

                    System.out.println("Введите название новой игрушки:");

                    String name = scanner.next();

                    System.out.println("Введите количество новой игрушки:");

                    int quantity = scanner.nextInt();

                    System.out.println("Введите вес (частоту выпадения) новой игрушки:");

                    double weight = scanner.nextDouble();

                    toyShop.addToy(new Toy(id, name, quantity, weight));

                    break;

                case 3:

                    System.out.println("Введите id игрушки, у которой необходимо изменить вес:");

                    int toyId = scanner.nextInt();

                    System.out.println("Введите новый вес (частоту выпадения) игрушки:");

                    double newWeight = scanner.nextDouble();

                    toyShop.setToyWeight(toyId, newWeight);

                    break;

                case 4:

                    System.out.println("Введите имя файла для сохранения списка игрушек:");

                    String saveFilename = scanner.next();

                    toyShop.saveToysToFile(saveFilename);

                    

                    break;

                case 5:

                    System.out.println("Введите имя файла для загрузки списка игрушек:");

                    String loadFilename = scanner.next();

                    toyShop.loadToysFromFile(loadFilename);

                    break;

                case 6:

                    exit = true;

                    break;

                default:

                    System.out.println("Неверный выбор. Попробуйте еще раз.");

                    break;

            }

        }

        scanner.close();

    }

}
