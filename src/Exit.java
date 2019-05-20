public class Exit {
    private static Exit ourInstance = new Exit();
    private static int x=(int) ( Math.random()*50+100);
    private  static int y=(int) (Math.random()*30+40);
    private double  hiddenexit= Math.random()*30+100;
    public static Exit getInstance() {
        return ourInstance;
    }

    public static class Door {
        private int height=x-10;
        private int width =y+5;
        public int getHeight() {
            return height;
        }
        public int getWidth() {
            return width;
        }
    }

    private Exit() {
    }
    public double getHiddenexit() {
        return hiddenexit;
    }
}
