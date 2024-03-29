//submitted by Wing Hung Wu
//reference: https://medium.com/@ihorsokolyk/two-factor-authentication-with-java-and-google-authenticator-9d7ea15ffee6

package edu.deakin.sit764.config;

import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.Scanner;

import static edu.deakin.sit764.config.Redback_2FAConfig.getTOTPCode;

public class MainApplication {

    public static void main(String[] args) throws IOException, WriterException {
        String secretKey = "generated_in_Redback_2FAConfig";
        String email = "testing@gmail.com";
        String company = "Redback Company";
        String barcodeurl = Redback_2FAConfig.getGoogleAuthenticatorBarCode(secretKey, email, company);
        System.out.println(barcodeurl);
        Redback_2FAConfig.createQRCode(barcodeurl, "QRCode.png", 400, 400);
        
        System.out.println("Please enter the 6 digit code:");
        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();
        if (code.equals(getTOTPCode(secretKey))) {
            System.out.println("Logged in successful");
        } else {
            System.out.println("Invalid 2FA Code. Please try again.");
        }

    }

}

