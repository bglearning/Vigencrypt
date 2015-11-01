package codingotaku.vigencrypt;

import java.io.*;

public class App{

        private static final String characters = " !\"'(),.0123456789:;?ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        public static void main(String[] args){

                if(args.length!=2){
                        System.out.println("Error: Name of file not supplied");
                        System.exit(1);
                }

                String option = args[0];
                String filename = args[1];


                System.out.println("Filename: "+filename);
            
                FileInputStream in = null;
                FileOutputStream out = null;

                try{
                        in = new FileInputStream(filename);

                        String outFilename = filename;

                        boolean encrypt = true;

                        if(option.equals("enc")){
                                outFilename += ".vig";
                                encrypt = true;
                        }else if(option.equals("dec")){
                                outFilename += ".giv";
                                encrypt = false;
                        }else{
                                System.out.println("Error: Invalid option");
                                System.exit(1);
                        }
                        
                        out = new FileOutputStream(outFilename);

                        Console console = System.console();

                        if(console == null){
                                System.out.println("Error: Non interactive mode");
                                System.exit(1);
                        }

                        System.out.println("Enter password: ");
                        char[] password = console.readPassword();
                        if(encrypt){
                            System.out.println("Beginning Encryption...");
                        }else{
                            System.out.println("Beginning Decryption...");
                        }

                        int inputCharCode;

                        int passwordIndex = 0;

                        while((inputCharCode = in.read())!=-1){
                                char ch = (char) inputCharCode;

                                char passwordChar = password[passwordIndex];

                                int passwordCharValue = characters.indexOf(passwordChar);

                                if(characters.indexOf(ch) != -1){
                                        int inputCharValue = characters.indexOf(ch);
                                        int modifiedCharValue;
                                        if(encrypt){
                                            modifiedCharValue = (inputCharValue + passwordCharValue)%characters.length();
                                        }else{
                                            modifiedCharValue = (inputCharValue - passwordCharValue);
                                            if(modifiedCharValue < 0){
                                                    modifiedCharValue += characters.length();
                                            }
                                        }

                                        ch = characters.charAt(modifiedCharValue);

                                        passwordIndex++;
                                        if(passwordIndex >= password.length){
                                                passwordIndex = 0;
                                        }
                                }

                                out.write(ch);
                        }

                        if(encrypt){
                            System.out.println("Encryption done!");
                        }else{
                            System.out.println("Decryption done!");
                        }
                }catch(IOException ioexc){
                        System.out.println(ioexc.getMessage());
                }finally{
                        try{
                            if(in != null){
                                    in.close();
                            }
                            if(out != null){
                                    out.close();
                            }
                        }catch(IOException ioexc){
                                ioexc.printStackTrace();
                        }
                }

        }

}
