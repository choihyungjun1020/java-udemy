package section2_thread_basic_construction._02_Inheritance;

public class Inheritance {
    public static void main(String[] args) {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello from " + Thread.currentThread().getName());
//            }
//        });

        Thread thread = new NewThread();

        thread.start();
    }

    /**
     * 스레드를 상속해, 새로운 스레드 클래스를 구현하면
     * static class method 를 호출하지 않고 모든 정보에 직접 접근이 가능하다.
     */
    private static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello from " + this.getName());
        }
    }
}
