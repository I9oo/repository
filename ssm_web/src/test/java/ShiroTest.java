import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;

public class ShiroTest {

    // (1) md5 加密,生成32位加密字符串
    @Test
    public void md5_1(){
        Md5Hash md5Hash = new Md5Hash("1234");
        System.out.println("加密：" + md5Hash.toString());
    }

    // (2) 加密加盐
    @Test
    public void md5_2(){
        String salt = "AAA";
        Md5Hash md5Hash = new Md5Hash("1234",salt);
        System.out.println(md5Hash);
    }

    // (3) 加密 加盐 随机盐
    @Test
    public void md5_3(){
        // 随机盐
        SecureRandomNumberGenerator srn = new SecureRandomNumberGenerator();
        String salt = srn.nextBytes().toHex();
        System.out.println("随即盐：" + salt);

        Md5Hash md5Hash = new Md5Hash("1234",salt);
        System.out.println(md5Hash.toString());
    }

    // (4) 加密 加盐 随机盐 迭代次数
    @Test
    public void md5_4(){
        // 随机盐
        SecureRandomNumberGenerator srn = new SecureRandomNumberGenerator();
        String salt = srn.nextBytes().toHex();

        Md5Hash md5Hash = new Md5Hash("1234",salt,3);// 迭代3次
        System.out.println(md5Hash.toString());
    }


    // (5) sha256 加密,生成32位加密字符串
    @Test
    public void sha256(){
        Sha256Hash hash = new Sha256Hash("1234");
        System.out.println("加密：" + hash.toString());
        System.out.println("加密：" + hash.toString().length());
    }
}
