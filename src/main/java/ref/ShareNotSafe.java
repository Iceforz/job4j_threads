package ref;

import java.security.spec.RSAOtherPrimeInfo;

public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("name");
        User user2 = User.of("secondName");
        cache.add(user);
        cache.add(user2);
        Thread first = new Thread(
                () -> {
                    user.setName("rename");
                }
        );
        first.start();
        first.join();
        System.out.println(cache.findById(1).getName());
        System.out.println(cache.findAll());
    }
}
