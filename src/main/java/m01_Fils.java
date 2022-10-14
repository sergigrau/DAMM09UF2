public class m01_Fils {
    public static void main(String[] args) {

        Runnable runnable1 = new m01_FilLletres("fil1");
        Runnable runnable2 = new m01_Filnumeros("fil2");

        Thread fil1 = new Thread(runnable1);
        Thread fil2 = new Thread(runnable2);
        fil1.setPriority(5);
        fil2.setPriority(10);

        fil1.start();
        fil2.start();
    }
}
