public class Toy {
    private int id;
    private String name;
    private int quantity;
    private double weigth;

    public Toy(int id, String name, int quantity, double weigth){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weigth = weigth;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getQuntity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public double getWeigth(){
        return weigth;
    }

    public void setWeigth(double weigth){
        this.weigth = weigth;
    }

    public int getQuantity() {
        return 0;
    }

    public void setWeight(double weight) {
    }

    public double getWeight() {
        return 0;
    }
}