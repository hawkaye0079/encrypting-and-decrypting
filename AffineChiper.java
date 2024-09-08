import java.util.Scanner;

public class AffineChiper {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message: ");
        String a = sc.nextLine(); // Use nextLine to capture the entire message including spaces
        String b = encrypt(a);
        System.out.println("Encrypted message: " + b);
        String c = decrypt(b); // Pass the encrypted message to decrypt
        System.out.println("Decrypted message: " + c);
    }

    public static String encrypt(String plaintext) {
        String ct = "";
        int B = 0, c;
        char ch;
        for (int i = 0; i <= plaintext.length() - 1; i++) {
            ch = plaintext.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                B = ch - 97;
                c = (B * 7 + 2) % 26;  // Encryption formula
                ch = (char) (65 + c);
                ct = ct + ch;
            } else if (ch >= 'A' && ch <= 'Z') {
                B = ch - 65;
                c = (B * 7 + 2) % 26;
                ch = (char) (65 + c);
                ct = ct + ch;
            } else {
                ct = ct + ch;  // Preserve non-alphabet characters
            }
        }
        return ct;
    }

    public static String decrypt(String ciphertext) {
        String pt = "";
        int inverse = 1;
        while ((7 * inverse) % 26 != 1) {
            inverse++;  // Finding modular inverse of 7 modulo 26
        }
        for (int i = 0; i < ciphertext.length(); i++) {
            char ch = ciphertext.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                int value = (int) ch - 65;
                int dec = (((value - 2) * inverse) % 26 + 26) % 26;  // Decryption formula
                ch = (char) (dec + 65);  // Convert back to uppercase
                pt = pt + ch;
            } else if (ch >= 'a' && ch <= 'z') {
                int value = (int) ch - 97;
                int dec = (((value - 2) * inverse) % 26 + 26) % 26;
                ch = (char) (dec + 97);  // Convert back to lowercase
                pt = pt + ch;
            } else {
                pt = pt + ch;  // Preserve non-alphabet characters
            }
        }
        return pt;
    }
}
