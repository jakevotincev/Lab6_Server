import java.io.Serializable;
import java.util.Date;

public abstract class Animal implements Seable, Searchable, Sitable, Sayable, Comparable<Animal>, Serializable {
    private static final long serialVersionUID =1;
    private String name = "Животное";
    private double height;
    private double weight;
    private double width;
    private SkinColor color;
    private double x;
    private double y;
    private Date birthDay;

    public Animal() {
        birthDay=new Date();
    }

    public Animal(String name, double height, double weight, double width, SkinColor color, double x, double y) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.width = width;
        this.color = color;
        this.x = x;
        this.y = y;
        birthDay=new Date();
    }

    public Animal(String name) {
        this.name = name;
        birthDay=new Date();
    }

    public Animal(String name, SkinColor color) {
        this.name = name;
        this.color = color;
        birthDay=new Date();
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setColor(String color) {
        switch (color.toLowerCase()) {
            case "pink": {
                this.color = SkinColor.PINK;
            }
            break;
            case "orange": {
                this.color = SkinColor.ORANGE;
            }
            break;
            case "yellow": {
                this.color = SkinColor.YELLOW;
            }
            break;
            case "blue": {
                this.color = SkinColor.BLUE;
            }
            break;
            case "brown": {
                this.color = SkinColor.BROWN;
            }
            break;
        }
    }

    public abstract void lookAt(PartsOfTheRoom part);

    public abstract void sitOn(ThingsInRoom thing);

    public SkinColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", width=" + width +
                ", color=" + color +
                ", x=" + x +
                ", y=" + y +
                ", birthDay=" + birthDay.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        return name.equals(animal.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = 25;
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(width);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public double getHeight() {

        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getWeight() {

        return weight;
    }

    class EurekaLamp {
        private boolean light;   // если true => включена

        public void turnOnTheLight() {
            System.out.println("над " + getName() + " зажглась лампочка");
            light = true;
        }

        public void turnOffTheLight() {
            light = false;
        }
    }

    @Override
    public int compareTo(Animal o) {
        return name.compareTo(o.getName());
    }
}
