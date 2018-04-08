
package com.hc_128;

public class Main {
	static long data[] = new long[16];
	public static void main(String[] args) {
		long key[] = new long[8];
		long iv[] = new long [8];

		double duration, speed;
		long i;
		for( i = 0; i < 4; i++) {
			key[(int) i] = 0;
			key[(int) i + 4] =0;
		}
		for(i = 0; i < 4; i++) {
			iv[(int)i]=0;
			iv[(int)i + 4]=0;
		}
		HC128 cipher = new HC128();
		cipher.initialization(key, iv);
		for(i = 0; i < 16; i++) {
			data[(int)i] = i;
		}
		for(i = 0; i < 16; i++) {
			System.out.print(Long.toHexString(cipher.u32(data[(int) i])));
			System.out.println("");
		}
		System.out.println("");
		System.out.println("");
		System.out.println("");
		long encData[] = new long[16]; 
		encData = cipher.encrypt(data);
		for(i = 0; i < 16; i++) {
			System.out.print(Long.toHexString(cipher.u32(encData[(int) i])));
			System.out.println("");
		}
		cipher.initialization(key, iv);
		long decData[] = new long[16];
		decData = cipher.encrypt(encData);
		System.out.println("");
		System.out.println("");
		System.out.println("");
		for(i = 0; i < 16; i++) {
			System.out.print(Long.toHexString(cipher.u32(decData[(int) i])));
			System.out.println("");
		}
		

		long start = System.nanoTime();
		for(i = 0; i < 0x4000000; i++) {
			cipher.encrypt(data);	
		}
		long finish = System.nanoTime();
		duration = ((double)(finish - start)/ 1000000000.0);
		speed = (((double)i)*32*16)/duration;
		
		System.out.println("Duration: "+ duration);
		System.out.println("Speed: "+ speed);
		
	}


}
