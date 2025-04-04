import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.*;

public class CriptografiaJava {
    public static void main(String[] args) throws Exception {
        // Gerar chave AES
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey chaveSecreta = keyGen.generateKey();

        // Interface gráfica para entrada de texto
        String mensagem = JOptionPane.showInputDialog("Digite a mensagem a ser criptografada:");

        // Criptografar
        String mensagemCriptografada = criptografar(mensagem, chaveSecreta);
        JOptionPane.showMessageDialog(null, "Mensagem Criptografada:\n" + mensagemCriptografada);

        // Descriptografar
        String mensagemDescriptografada = descriptografar(mensagemCriptografada, chaveSecreta);
        JOptionPane.showMessageDialog(null, "Mensagem Descriptografada:\n" + mensagemDescriptografada);
    }

    public static String criptografar(String texto, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        byte[] textoCriptografado = cipher.doFinal(texto.getBytes());
        return Base64.getEncoder().encodeToString(textoCriptografado);
    }

    public static String descriptografar(String textoCriptografado, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, chave);
        byte[] textoDecodificado = Base64.getDecoder().decode(textoCriptografado);
        byte[] textoOriginal = cipher.doFinal(textoDecodificado);
        return new String(textoOriginal);
    }
}
