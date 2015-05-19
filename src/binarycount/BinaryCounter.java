package binarycount;

public class BinaryCounter {
	public static void main(String[] args) {
		// バイナリカウント対象の数字
		long[] targets = {1000L, 10000000000L};
		
		for(long l : targets)
			countBitTotal(l);
	}

	public static void countBitTotal(long num) {
		
		long n = num;
		
		// 2進数でのnの桁数
		int nBinDigit = Long.toBinaryString(n).length();
		
		System.out.println(n + "の2進数表記..." + Long.toBinaryString(n));
		System.out.println(n + "の2進数での桁数..." + nBinDigit);
	
		// 1が立っているトータル数を格納する変数
		long total = 0L;
	
		// 2進数でのnの上位ビットで1が立っている数を保持する変数
		int upperBit = 0;
		
		// 2進数でのnの最上桁から1桁に向かって繰り返し
		for(int i = 0; i <= nBinDigit - 1; i++) {
			// nBinDigit - i　桁が1であれば2 の（ nBinDigit - i　- 1）乗の場合のtotal数を求める
			long operand = (long)Math.pow(2, nBinDigit -i -1); 
			if( (n & operand ) > 0 ) {
				System.out.println(nBinDigit -i + "桁目は1です。");
				for(int j = 0; j <= nBinDigit - i -1; j++) {
					total += combination(nBinDigit - i -1, j) * (j + upperBit) ;
				}
				total += 1;
				upperBit++;
			} else {
				System.out.println(nBinDigit -i + "桁目は0です。");
			}
			System.out.println(nBinDigit - i + "桁が1になるまでの1のtotal..." + total);			
		}
		
		System.out.println();
		System.out.println("===================================");
		System.out.println("n = " + n + " の場合の答えは " + total + " です。");
		System.out.println("===================================");
		System.out.println();


		
	}
	
	/**
	 * コンビネーションの数を返す
	 * @param n	nCrのnの部分
	 * @param r nCrのrの部分
	 * @return　
	 */
	public static long combination(int n, int r) {
		// nCrのnの部分
		int comN = n;
		
		// nCrのrの部分
		int comR;

		// rが大きい場合はnCr → nC(n-r)に変更
		if( r > n / 2) {
			comR = n - r;			
		} else {
			comR = r;						
		}
				
		long denominator = 1L ;	// 分母
		long numerator = 1L;	//分子
						
		// nCrを求める
		for(int i = 1; i <= comR; i++) {
			// 約分できるなら約分
			if(comN % i == 0) {
				numerator *= comN / i;
				denominator *= 1;
			} else {
				numerator *= comN;
				denominator *= i;
			}
			comN--;
			
			// 約分できるなら約分
			if(numerator % denominator == 0) {
				numerator = numerator / denominator;
				denominator = 1;
			}
			
			// 桁あふれしていないか確認
			if(numerator < 0 || denominator < 0) {
				System.out.println("桁溢れが発生しました");
				System.exit(1);
			}
		}

		return numerator / denominator;
	}
}
