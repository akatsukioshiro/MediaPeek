import java.io.*;
import java.util.Scanner;

class MediaPeek{
    public static void encode(){
        File f = new File("ByteOutput.txt");
        String fname = "";
        Scanner sc=new Scanner(System.in);
        try{
        while(!f.exists()){
            System.out.print("encode>>"+f.getName()+"Doesn't exist. Please enter a file path for the Modified Byte File : ");
            f = new File(sc.nextLine());
        }
        System.out.print("encode>>"+f.getName()+" found and selected.\n"+"encode>>Enter a new output filename : ");
        String fn = sc.nextLine();
        FileOutputStream fos = new FileOutputStream(new File(fn));
        sc =new Scanner(new FileReader(f));
        while(sc.hasNext())fos.write(sc.nextInt());
        fos.close();
        sc.close();
        System.out.println("Byte File Created : "+fn);
        }catch(IOException e){e.printStackTrace();}
        System.out.println("Encode complete");
    }
    public static void decode(){
        File f = new File("sample.jpg");
        File f1=new File("ByteOutput.txt"),f2=new File("SemiConverted.txt");
        int ctr=0;
        try{
            Scanner sc=new Scanner(System.in);
            while(!f.exists()){
                    System.out.print("decode>> Enter filepath to media file : ");
                    f=new File(sc.nextLine());
                }
            FileInputStream fis= new FileInputStream(f);
            FileWriter fw1 = new FileWriter(f1);
            PrintWriter pw2=new PrintWriter(f2);
            int readByte = fis.read();
            boolean toggle = true;
            while(readByte!=-1){
                fw1.write(readByte+" ");
                char ch = (char)readByte;
                if(Character.isDigit(ch)||
                    Character.isAlphabetic(readByte)||
                    ch==':'||ch=='<'||ch==' '||ch=='/'||
                    ch=='*'||ch=='>'||ch=='.'||ch=='?'||
                    ch=='\n'||ch=='-'||ch=='#'||ch=='\"'||
                    ch=='\r'||ch=='='){
                    toggle = false;
                    pw2.print(ch);
                }
                else {pw2.print(readByte);}
                readByte = fis.read();
            }
            fw1.close();
            pw2.close();
            sc.close();
            fis.close();
        }catch(IOException e){e.printStackTrace();}
        System.out.println("Decoding Completed. ");
    }
    public static void main(String[] args){
        System.out.println("MediaPeek is a program that helps decode any media file to two versions.\nByteOutput.txt are the byte output of the file.\nSemiConverted.txt is a semi-converted format of the image file.\nThe commands are encode, decode and exit.\nNote : Please use small sized files for easier handling.\n");
        boolean flag =true;
        Scanner sc=new Scanner(System.in);;
        String com="";
        while(flag) {
            sc.reset();
            System.out.print(">>");
            flag = false;
            while(!flag)
            try{
                com=sc.nextLine();
                flag=true;
                }catch(Exception e){
                flag = false;
            }
            switch(com){
                case "decode" : decode();break;
                case "encode" : encode();break;
                case "exit" : System.exit(0);break;
                default : System.out.println("No commands found");
            }
        }
    }
}
