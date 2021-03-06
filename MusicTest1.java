import javax.sound.midi.*;

public class MusicTest1{
	public void play(){
		try{
		Sequencer player=MidiSystem.getSequencer();
		System.out.println("we got a sequencer");
		player.open();
		Sequence seq=new Sequence(Sequence.PPQ,4);
		Track track=seq.createTrack();

		ShortMessage a =new ShortMessage();
		a.setMessage(144,1,44,100);
		MidiEvent noteOn=new MidiEvent(a,1);
		track.add(noteOn);

		ShortMessage b =new ShortMessage();
		a.setMessage(128,1,44,100);
		MidiEvent noteOff=new MidiEvent(b,16);
		track.add(noteOff);

		player.setSequence(seq);
		player.start();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static void main(String []args){
		MusicTest1 mt=new MusicTest1();
		mt.play();
	}

	public static MidiEvent makeEvent(int comd, int chan, int one, int two,int tick){
		MidiEvent event=null;
		try{
			ShortMessage a=new ShortMessage();
			a.setMessage(comd,chan,one,two);
			event=new MidiEvent(a,tick);

		}catch(Exception a){
		}
		return event;

	}
}