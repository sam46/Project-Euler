import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Euler60 {

	// I took the first 1000 prime and listed all 4 primes that have the said property, it gave me 124 valid results, I put em in data[]. 
	// To get this list I used a program with 4 nested for-loops.
	// Now in this program,
	// I tested the first 1500 primes against data,
	// it gave me one solution: 8389
	// and the five primes are 13, 5197, 5701, 6733, 8389
	// their sum is : 26033
	// Done!!
	// For another version of the solution see project Euler60_2.

	
	static long[][] data = {
		 {3,7,109,673            }
		,{3,7,541,4159           }
		,{3,11,2069,2297         }
		,{3,17,449,2069          }
		,{3,17,449,6353          }
		,{3,17,449,6599          }
		,{3,17,2069,2297         }
		,{3,31,1237,6571         }
		,{3,37,67,2377           }
		,{3,37,67,5923           }
		,{3,37,2377,4159         }
		,{3,73,6793,7159         }
		,{3,467,617,4253         }
		,{3,2503,5281,5869       }
		,{7,19,97,3727           }
		,{7,19,97,4507           }
		,{7,19,1249,3727         }
		,{7,19,3727,5659         }
		,{7,61,1693,3181         }
		,{7,127,6949,7723        }
		,{7,433,1471,3613        }
		,{7,829,2671,3361        }
		,{7,1237,1549,3019       }
		,{7,1249,3727,6949       }
		,{7,1249,4441,6949       }
		,{7,2089,2953,3181       }
		,{7,2089,3181,4219       }
		,{7,2269,3613,5821       }
		,{11,23,743,1871         }
		,{11,23,6329,7331        }
		,{11,239,1049,1847       }
		,{11,239,1091,1847       }
		,{11,239,3467,5807       }
		,{11,353,4967,5849       }
		,{11,1103,5807,6329      }
		,{11,2297,4967,7127      }
		,{11,4013,4643,5153      }
		,{11,4643,5153,5849      }
		,{13,19,5077,6043        }
		,{13,61,2383,5431        }
		,{13,5197,5701,6733      }
		,{17,2741,3917,4649      }
		,{17,2741,3917,6899      }
		,{19,31,1237,6271        }
		,{23,47,1481,4211        }
		,{23,47,2333,5927        }
		,{23,47,4211,5483        }
		,{23,89,2357,6323        }
		,{23,311,677,827         }
		,{23,677,827,1871        }
		,{23,743,1871,6323       }
		,{23,1607,5009,5849      }
		,{23,2003,3557,5051      }
		,{23,2819,3617,7607      }
		,{29,71,5237,6563        }
		,{29,3767,4523,6911      }
		,{31,1123,2029,2281      }
		,{31,1123,2029,5281      }
		,{31,3037,3331,5701      }
		,{37,991,2269,3613       }
		,{37,1549,2707,3463      }
		,{37,2707,4909,5857      }
		,{41,1013,7187,7331      }
		,{43,613,6547,6967       }
		,{47,251,5147,5273       }
		,{47,947,1439,4931       }
		,{47,2333,5297,6701      }
		,{67,619,5419,7687       }
		,{71,821,1163,5849       }
		,{79,967,1117,3511       }
		,{79,1801,3253,3547      }
		,{83,449,6491,7583       }
		,{89,107,1061,4973       }
		,{97,379,2221,5581       }
		,{97,2221,4513,5581      }
		,{101,2633,2699,4337     }
		,{127,2281,6361,6481     }
		,{131,797,2411,5519      }
		,{151,4801,5197,6733     }
		,{163,367,4597,5563      }
		,{199,3259,4327,5107     }
		,{229,613,4021,6733      }
		,{239,1049,1847,7523     }
		,{269,617,887,2741       }
		,{283,1321,3001,6607     }
		,{313,1231,4231,6037     }
		,{313,1747,4231,6037     }
		,{397,907,3919,4567      }
		,{401,2441,3671,7691     }
		,{401,3407,3671,7691     }
		,{419,443,7757,7883      }
		,{419,449,563,5297       }
		,{467,587,617,6323       }
		,{521,1523,2447,7907     }
		,{677,2309,4451,6257     }
		,{809,1361,2141,3947     }
		,{809,3947,4679,5393     }
		,{967,5623,6421,6949     }
		,{1087,5413,5569,6451    }
		,{1103,3041,6317,7529    }
		,{1123,3919,5281,7489    }
		,{1153,1699,6691,7507    }
		,{1217,2309,5081,7817    }
		,{1289,2801,4733,7919    }
		,{1319,2243,2789,4133    }
		,{1439,2003,5657,6983    }
		,{1439,3833,5657,6983    }
		,{1447,2803,3769,4051    }
		,{1451,2699,3413,3761    }
		,{1523,2447,5813,7907    }
		,{1523,3203,5279,6827    }
		,{1583,1877,2447,6113    }
		,{1621,2767,3187,7477    }
		,{1753,1951,3547,3643    }
		,{1783,2953,3643,4273    }
		,{2039,3299,3767,4523    }
		,{2063,2243,5477,6599    }
		,{2269,4057,4663,7879    }
		,{2593,3307,5443,7039    }
		,{2801,4733,6689,7331    }
		,{2897,4721,4877,5501    }
		,{3391,3433,3643,6607    }
		,{5023,5443,6841,7039    }
		,{5443,6637,6841,7039    }

	};
	
	public static void main(String[] args) throws IOException {
		primes.add(2l);
		primes.add(3l);
		long tem = 3;

		while(primes.size() < 1500) {	isPrime(tem++);	if(tem == 8389) System.out.println("size:"+primes.size());	}

		Long[] ppp = new Long[0];
		ppp = primes.toArray(ppp);

		
		for (int i = 0; i < ppp.length; i++) {
			for (int j = 0; j < data.length; j++) {
				if(check(data[j],ppp[i])){
					System.out.println(data[j][0] + ", "+ data[j][1] +", "+ data[j][2] +", "+ data[j][3] +", "+ ppp[i]);
					long sum = data[j][0] + data[j][1] + data[j][2] + data[j][3] + ppp[i];
					System.out.println("sum: "+sum);
				}
			}
		}
		
		
		// The End
		System.out.println("Done!!");
	}
	

	static boolean isPair(long a, long b){
		long x = Long.parseLong(String.valueOf(a) + String.valueOf(b));
		if(!isPrime(x))	return false;
		x = Long.parseLong(String.valueOf(b) + String.valueOf(a));
		if(isPrime(x))	return true;
		return false;
	}
	
	static boolean check(long[] arr, long x){
		for (int i = 0; i < arr.length; i++) {
			if(!isPair(x, arr[i]))
				return false;
		}

		return true;
	}
	
	
	
	
	
	static List<Long> primes = new ArrayList<Long>();
	static boolean isPrime(long x){
		if(primes.contains(x))
			return true;
		if(x==2 || x==3)
			return true;
		if(x==1)
			return false;
		
//-----------------------------------------\\
		long n = (long) Math.ceil(Math.sqrt(x));
		long i =2;
		while( i<= n){
			if(x%i == 0)
				return false;
			i++;
		}
		
		primes.add(x);
		return true;
	}
	
}
