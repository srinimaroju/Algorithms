public class Deck {
	Card[] cards;

	public static final short[] card_numbers={1,2,3,4,5,6,7,8,9,10,11,12,13};
	

	public static final short HEART=1;
	public static final short SPADE=2;
	public static final short DIAMOND=3;
	public static final short FLOWER=4;
	
	public static final short[] 
			card_suites={Deck.HEART,Deck.SPADE,Deck.DIAMOND,Deck.FLOWER};

	public Deck() {
		cards = new Card[52];
		short i=0;
		for(short num:Deck.card_numbers){
			for (short suite:Deck.card_suites) {
				cards[i++]=(new Card(num,suite));
			}
		}
	}
	public void shuffle() {

	}
	public String toString(){
		String output="";
		for(Card card:this.cards){
			output += card.toString()+"\n";
		}
		return output;
	}
    public static void main(String args[])
    {

    	Deck deck = new Deck();
    	System.out.println ("deck is \n" +  deck);
    }
}

class Card {
	short number;
	short suite;
	public Card(short number, short suite) {
		this.number=number;
		this.suite=suite;
	}
	public String toString() {
		return "[Card | Number: "+this.number+" | Suite:" + this.suite + "]";
	}
}