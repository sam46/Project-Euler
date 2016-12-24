
public class Euler19 {

	static String days[] = {	"mon",	"tue",	"wed",	"thu",	"fri",	"sat",	"sun"  };
	
	
	
	public static void main(String[] args) {
		int d=0,m=1,y=1900;
		String weekday = days[0];
		int i=0;
		int answer =0;
		
		while( !(d==31 && m==12 && y==2000) ) {
			
			if(m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12) { //31 days
				if(d==31) { d=0; m++; }
			}
			else if(m==4 || m==6 || m==9 || m==11) {	// 30 days
				if(d==30) { d=0; m++; }
			}
			else if(m==2 && y%4==0 && y!=1900) { // leap years
				if(d==29) { d=0; m++; }
			}
			else if(m==2 && (y%4 != 0 || y==1900)) {
				if(d==28) { d=0; m++; }
			}
			
			if(m==13) {m=1; y++;}
			
			d++;
			weekday = days[i%7];
			i++;
			
			if(d==1 && weekday.equals("sun") && y!=1900)
				answer++;
		}
		
		System.out.println(answer);
	}

	
	
}
