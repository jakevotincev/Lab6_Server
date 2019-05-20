public class WinniePooh extends Animal {
    private double iq = 20;

    public WinniePooh(String name, int heigh, int weight, int width, SkinColor color, double iq,double x,double y) {
        super(name, heigh, weight, width, color,x,y);
        this.iq = iq;

    }

    public void setIq(double iq) {
        this.iq = iq;
    }

    public WinniePooh(double iq) {
        this.iq = iq;
    }

    public WinniePooh(String name, double iq) {
        super(name);
        this.iq = iq;
    }

    public double getIq() {
        return iq;
    }

    public WinniePooh(String name) {
        super(name);
    }

    public WinniePooh() {
        super();
    }

    @Override
    public void lookAt(PartsOfTheRoom part) {
        switch (part) {
            case FLOOR:
                System.out.println(getName() + " посмотрел на пол");
            case CEILING:
                System.out.println(getName() + " уставился на потолок");
            case WALL:
                System.out.println(getName() + " взглянул на стену");

        }
    }

    @Override
    public void sitOn(ThingsInRoom thing) {
        switch (thing) {
            case CHAIR:
                System.out.println(getName() + " сел на стул");
            case SOFA:
                System.out.println(getName() + " уселся на диван");
            case TABLE:
                System.out.println(getName() + " присел на стол");
        }
    }

    @Override
    public void sayPhrase() {
        if (getColor() == SkinColor.BROWN)
            System.out.println("В голове моей опилки да, да, да!" + " © " + getName());
        else if (getColor() == SkinColor.ORANGE)
            System.out.println("Нужно делать так, как нужно. А как не нужно, делать не нужно!" + " © " + getName());
        else if (getColor() == SkinColor.BLUE)
            System.out.println("IT's MOre than a University" + " © " + getName());
        else
            System.out.println("Я неправильный Винни-Пух" + " © " + getName());
    }

    @Override
    public String toString() {
        return "WinnieThePooh{" +
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
            WinniePooh.EurekaLamp lamp = new WinniePooh.EurekaLamp();
            lamp.turnOnTheLight();
            System.out.println("Ура! Я нашел выход!" + " © " + getName());
            lamp.turnOffTheLight();
            lamp = null;
            GoOut();
        }
    }

    private void GoOut() {
        Exit.Door door = new Exit.Door();
        if (getWidth() <= door.getWidth()) {
            System.out.println(getName() + " вышел");
        } else System.out.println(getName() + " застрял");
    }

}


