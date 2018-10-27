import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SpringBCry {
    //sha256+随即盐方式进行加密。
    public static void main(String[] args) {
        // Spring提供的高级加密支持
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密
        String str1 = passwordEncoder.encode("1234");
        String str2 = passwordEncoder.encode("1234");
        String str3 = passwordEncoder.encode("1234");
        // 打印
        //$2a$10$6ko/CrI34FRNchpWis5pE.IJ1VDGOZMPEo5XRWkwcY9rGYBEbdf8q
        //$2a$10$fnP5klM7LMaqNYFFV.xRxeNHeZPgomQXbi2ZvvnxbS4.XPksAOU9q
        //$2a$10$UuILRGWDW8ISct0k2C50NOsYu/akwRNruFGXOxiRbRtMnBfUzCYoe
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);

        // 密码匹配
        boolean flag1 = passwordEncoder.matches("1234", str1);
        boolean flag2 = passwordEncoder.matches("1234", str2);
        boolean flag3 = passwordEncoder.matches("1234", str3);
        System.out.println(flag1);
        System.out.println(flag2);
        System.out.println(flag3);
    }
}
