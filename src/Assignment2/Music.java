package Assignment2;

/* Class: CISC 3130
* Section: MY9
* EmplId: 23402081
* Name: Chongjia Deng
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

//*************************** Iterator  ***************************************//
class SongList implements Iterable<Music> {   //interface which belongs to collection framework
	 private int currentIndex = 0;
	 private static ArrayList<Music> SongList;
	 private ArrayList<Music> list = new ArrayList<Music>();
	 
public SongList() {    // a constructor for iterate a array list.
	SongList= new ArrayList<Music>();
}
public SongList(Music[] arr){  // adding arry in to the list.
	this();
    for(Music m:SongList)
            SongList.add(m);
}

public static void add(Music music){ // a method add music in to the list.
	 if(SongList.size()==0){
		 SongList.add(music);
	 }
	 else
	 for(int i=0; i<SongList.size();i++){
		 if(!SongList.get(i).equals(music)){
			 SongList.add(music);
		 }
		 else
            System.out.println(music+"already there");
   }
}


public Iterator<Music> iterator() {      // Iterator to traverse the list 
    return list.iterator();
}
}
//*************************** Stack ***************************************//
class songHistory{ // To Create a history list class for song which we had played, we use stack.Because stack is first in last out. Easy to show up.
	private int top;   // top of stack
    private int maxSize;  // size of stack array
    private String[] history;
    
    public  songHistory(int s) { // constructor
    	maxSize = s;  // set array size
    	history = new String[maxSize]; // create array
        top = -1;  // no items yet  
    }
    
    public void push(String s) { // put item on top of stack
    	history[++top] = s;  // increment top, insert item
    }
    
    public String pop() { // take item from top of stack
    	return history[top--]; // access item, decrement top
    }
    
    public String peek() { // peek at top of stack
    	return history[top];
    }
    
    public boolean isEmpty() { // true if stack is empty
    	return (top == -1);
    }
    
    public boolean isFull() {  // true if stack is full
    return (top == maxSize -1);
    }
    
	public String lastListene(songHistory list) { 
	return list.pop(); 				// return the recently song, which is the first song of the history list (stack).
	}

}
//*************************** Queue ***************************************//
class playlist { 
	//// We use queue to show up a play list for song, because queue is first in first out, it is easy to show up what we have in the list.
	private int maxSize;
	private String[] playlist;
	private int front;
	private int rear;
	private int nItems;
	private int elements;
	
	public playlist(int s) {  // the constructor for the queue
		  maxSize = s;
		  playlist = new String[maxSize];
	      front = 0;
	      rear = -1;
	      nItems = 0;
	}
	public void insert(String s) {// put song at rear of the queue
		  if(rear == maxSize -1) // deal with wraparound
			  rear = -1;
		  playlist[++rear] = s;  // increment rear and insert
		  nItems++;				// one more item
	}
	public String remove() {
		String temp = playlist[front++]; // take Song from front of queue
										// get tracksName and incr front
		if (front == maxSize)  // deal with wraparound
			front = 0;			
		nItems --;				// one less item(song).
		return temp;
	}
	public String peekFront() {  // peek the song at front of queue
		return playlist[front];
	}
	public boolean isEmpty() {// check if queue is empty.
		return(nItems==0);
	}
	public boolean isFull() {
		return (nItems == maxSize); //check if queue is full.
	}
	
	public playlist weekList(Music Songs ) { // a method for week list.
		  
		  playlist week = new playlist(Songs.nElems); // create a queue for week list.
		
		  for(int i =0; i < Songs.nElems; i++) { // count element of the song (ArrayList) in the Music class.
			  week.insert(Songs.getTrackName());	 //get every name of song from the ArrayList.
    } 
		 return week; 	//return the queue.
	}

	public playlist mergingFunction(playlist first, playlist second){// A method to merger two queue in to one queue.
		playlist mergedQueue = new playlist(first.maxSize + second.maxSize);  // A result queue for merge two queue in to one.
		   while (!first.isEmpty() && !second.isEmpty()) {    // while two queue are not empty.
	           String left = first.peekFront(); 
	           String right = second.peekFront();  		
	       if(left.compareTo(right)<0) {			// Set the condition by using the alphabetical order method to compare each character.
	           mergedQueue.insert(first.remove());
	       } else {
	           mergedQueue.insert(second.remove());
	       }
		   }
		   while (!first.isEmpty()) {				// insert element to the new queue, while it not empty.
	           mergedQueue.insert(first.remove());
		   }
	       while (!second.isEmpty()) {
	           mergedQueue.insert(second.remove());
	       }
	       return mergedQueue;			// return the new merged queue.
	}

	public playlist mergedTwoList(playlist week1, playlist week2) {// merged two queue and display the play list.
	
		  playlist result = mergingFunction(week1, week2);
		  return result;
	}
	
	//public String toString() {
	//	  return 
		//
	//}
	//System.out.println(result.toString());
	  
	public void addSong(playlist s){ // add the specific name of song in the queue(playlist).
		  if(rear == maxSize -1) 
			  rear = -1;
		  playlist[++rear] = s.peekFront();  // increment rear and insert
		  nItems++;				// one more item 
	}
	  
	public void listenToSong(playlist songlist){ // retrieves the next song to listen to history list.
		  int maxSize = 1;
		  songHistory history = new songHistory(maxSize);
		  while(!songlist.isEmpty()) {
			  history.push(songlist.remove());  // poll song list from the queue, adding to the history list.
			 maxSize ++; 
		  }
	  } 
}


//*************************** ArrayList ***************************************//

class Music{   // we create a music class prepare to convert array list in to read CSV file.
	 private int position; 	// the index of each song of the play list.
	 private String trackName; // the name of song is String.
	 private String artist; //   the name of artist is String.
	 private int streams; // the streaming of the amount is number.
	 private String url;  // URL is the String.
	 public static int nElems = 0;  // Initialize the text line.

	 public Music(int position, String trackName,String artist,int streams, String url) { // A constructor for the Music's array list.
	 this.position = position;  
	 this.trackName = trackName;
	 this.artist = artist;
	 this.streams = streams;
	 this.url = url;
	 nElems++;
	}
	public void setPosition(int position) {// set value to the position.
		this.position = position;
	}
	public int getPosition() {
		return position;			// a method return the index of song.
	}
	public void setTrackName(String trackName) { // set the name of the song to the array list
		this.trackName = trackName;
	}
	public String getTrackName() { // a method return the name of the song.
		return trackName;
	}
	public void setArtist(String artist) {  // set the name of the singer to the array list.
		this.artist = artist;
	}
	public String getArtist() {  //a method return the name of singer.
		return artist;
	}
	public void setStreams(int streams) { //set the value to thte streaming number.
		this.streams= streams;
	}
	public void setURL(String url) { // set String to the URL in to the array list.
		this.url= url;
	}
	public String getURL() {  // a method return the URL String.
		return url;
	}
	public int compareTo(Music another) {   //a method using alphabetical order compare the name of song.
		if(trackName.compareTo(another.trackName) > 0) {  
			return 1;}   // if the character less or bigger than another in assic table. Yes bigger return 1, on the contrary return -1.
		else
			return -1;
	}	



}

class MyApp{
	 public  ArrayList readFiled(String fileName) throws FileNotFoundException, IOException { 
			// a method read CSV file in to the array list.
		   String name = fileName;
		   ArrayList Songs = new ArrayList<>(); 
		   BufferedReader br = new BufferedReader(new FileReader(name)); //setup a scanner
		   String line = br.readLine(); 
		    while((line = br.readLine())!= null && !line.isEmpty()){ // // while next line in CSV file is not empty.
		    String[] fields = line.split(","); // Read line in from file until see the "comma".
		    String index = fields[0];   //copy the content of the array to the String.
		    String trackName = fields[1];
		    String artist = fields[2];
		    String streamsNum = fields[3];
		    String url = fields[4];
		    int position = Integer.parseInt(index);  // convert the String to number. because the position and streaming is integer.
		    int streams = Integer.parseInt(streamsNum);
		    Music list = new Music (position, trackName, artist, streams, url); 
		    // using the constructor which we set before, arrayList(int,String,String,String, int,String).
		    Songs.add(list);  // all information adding to the list. then read next line. 
		    }
		    br.close();
		    return Songs;
		   	}
	 
	 public void read(String filename1,String filename2) {
		 
		 try {
			
			ArrayList read1 = readFiled(filename1);
			ArrayList read2 = readFiled(filename2);
			playlist list1 = new weekList(read1);
			playlist list2 = new weekList(read2);
			
			playlist Playlist =  new mergedTwoList(list1,list2);
			
			Playlist.listenToSong();
			Playlist.addSong();
			Playlist.lastListene();
			System.out.print(Playlist.toString());
		}  catch(Exception e) {
        	System.out.println(e);// if does not exit the CSV file.
		}
	 }
		public static void main(String[]args) {	
			String name1 = "weekly-2020-01-17--24.csv";
			String name2 = "weekly-2020-02-17--24.csv";

			MyApp test = new MyApp();
			test.read(name1,name2);
		
}
}
