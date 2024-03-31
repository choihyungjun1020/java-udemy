package section2_thread_basic_construction._02_Inheritance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 상속 예제
 */
public class InheritanceExample {
    public static final int MAX_PASSWORD = 9999;
    public static void main(String[] args) {
        Random random = new Random();

        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        List<Thread> threads = new ArrayList<>();
        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceThread());

        for (Thread thread : threads) {
            thread.start();
        }
    }

    /**
     * 금고 클래스
     */
    private static class Vault {
        private int password;
        public Vault(int password) {
            this.password = password;
        }

        /**
         * 비밀번호가 맞는지 확인하는 로직, 잠재적 해커를 막기 위해 5초의 sleep 을 부여
         * @param guess 입력한 비밀번호
         * @return 비밀번호가 맞는지의 여부
         */
        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }

            return this.password == guess;
        }
    }

    /**
     * 금고를 해킹할 해커 스레드
     */
    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }

    /**
     * 해커 스레드를 확장하는 AscendingHackerThread
     * 비밀번호를 해킹할 때, 모든 번호를 오름차순으로 평가한다.
     */
    private static class AscendingHackerThread extends HackerThread {
        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < MAX_PASSWORD; guess++) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    /**
     * 해커 스레드를 확장하는 DescendingHackerThread
     * 비밀번호를 해킹할 때, 모든 번호를 내림차순으로 평가한다.
     */
    private static class DescendingHackerThread extends HackerThread {
        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD; guess >= 0; guess--) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    /**
     * 스레드 클래스를 확장한 경찰 클래스
     */
    private static class PoliceThread extends Thread {
        @Override
        public void run() {
            for (int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                System.out.println(i);
            }

            System.out.println("Game over for you hackers");
            System.exit(0);
        }
    }
}
