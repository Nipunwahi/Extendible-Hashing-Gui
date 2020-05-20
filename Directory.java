

import java.util.ArrayList;
import java.util.List;

public class Directory {
	int globalDepth=1;
	List<Bucket> buckets=new ArrayList<Bucket>();
	
	public Directory() {
		for(int i=0;i<(1<<globalDepth);i++)
		{
			buckets.add(new Bucket());
		}
	}
	
	Bucket getBucket(int key) {
		Bucket b=buckets.get(key & ((1<<globalDepth)-1));
		return b;
	}
	
	void insert(int key,int value) {
		Bucket b=getBucket(key);
		
		if(b.isFull() && b.localDepth==globalDepth) {
			List<Bucket> temp=new ArrayList<Bucket>(buckets);
			buckets.addAll(temp);
			globalDepth++;
		}
		
		if(b.isFull() && b.localDepth<globalDepth) {
			b.put(key, value);
			Bucket b1, b2;
			b1 = new Bucket();
			b2 = new Bucket();
			for (Pair p : b.records) {
				int k=p.getKey();
				int v=p.getVal();

				int h = k & ((1 << globalDepth) - 1);

				if ((h | (1 << b.localDepth)) == h)
					b2.put(k, v);
				else
					b1.put(k, v);
			}

			List<Integer> l = new ArrayList<Integer>();

			for (int i = 0; i < buckets.size(); ++i)
				if (buckets.get(i) == b)
					l.add(i);

			for (int i : l)
				if ((i | (1 << b.localDepth)) == i)
					buckets.set(i, b2);
				else
					buckets.set(i, b1);

			b1.localDepth = b.localDepth + 1;
			b2.localDepth = b1.localDepth;

		}
		
		else{
			b.put(key, value);
		}
	}
	
	int get(int key) {
		return getBucket(key).get(key);
	}
}
