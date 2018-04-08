package com.hc_128;

public class HC128 {
	public long P[], Q[], X[], Y[], counter1024;
	HC128(){
		P =  new long[512];
		Q =  new long[512];
		X =  new long[16];
		Y =  new long[16];
		
	}

	public long h1(long x){
		short a,b;
		a = u8((short)(x));

		b = u8((short)(x >> 8));



		long y = Q[a]+Q[256+b];
		return y;
	}
	public long h2(long x){
		short a,b;
		a = u8((short)(x));

		b = u8((short)(x >> 8));


		long y = P[a] + P[256+b];
		return y;
	}
	public long step_A(long u, long v, long a, long b, long c, long d, long m) {
		long tem0, tem1, tem2, tem3 = 0;
		tem0 = rotr(v,23);
		tem1 = rotr(c,10);
		tem2 = ((v ^ c) & 0x1ff) ;

		u += b + (tem0^tem1) + tem2;
		a = u;
		tem3 =h1(d);
		m ^= tem3 ^ u;
		return m;
		
	}
	public long step_B(long u, long v, long a, long b, long c, long d, long m) {
		long tem0, tem1, tem2, tem3 = 0;
		tem0 = rotr(v,23);
		tem1 = rotr(c,10);
		tem2 = ((v ^ c) & 0x1ff);

		u += b + (tem0^tem1) + tem2;
		a = u;
		tem3 = h2(d);
		m ^= tem3 ^ u;
		return m;
		
	}
	long[] encrypt(long data[]) { //each time it encrypts 512-bit data
		long cc,dd; 
		cc = (counter1024 & 0x1ff); 
		dd = ((cc+16)&0x1ff);
		if (counter1024 < 512) {
			counter1024 = (counter1024 + 16) & 0x3ff; 
			data[0] = step_A(P[(int)(cc)], P[(int)(cc+1)], X[0], X[6], X[13],X[4], data[0]);
			data[1] = step_A(P[(int)(cc+1)], P[(int)(cc+2)], X[1], X[7], X[14],X[5], data[1]);
			data[2] = step_A(P[(int)(cc+2)], P[(int)(cc+3)], X[2], X[8], X[15],X[6], data[2]);
			data[3] = step_A(P[(int)(cc+3)], P[(int)(cc+4)], X[3], X[9], X[0], X[7], data[3]); 
			data[4] = step_A(P[(int)(cc+4)], P[(int)(cc+5)], X[4], X[10],X[1], X[8], data[4]);
			data[5] = step_A(P[(int)(cc+5)], P[(int)(cc+6)], X[5], X[11],X[2], X[9], data[5]);
			data[6] = step_A(P[(int)(cc+6)], P[(int)(cc+7)], X[6], X[12],X[3], X[10],data[6]); 
			data[7] = step_A(P[(int)(cc+7)], P[(int)(cc+8)], X[7], X[13],X[4], X[11],data[7]); 
			data[8] = step_A(P[(int)(cc+8)], P[(int)(cc+9)], X[8], X[14],X[5], X[12],data[8]); 
			data[9] = step_A(P[(int)(cc+9)], P[(int)(cc+10)],X[9], X[15],X[6], X[13],data[9]); 
			data[10] = step_A(P[(int)(cc+10)],P[(int)(cc+11)],X[10],X[0], X[7], X[14],data[10]);
			data[11] = step_A(P[(int)(cc+11)],P[(int)(cc+12)],X[11],X[1], X[8], X[15],data[11]);
			data[12] = step_A(P[(int)(cc+12)],P[(int)(cc+13)],X[12],X[2], X[9], X[0], data[12]); 
			data[13] = step_A(P[(int)(cc+13)],P[(int)(cc+14)],X[13],X[3], X[10],X[1], data[13]);
			data[14] = step_A(P[(int)(cc+14)],P[(int)(cc+15)],X[14],X[4], X[11],X[2], data[14]); 
			data[15] = step_A(P[(int)(cc+15)],P[(int)(dd)], X[15],X[5], X[12],X[3], data[15]);
		}
		else {
			counter1024 = (counter1024 + 16) & 0x3ff; 
			data[0] = step_B(Q[(int)(cc)], Q[(int)(cc+1)], Y[0], Y[6], Y[13],Y[4], data[0]); 
			data[1] = step_B(Q[(int)(cc+1)], Q[(int)(cc+2)], Y[1], Y[7], Y[14],Y[5], data[1]); 
			data[2] = step_B(Q[(int)(cc+2)], Q[(int)(cc+3)], Y[2], Y[8], Y[15],Y[6], data[2]); 
			data[3] = step_B(Q[(int)(cc+3)], Q[(int)(cc+4)], Y[3], Y[9], Y[0], Y[7], data[3]);
			data[4] = step_B(Q[(int)(cc+4)], Q[(int)(cc+5)], Y[4], Y[10],Y[1], Y[8], data[4]); 
			data[5] = step_B(Q[(int)(cc+5)], Q[(int)(cc+6)], Y[5], Y[11],Y[2], Y[9], data[5]);
			data[6] = step_B(Q[(int)(cc+6)], Q[(int)(cc+7)], Y[6], Y[12],Y[3], Y[10],data[6]);
			data[7] = step_B(Q[(int)(cc+7)], Q[(int)(cc+8)], Y[7], Y[13],Y[4], Y[11],data[7]); 
			data[8] = step_B(Q[(int)(cc+8)], Q[(int)(cc+9)], Y[8], Y[14],Y[5], Y[12],data[8]); 
			data[9] = step_B(Q[(int)(cc+9)], Q[(int)(cc+10)],Y[9], Y[15],Y[6], Y[13],data[9]); 
			data[10] = step_B(Q[(int)(cc+10)],Q[(int)(cc+11)],Y[10],Y[0], Y[7], Y[14],data[10]); 
			data[11] = step_B(Q[(int)(cc+11)],Q[(int)(cc+12)],Y[11],Y[1], Y[8], Y[15],data[11]); 
			data[12] = step_B(Q[(int)(cc+12)],Q[(int)(cc+13)],Y[12],Y[2], Y[9], Y[0], data[12]);
			data[13] = step_B(Q[(int)(cc+13)],Q[(int)(cc+14)],Y[13],Y[3], Y[10],Y[1], data[13]); 
			data[14] = step_B(Q[(int)(cc+14)],Q[(int)(cc+15)],Y[14],Y[4], Y[11],Y[2], data[14]);
			data[15] = step_B(Q[(int)(cc+15)],Q[(int)(dd)], Y[15],Y[5], Y[12],Y[3], data[15]);
		
		}
		return data;
	}


	public void initialization(long key[], long iv[])
	{
		int i,j;
		
		//expand the key and iv into P and Q
		long w[] = new long[1280];
		for(i = 0; i < 8; i++) w[i] = key[i];
		for(i = 8; i < 16; i++) w[i] = iv[i-8];
		for(i = 16; i < 1280; i++) {
			w[i] = f2(w[i-2]) + w[i-7] + f1(w[i-15]) + w[i-16] + i;
		}
		
		for(i = 0; i < 512; i++) {
			P[i]= w[i+256];
		}
		for(i=0; i < 512;i++) {
			Q[i] = w[i+768];
		}
		//run the cipher 1024 steps without generating output
		for(i = 0; i < 512; i++) {
			P[i] = (P[i] + feedback_1(P[(int)u32((i - 3) & 0x1ff)],P[(int)u32((i - 10) & 0x1ff)], P[(int)u32((i - 511) & 0x1ff)] ) ^ h1(P[(int)u32((i - 12) & 0x1ff)] ));
		}
		for(i = 0; i < 512; i++) {
			Q[i] = (Q[i] + feedback_2(Q[(int)u32((i - 3) & 0x1ff)],Q[(int)u32((i - 10) & 0x1ff)], Q[(int)u32((i - 511) & 0x1ff)] ) ^ h2(Q[(int)u32((i - 12) & 0x1ff)] ));
		}
		//initialize counter1024, and tables X and Y
		counter1024 = 0;
		for (i = 0; i < 16; i++) X[i] = P[496+i];
		for (i = 0; i < 16; i++) Y[i] = Q[496+i];
		
	}
	
	
	
	public long rotr(long x, long n) {
		return (((x)>>(n))|((x)<<(32-(n))));
	}
	
	public long f1(long x) {
		return  (rotr((x),7) ^ rotr((x),18) ^ ((x) >> 3));
	}
	public long f2(long x) {
		return (rotr((x),17) ^ rotr((x),19) ^ ((x) >> 10));
	}
	
	public long f(long a, long b, long c, long d) {
		return (f2((a)) + (b) + f1((c)) + (d));
	}
	
	public long feedback_1(long v,long b,long c) {
		long tem0,tem1,tem2; 
		tem0 = rotr((v),23); tem1 = rotr((c),10); 
		tem2 = rotr(b,8);
		return (b)+(tem0^tem1)+tem2; //please check if type casting ???
		}
	
	public long  feedback_2(long v,long b, long c) { 
		long tem0,tem1,tem2; 
		tem0 = rotr((v),23); tem1 = rotr((c),10); 
		tem2 = ((v) ^ (c)) & 0x3ff; 
		return (b)+(tem0^tem1)+tem2; //please check if type casting ???
		}
	public short u8(short x) {
		return (short) ((x | 0xff00)^0xff00);
	}
	public long u32(long x) {
		return (long) ((x|0xffff0000)^0xffff0000);
	}

	

	
}
