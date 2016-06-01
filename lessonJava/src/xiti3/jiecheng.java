public class jiecheng {
    public static void main(String args[]) {
        int i,n; float s;
        for(n=0;n<=10;n=n+2) {
            if(n==0)
                System.out.println("0!=1\n");
            else {s=1;  for(i=1;i<=n;i++) s=s*i;
                System.out.println(n+"!="+s+"\n"); }
        }
    }
}