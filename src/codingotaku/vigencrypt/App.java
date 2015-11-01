package codingotaku.vigencrypt;

import java.io.*;

public class App{

        private static final String characters = " !\"'(),.0123456789:;?ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        public static void main(String[] args){

                if(args.length != 1){
                        System.out.println("Error: Name of file not supplied");
                        System.exit(1);
                }

                String filename = args[0];

                System.out.println("Filename: "+filename);
            
                FileInputStream in = null;
                FileOutputStream out = null;

                try{
                        in = new FileInputStream(filename);
                        out = new FileOutputStream(filename+".vig");

                        Console console = System.console();

                        if(console == null){
                                System.out.println("Error: Non interactive mode");
                                System.exit(1);
                        }

                        System.out.println("Enter password: ");
                        char[] password = console.readPassword();
                        System.out.println("Beginning Encryption...");

                        int inputCharCode;

                        int passwordIndex = 0;

                        while((inputCharCode = in.read())!=-1){
                                char ch = (char) inputCharCode;

                                char passwordChar = password[passwordIndex];

                                int passwordCharValue = characters.indexOf(passwordChar);

                                if(characters.indexOf(ch) != -1){
                                        int inputCharValue = characters.indexOf(ch);
                                        int modifiedCharValue = (inputCharValue + passwordCharValue)%characters.length();

                                        ch = characters.charAt(modifiedCharValue);

                                        passwordIndex++;
                                        if(passwordIndex >= password.length){
                                                passwordIndex = 0;
                                        }
                                }

                                out.write(ch);
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
