public class Sova extends Animal {
    private double iq = 25;

    public Sova(String name, int heigh, int weight, int width, SkinColor color, double iq,double x, double y) {
        super(name, heigh, weight, width, color,x,y);
        this.iq = iq;

    }

    public Sova() {
        super();
    }

    public void setIq(double iq) {
        this.iq = iq;
    }

    public double getIq() {
        return iq;
    }

    public Sova(double iq) {
        this.iq = iq;
    }

    public Sova(String name, double iq) {
        super(name);
        this.iq = iq;
    }
    public Sova(String name, double iq, SkinColor color) {
        super(name,color);
        this.iq = iq;
    }

    @Override
    public String toString() {
        return "Sova{" +
                "name='" + super.getName() + '\'' +
                ", height=" + super.getHeight() +
                ", weight=" + super.getWeight() +
                ", width=" + super.getWidth() +
                ", color=" + super.getColor() +
                ", x=" + super.getX() +
                ", y=" + super.getY() +
                ", birthDay=" + super.getBirthDay().toString() +
                ", iq=" + this.iq +
                '}';
    }

    @Override
    public void lookAt(PartsOfTheRoom part) {
        switch (part) {
            case FLOOR:
                System.out.println(getName() + " посмотрела на пол");
                System.out.println(getName() + " посмотрела на пол");
            case CEILING:
                System.out.println(getName() + " взглянула на потолок");
            case WALL:
                System.out.println(getName() + " Уставилась на стену");

        }
    }

    @Override
    public void sitOn(ThingsInRoom thing) {
        switch (thing) {
            case CHAIR:
                System.out.println(getName() + " села на стул");
            case SOFA:
                System.out.println(getName() + " уселась на диван");
            case TABLE:
                System.out.println(getName() + " присела на стол");
        }
    }

    private void GoOut() {
        System.out.println(getName() + " вышла");
    }

    @Override
    public void sayPhrase() {
        if (getColor() == SkinColor.YELLOW)
            System.out.println(getName());
        else if (getColor() == SkinColor.BLUE)
            System.out.println("IT's MOre than a University" + " © " + getName());
        else
            System.out.println("..." + " © " + getName());
    }

    public void askForHelp(Animal animal) {
        System.out.println("Помогите мне найти выход");
        animal.sayPhrase();
        animal.searchExit();
    }

    @Override
    public void searchExit() {
        Exit exit = Exit.getInstance();
        double k = getWeight() / getHeight();
        if (iq * (1 + k) > exit.getHiddenexit()) {
            Sova.EurekaLamp lamp = new Sova.EurekaLamp();
            lamp.turnOnTheLight();
            System.out.println("Я нашла выход!" + " © " + getName());
            lamp.turnOffTheLight();
            lamp = null;
            GoOut();
        }
    }
}
