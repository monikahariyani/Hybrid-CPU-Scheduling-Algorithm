import java.util.*;

public class HybridAlgorithm1{
         public static void main(String[]args){

           Scanner sc = new Scanner(System.in);
         
            System.out.println("enter number of processes");
            int l = sc.nextInt();
            int[]AT = new int[l];
            int[]BT = new int[l];
            System.out.println("Enter arrival time");
            for(int i=0;i<l;i++){
            System.out.print("Element"+(i+1)+":");
            AT[i]=sc.nextInt();
            }
            System.out.println("enter burst time");
            for(int i=0;i<l;i++){
            System.out.print("Element"+(i+1)+":");
            BT[i]=sc.nextInt();
            }


            int TQ=2;

            int switchPoint = 3;
            int n = AT.length;

           int[]RT=Arrays.copyOf(BT,n);
           int[]CT=new int[n];
           int[]WT=new int[n];
           int[]TAT=new int[n];
           int[]RT_Times=new int[n];
           boolean[] firstExecution = new boolean[n];
           int time=0;
           int completed=0;


           boolean done;
        
           System.out.println("Execution order:");
     do 
     {
       done = true;
         for(int i=0;i<n;i++){
           if(RT[i]>0){
       done = false;
   
       if(RT[i]<=switchPoint){
       int shortest = i;
          for(int j = 0;j<n ; j++){
        if(AT[j]<=time && RT[j]>0 && RT[j]<RT[shortest]){
          shortest = j;
    }
    }
if(!firstExecution[shortest]){
RT_Times[shortest]=time-AT[shortest];
firstExecution[shortest]=true;
}
    System.out.print("P"+(shortest+1)+" ");
    time += RT[shortest];
     
     RT[shortest]=0;
     CT[shortest]=time;
     completed++;
     }

 else{
if(!firstExecution[i]){
RT_Times[i]=time-AT[i];
firstExecution[i]=true;
}
    System.out.println("P"+(i+1)+" ");


             if(RT[i]>TQ){
                     time+= TQ;
                     RT[i]-=TQ;
                   }
            
             else{
                time +=RT[i];
                RT[i]=0;
                CT[i]=time;
                completed++;
                }
             }
          }
       }

    } while(completed<n);
      for (int i =0;i<n;i++){
TAT[i]=CT[i]-AT[i];
WT[i]=TAT[i]-BT[i];
}
double avgWT = 0 ,avgTAT = 0, avgRT = 0;

for(int i=0;i<n;i++){
avgWT += WT[i];
avgTAT += TAT[i];
avgRT += RT_Times[i];
}
avgWT /= n;
avgTAT /= n;
avgRT /= n;
double throughput = (double)n/time;

          System.out.println("\n\nProcess summary;");

          System.out.println("process\tArrival\tBurst\tCompletion\tWaiting\t\tTurnaround\tResponse");

     for(int i=0;i<n;i++){

            

          System.out.println("P"+(i+1)+"\t"+AT[i]+"\t"+BT[i]+"\t"+CT[i]+"\t\t"+WT[i]+"\t\t"+TAT[i]+"\t\t"+RT_Times[i]);
      }

         System.out.println("\n total time taken by all processes:"+time);
         System.out.println("Average Waiting Time "+avgWT);
         System.out.println("Average Turnaround time"+avgTAT);
         System.out.println("Average Response Time"+avgRT);
         System.out.println("Throughput:"+throughput);
  }
} 

