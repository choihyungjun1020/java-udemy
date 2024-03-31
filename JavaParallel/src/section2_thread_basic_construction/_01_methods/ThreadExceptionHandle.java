package section2_thread_basic_construction._01_methods;

public class ThreadExceptionHandle {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("Intentional Exception");
            }
        });

        thread.setName("Misbehaving thread");

        // 처음부터 전체 스레드에 해당되는 예외 핸들러를 지정
        // 스레드 내에서 발생한 예외가 어디서도 캐치되지 않으면, 핸들러가 호출됨
        // 캐치되지 않은 스레드와 예외를 출력, 리소스의 일부를 정리한다거나 추가 데이터를 로그해, 문제 발생 이후 트러블 슈팅 가능
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread " + t.getName() + " the error is " + e.getMessage());
            }
        });

        thread.start();
    }
}
