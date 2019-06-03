import javax.sound.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class MiniMusicPlayer3{
		static JFrame f=new JFrame("my first music video");
		static MyDrawPanel ml;

		public static void main(String[]args){
			MiniMusicPlayer3 mini=new MiniMusicPlayer3();
			mini.go();
		}

		public void setUpGui(){
			ml=new MyDrawPanel();
			f.setContentPane(ml);
			f.setBounds(30,30,300,300);
			f.setVisible(true);
		}

		public void go(){
			setUpGui();
			try{
				Sequencer sequencer=MidiSystem.getSequencer();
				sequencer.open();
				sequencer.addControllerEventListener(ml,new int[]{127});
				Sequence seq=new Sequence(Sequence.PPQ,4);
				Track track =seq.createTrack();
				int r=0;
				for(int i=0;i<60;i+=4){
					r=(int) ((Math.random()*50)+1);
					track.add(makeEvent(144,1,r,100,i));
					track.add(makeEvent(177,1,127,0,i));
					track.add(makeEvent(128,1,r,100,i+2));
				}
				sequencer.setSequence(seq);
		sequencer.setTempoInBPM(120);
		sequencer.start();
			}catch(Exception ex){
				ex.printStackTrace();
			}
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

	class MyDrawPanel extends JPanel implements ControllerEventListener{
	boolean msg=false;
	
	public void controlChange(ShortMessage event){
		msg=true;
		repaint();
	}

	public void paintComponent(Graphics g){
		if(msg){
		Graphics2D g2=(Graphics2D) g;

		int red=(int ) (Math.random()*255);
		int green=(int) (Math.random()*255);
		int blue=(int) (Math.random()*255);
		Color randomColor=new Color (red,green,blue);
		g.setColor(randomColor);

		int ht=(int) ((Math.random()*120)+10);
		int width=(int) ((Math.random()*120)+10);
		int x=(int)((Math.random()*40)+10);
		int y=(int)((Math.random()*40)+10);
		g.fillOval(70,70,100,100);
		msg=false;
		}
	}

}

}