

import processing.core.PApplet;
import processing.core.PFont;

public class Main extends PApplet{
	PFont f,g;
	Directory dir=new Directory();
	int num=0;
	String str_num="";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(new String[] { Main.class.getName() });
	}
	
	public void settings() {
		size(800,800);
	}
	
	public void setup(){
		textSize(128);
        f = createFont("Arial",16,true);
        //noStroke();
    }
	public void draw(){
		String gd=Integer.toString(dir.globalDepth);
		background(150);
		fill(0);
		textSize(24);
		text("Extendible Hashing Simulator",250,30,0);
		text("Enter key",40,180);
		textSize(24);
		text("Global Depth: "+gd,30,80);
		
		fill(255);
		rect(30,200,125,50);
		textFont(f,16);
    	fill(0);
    	text(str_num,40,230);
        int x=50;
        System.out.println(dir.buckets.size());
		for(int i=0;i<dir.buckets.size();i++) {
			Bucket b=dir.buckets.get(i);
			
			fill(255);
        	rect(250,x,100,50);
        	
        	
        	int len=dir.globalDepth;
        	String bin="";
        	int k;
        	k=i;
        	int req=0;
        	while(k>0)
        	{
        		bin+=Integer.toString(k%2);
        		k/=2;
        		req++;
        	}
        	while(req<len) {bin+="0";req++;}
        	StringBuilder binary = new StringBuilder();
        	binary.append(bin);
        	binary=binary.reverse();
        	String fin="";
        	fin+=binary;
        	textFont(f,16);
        	fill(0);
        	text(fin,275,x+30);
        	
        	int y=0;
        	for(int j=0;j<b.bucketSize;j++) {
        		fill(255);
        		rect(400+y,x,50,50);
        		y+=50;
        	}
        	
        	String ld=Integer.toString(b.localDepth);
        	textFont(f,16);
        	fill(0);
        	text("Local Depth: "+ld,400+y+15,x+30);
        	
        	y=0;
        	for(Pair p:b.records) {
        		String s=Integer.toString(p.getVal());        		
        		textFont(f,16);
            	fill(0);
            	text(s,415+y,x+30);
            	y+=50;
        	}
        	
        	x+=55;
		}
    }
	
	public void keyPressed() {
		if(key>='0' && key<='9') {
			str_num+=key;
			redraw();
		}
		if(key==ENTER || key==RETURN) {
			num = Integer.parseInt(str_num);
		    str_num = "";
		    System.out.println(num);
		    dir.insert(num%11, num);
		    redraw();
		}
		if(keyCode==(int)BACKSPACE && str_num.length()>0) {
			str_num=str_num.substring(0,str_num.length()-1);
			redraw();
		}
	}
}
