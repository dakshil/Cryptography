import java.io.*;
import java.util.*;

public class customCipher
{
  public static void main(String args[])
  {
    //encryption
    Scanner sc=new Scanner(System.in);
    System.out.println("enter plaintext");
    String plaintext=sc.nextLine();
    int len=plaintext.length();
    char plain[]=new char[len];
    char cipher1[]=new char[len];
    plain=plaintext.toCharArray();
    int p=(int)Math.pow(2,len-1); 
    char cipher2[]=new char[0]; 
    char cipher3[]=new char[0];
    if((p+1)%4==0)
    {
      cipher2=new char[p+3];
      cipher3=new char[p+3];
      p=p+3;
      System.out.println(p+" 1");
    }
    else if((p+1)%4==1)
    {
      cipher2=new char[p+4];
      cipher3=new char[p+4];
      p=p+4;
      System.out.println(p+" 2");
    }
    else if((p+1)%4==2)
    {
      cipher2=new char[p+5];
      cipher3=new char[p+5];
      p=p+5;
      System.out.println(p+" 3");
    }   
    else if((p+1)%4==3)
    {
      cipher2=new char[p+6];
      cipher3=new char[p+6];
      p=p+6;
      System.out.println(p+" 4");
    }   
    System.out.println("plaintext: "+plaintext);
    for(int i=0;i<p;i++)
    {
      cipher2[i]='#';
      
    }
    for(int i=0;i<len;i++)
    {
      cipher1[i]=(char)((((int)plain[i]-97)+3)%26 + 97);
      cipher2[(int)Math.pow(2,i)]=cipher1[i];
    }
    Random randomGenerator = new Random();    
    for(int i=0;i<p;i++)
    {
      if(cipher2[i]=='#')
      {
        int randomInt = randomGenerator.nextInt(26);
        cipher2[i]=(char)(randomInt+97);
      }
    }
    System.out.println("");
    System.out.print("ciphertext after substitution: ");
    int n=p/4;
    for(int i=0;i<n*4;i++)
    {
      System.out.print(cipher2[i]);
    }
    System.out.println("");
    char cip31[]=new char[n];
    char cip32[]=new char[n];
    char cip33[]=new char[n];
    char cip34[]=new char[n];
    for(int i=0;i<n;i++)
    {
      cip31[i]=cipher2[i];
      cip32[i]=cipher2[i+n];
      cip33[i]=cipher2[i+2*n];
      cip34[i]=cipher2[i+3*n];
    }
    int sum=n-1;
    int x=0,y=n-1,z=0,g=n-1,e=0;
    while(sum>=0)
    {
      cipher3[e]=cip31[x];
      x++;
      e++;
      sum--;
      cipher3[e]=cip34[y];
      y--;
      e++;
      
    }
    sum=n-1;
    while(sum>=0)
    {
      cipher3[e]=cip32[z];
      z++;
      e++;
      sum--;
      cipher3[e]=cip33[g];
      g--;
      e++;
      
    }
    String ciphertext = String.valueOf(cipher3);
    System.out.print("ciphertext after columnar: ");
    System.out.println(ciphertext);
    //decryption
    len=ciphertext.length();
    char cipher[]=new char[len];
    cipher=ciphertext.toCharArray();
    n=len/4-1;
    sum=n;
    x=0;y=n;z=0;g=n;e=0;
    while(sum>=0)
    {
      cip31[x]=cipher[e];
      x++;
      e++;
      sum--;
      cip34[y]=cipher[e];
      y--;
      e++;
      
    }
    sum=n;
    while(sum>=0)
    {
      cip32[z]=cipher[e];
      z++;
      e++;
      sum--;
      cip33[g]=cipher[e];
      g--;
      e++; 
    }
    n=n+1;
    for(int i=0;i<n;i++)
    {
      cipher2[i]=cip31[i];
      cipher2[i+n]=cip32[i];
      cipher2[i+2*n]=cip33[i];
      cipher2[i+3*n]=cip34[i];
    }
    for(int i=0;i<n*4;i++)
    {
      System.out.print(cipher2[i]);
    }
    //find 2 positions at power of 2 and put in array. subtract key
    int temp=0;
    int i;
    for(i=0;i<n*4;i++)
    {
      if(temp<n*4)
      temp=(int)Math.pow(2,i);
      else
      break;
    }
    int max=i-1;
    char plain2[]=new char[max];
    int max2=0;        
    System.out.println();   
    for(i=0;i<max;i++)
    {
      plain2[i]=cipher2[(int)Math.pow(2,max2)];
      plain2[i]=(char)(((int)plain2[i]-3));      
      max2++;
    }
    System.out.print("Deciphered plaintext: ");
    System.out.println(plain2); 
  }
}
