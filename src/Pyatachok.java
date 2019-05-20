public class Pyatachok extends Animal {
    private double iq = 15;

    public Pyatachok(String name, int high, int weight, int width, SkinColor color, double iq, double x, double y) {
        super(name, high, weight, width, color,x,y);
        this.iq = iq;

    }

    public void setIq(double iq) {
        this.iq = iq;
    }

    public Pyatachok(double iq) {
        this.iq = iq;
    }

    public Pyatachok(String name, double iq) {
        super(name);
        this.iq = iq;
    }

    public Pyatachok(String name) {
        super(name);
    }

    public Pyatachok() {
        super();
    }

    public double getIq() {
        return iq;
    }

    @Override
    public void lookAt(PartsOfTheRoom part) {
        switch (part) {
            case FLOOR:
                System.out.println(getName() + " уставился на пол");
            case CEILING:
                System.out.println(getName() + " посмотрел на потолок");
            case WALL:
                System.out.println(getName() + " взглянул на стену");

        }
    }

    @Override
    public void sitOn(ThingsInRoom thing) {
        switch (thing) {
            case CHAIR:
                System.out.println(getName() + " присел на стул");
            case SOFA:
                System.out.println(getName() + " уселся на диван");
            case TABLE:
                System.out.println(getName() + " сел на стол");
        }
    }

    @Override
    public void sayPhrase() {
        if (getColor() == SkinColor.PINK)
            System.out.println("Кажется, дождь собирается…" + " © " + getName());
        else if (getColor() == SkinColor.BLUE)
            System.out.println("IT's MOre than a University" + " © " + getName());
        else System.out.println("Я странный пятачок" + " © " + getName());
    }

    @Override
    public String toString() {
        return "Pyatachok{" +
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
    public void searchExit() {
        Exit exit = Exit.getInstance();
        double k = getWeight() / getHeight();
        if (iq * (1 + k) > exit.getHiddenexit()) {
            Pyatachok.EurekaLamp lamp = new Pyatachok.EurekaLamp();
            lamp.turnOnTheLight();
            System.out.println("Кажется я нашел выход..." + " © " + getName());
            lamp.turnOffTheLight();
            lamp = null;
            GoOut();
        }
    }

    private void GoOut() {
        boolean forget;
        if (Math.round(Math.random()) == 1) forget = true;
        else forget = false;
        if (forget == true) {
            System.out.println(getName() + " вышел, вернулся к глухой Совунье, чтобы сказать,где выход, и забыл, продолжает поиски...");
            searchExit();
        }

    }

}