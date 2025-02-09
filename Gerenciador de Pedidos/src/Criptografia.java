import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
    public static final String SHA256 = "SHA-256";
    public static final String MD5 = "MD5";

    protected String informacao;
    protected String padrao;

    public Criptografia (String informacao, String padrao) {
        this.informacao = informacao;
        this.padrao = padrao;
    }

    public String getInformacao() {
        return informacao;
    }

    public String getPadrao() {
        return padrao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

    public void setPadrao(String padrao) {
        this.padrao = padrao;
    }

    public String criptografar() {
        MessageDigest messageDigest;
        StringBuilder hexString = new StringBuilder();

        try {
            messageDigest = MessageDigest.getInstance(this.getPadrao());
            byte[] hash = messageDigest.digest(
                informacao.getBytes(StandardCharsets.UTF_8));
                hexString = new StringBuilder(2 * hash.length);
                for(int i = 0; i < hash.length; i++) {
                    String hex = Integer.toHexString(0xff & hash[i]);
                    if(hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }
        } catch (NoSuchAlgorithmException e) {
        }

        return hexString.toString().toUpperCase();
    }
    
}
